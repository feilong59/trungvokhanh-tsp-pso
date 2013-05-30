package apache.hbase;

import graph.Graph;
import graph.TSP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class Table {
    public static final byte[] TABLE = Bytes.toBytes("table");
    public static final byte[] FAM_MAIN = Bytes.toBytes("main");
    public static final byte[] COL_TSP = Bytes.toBytes("tsp");
    public static Configuration conf = HBaseConfiguration.create();
    public static final int NUMBER_OF_PARTICLES   = 100;

    @SuppressWarnings("resource")
    public static void create() throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(TABLE)) {
            System.out.println("table already exists!");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(TABLE);
            tableDesc.addFamily(new HColumnDescriptor(FAM_MAIN));
            admin.createTable(tableDesc);
        }
    }

    @SuppressWarnings("resource")
    public static void delete() throws Exception {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            admin.disableTable(TABLE);
            admin.deleteTable(TABLE);
        } catch (Exception e) {
        }
    }
    
    @SuppressWarnings("resource")
    public static byte[] get(byte[] rowKey) throws IOException {
        HTable table = new HTable(conf, TABLE);
        Get getRowData = new Get(rowKey);
        getRowData.addColumn(FAM_MAIN, COL_TSP);
        Result result = table.get(getRowData);            
        return result.getValue(FAM_MAIN, COL_TSP);
    }   
    
    @SuppressWarnings("resource")
    public static void put(Graph g) throws IOException {
        byte[] rowKey = Bytes.toBytes("graph");
        byte[] value = Bytes.toBytes(g.code());
        Put put = new Put(rowKey);
        put.add(FAM_MAIN, COL_TSP, value);
        HTable table = new HTable(conf, TABLE);
        table.put(put);
    }

    @SuppressWarnings("resource")
    public static void put(int n) throws IOException {
        byte[] rowKey = Bytes.toBytes("n");
        byte[] value = Bytes.toBytes(n);
        Put put = new Put(rowKey);
        put.add(FAM_MAIN, COL_TSP, value);
        HTable table = new HTable(conf, TABLE);
        table.put(put);
    }
        
    @SuppressWarnings("resource")
    public static void put(TSP[] tsp) throws IOException {
        List<Put> puts = new ArrayList<Put>();
        for (int i = 1; i <= NUMBER_OF_PARTICLES; i++) {
            byte[] rowKey = Bytes.toBytes(i);
            byte[] value = Bytes.toBytes(tsp[i].toString());
            Put put = new Put(rowKey);
            put.add(FAM_MAIN, COL_TSP, value);            
            puts.add(put);
        }
        HTable table = new HTable(conf, TABLE);
        table.put(puts);
    }
        
}
