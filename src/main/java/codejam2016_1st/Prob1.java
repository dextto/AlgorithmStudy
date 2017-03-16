package codejam2016_1st;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Prob1 {
    
    public static void printChildren(ArrayList<Integer>[] children) {
        String ret;
        for(ArrayList<Integer> cs : children) {
            ret = "";
            if (cs == null) {
                System.out.println("null");
            } else {
                for(Integer c : cs) {
                    ret += c + " ";
                }
                System.out.println(ret);
            }
        }
    }
    public static void printParent(int[] list) {
        String ret = "P: ";
        for(Integer i : list) {
            ret += i + " ";
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws IOException {
        String inFile = args[0];
        String outFile = (inFile.split(".in"))[0] + ".out";

        BufferedReader r = new BufferedReader(new FileReader(inFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(outFile));
        int T = Integer.parseInt(r.readLine()); // test case

        for(int _i = 0; _i < T; _i++) {
            // 부모와 자식 배열을 만든다.
            int N = Integer.parseInt(r.readLine());
            int[] parents = new int[N + 1]; // 인덱스 계산을 편하게 하기 위해 0번 인덱스는 사용하지 않는다.
            ArrayList<Integer>[] children = new ArrayList[N + 1];

            for (int i = 0; i < N - 1; i++) {
                String[] line = r.readLine().split(" ");
                int parent = Integer.parseInt(line[0]);
                int child = Integer.parseInt(line[1]); // 이름을 child로 하면 children배열과 헷갈려서 이름을 node로..
                
                // 부모 추가
                parents[child] = parent;

                // 자식 추가
                if (children[parent] == null) {
                    children[parent] = new ArrayList<Integer>(); 
                }
                children[parent].add(child);
            }

//            printParent(parents);
//            printChildren(children);
            
            // chlidren 배열을 훑어가면서 높이를 계산한다.
            int max = 2;
            for (int i = 1; i <= N; i++) {
                if (children[i] == null) continue;

                for (int child : children[i]) {
                    int h = 1; // 현재 child에서 최상단 직계 부모까지의 크기
                    int parent = parents[child];
                    while(parent != 0) {
                        h++;
                        parent = parents[parent];
                    }
                    max = Math.max(max, h);
                }
            }
            
            w.write(Integer.toString(max));
            w.write("\n");
        }

        r.close();
        w.close();
    }
}
