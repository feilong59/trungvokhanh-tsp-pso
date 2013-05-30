package graph;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tool {
    
    public static String[] split(String s) {
        String[] tokens = new String[3];
        String temp;
        int n = 0;
        temp = "";
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) != ' ')
                temp += s.charAt(i);
            else {
                if (temp.length() > 0) {
                    tokens[n] = temp;
                    n++;
                    temp = "";
                }
            }
        if (temp.length() > 0)
            tokens[n] = temp;
        return tokens;
    }
    
    public static void readData(String fileName, Graph g) throws NumberFormatException, IOException {
        int n;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            n = Integer.parseInt(br.readLine());
            g.setN(n);
            for (int i = 1; i <= n; i++) {
                String line = br.readLine();
                String[] chunks = split(line);
                double x = Double.parseDouble(chunks[1]);
                double y = Double.parseDouble(chunks[2]);
                g.setNodes(i, x, y);
            }
        } finally {
          if (br != null)
              br.close();
        }        
    }

}
