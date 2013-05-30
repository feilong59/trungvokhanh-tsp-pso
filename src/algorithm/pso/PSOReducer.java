package algorithm.pso;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PSOReducer extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text sourceParticle, Iterable<Text> particles, Context context) throws IOException, InterruptedException {
        StringBuilder s = new StringBuilder();
        for (Text t: particles) {
            s.append(t.toString()).append(",");
        }
        if (s.length() > 0)
            s.deleteCharAt(s.length() - 1);
        context.write(sourceParticle, new Text(s.toString()));
    }

}
