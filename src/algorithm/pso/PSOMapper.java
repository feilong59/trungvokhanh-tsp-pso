package algorithm.pso;

import java.io.IOException;
import java.util.Random;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PSOMapper extends Mapper<LongWritable, Text, Text, Text> {
    public static final int NUMBER_OF_PARTICLES  = 50;
    Random rand = new Random();
    
    public void map(LongWritable lineNumber, Text line, Context context) throws IOException, InterruptedException {
        int numberOfIterations;
        numberOfIterations = rand.nextInt(10);
        boolean visit[] = new boolean[NUMBER_OF_PARTICLES + 1];
        for (int i = 0; i < numberOfIterations; i++) {
            int particle = rand.nextInt(NUMBER_OF_PARTICLES) + 1;
            if (!visit[particle])
                context.write(line, new Text(String.valueOf(particle)));
            visit[particle] = true;
        }
    }
}
