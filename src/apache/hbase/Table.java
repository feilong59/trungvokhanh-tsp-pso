package apache.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

public class Table {
    public static final byte[] FAM_MAIN = Bytes.toBytes("main");
    public static final byte[] COL_TSP = Bytes.toBytes("tsp");
    public static Configuration conf = HBaseConfiguration.create();

    @SuppressWarnings("resource")
    public static void create(String tableName) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        if (admin.tableExists(tableName)) {
            System.out.println("table already exists!");
        } else {
            System.out.println("create table " + tableName + " ok.");
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            tableDesc.addFamily(new HColumnDescriptor(FAM_MAIN));
            admin.createTable(tableDesc);
        }
    }

    @SuppressWarnings("resource")
    public static void delete(String tableName) throws Exception {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        }
    }
    
    public byte[] get(String tableName, byte[] rowKey) throws IOException {
        HTable table = new HTable(conf, tableName);
        Get getRowData = new Get(rowKey);
        getRowData.addColumn(FAM_MAIN, COL_TSP);
        Result result = table.get(getRowData);            
        return result.getValue(FAM_MAIN, COL_TSP);        
    }        
        
}
