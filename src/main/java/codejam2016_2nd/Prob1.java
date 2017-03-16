package codejam2016_2nd;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Prob1 {
    private static final double PI = 3.141592;


    public static void main(String[] args) throws IOException {
        String inFile = args[0];
//        String inFile = "Set1.in"; // for test
//        String inFile = "prob1_Set1_ex.in"; // for test

        String outFile = (inFile.split(".in"))[0] + ".out";


        BufferedReader r = new BufferedReader(new FileReader(inFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(outFile));
        int cases = Integer.parseInt(r.readLine()); // test case


        while(cases-- > 0) {
            String[] line = r.readLine().split("\\s");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            double n = Double.parseDouble(line[2]);

            double l3 = y * 2 * (Math.PI - Math.atan(x / y) - Math.atan((n - x) / y));
            int ans = (int)(n + Math.ceil(l3));
//            System.out.println(ans);
            w.write(Integer.toString(ans));
            w.write("\n");
        }


        r.close();
        w.close();
    }
}
