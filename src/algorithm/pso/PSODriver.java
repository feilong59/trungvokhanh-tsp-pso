package algorithm.pso;

import graph.Graph;
import graph.TSP;
import graph.Tool;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import algorithm.generate.RandomTravelling;
import apache.hbase.Table;

public class PSODriver {
    public static final int NUMBER_OF_PARTICLES   = 100;
    public static final int NUMBER_OF_ITERATIONS  = 1;
    public static final String FILE_PATH  = "eil51.tsp";
    public static final Path input  = new Path("input");
    public static final Path output = new Path("output");
    TSP[] tsp;
    Graph g;
    
    private void initial() throws Exception {
        g = new Graph();
        Tool.readData(FILE_PATH, g);
        tsp = new TSP[NUMBER_OF_PARTICLES + 1];
        for (int i = 1; i <= NUMBER_OF_PARTICLES; i++) {
            tsp[i] = new RandomTravelling(g.getN());
            tsp[i].setCost(g.calculateCost(tsp[i].getNodes()));
        }
//        Table.delete();
        Table.create();
        Table.put(g);
        Table.put(g.getN());
    }
    
    private void format() throws IOException {
        FileSystem fs = FileSystem.get(new Configuration());
        if (fs.exists(output))
            fs.delete(output, true);
    }
        
    private void runMapReduce() throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "PSO algorithm");
        job.setJarByClass(this.getClass());
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setMapperClass(PSOMapper.class);
        job.setReducerClass(PSOReducer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, input);
        FileOutputFormat.setOutputPath(job, output);
        job.waitForCompletion(true);
    }
    
    public void run() throws Exception {
        initial();
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            Table.put(tsp);            
            runMapReduce();
            format();
        }
    }
    
    public void test() throws IOException {
        String graph = Bytes.toString(Table.get(Bytes.toBytes("graph")));
        int n = Bytes.toInt(Table.get(Bytes.toBytes("n")));
        Graph g1 = new Graph(n);
        g1.decode(graph);
        System.out.println(g1.getN());
        for (int i = 1; i <= g1.getN(); i++) {
//            System.out.println(g1.getNode(i).toString());
            byte[] row = Bytes.toBytes(i);
            String data = Bytes.toString(Table.get(row));
            System.out.println(tsp[i].getCost());
            System.out.println(data);
        }
    }
    
    public static void main(String[] args) throws Exception {
        PSODriver driver = new PSODriver();
        driver.run();
        driver.test();
    }

}
