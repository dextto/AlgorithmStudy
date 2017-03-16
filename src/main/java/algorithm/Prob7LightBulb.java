package algorithm;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Prob7LightBulb {

    static int UP = 0;
    static int DOWN = 1;
    
    public static void main(String[] args) throws IOException {
//        String inFile = "sample.in";
//        String inFile = "small_2.in";
        String inFile = "large.in";
        //String inFile = args[0];
        String outFile = (inFile.split(".in"))[0] + ".out";

        BufferedReader r = new BufferedReader(new FileReader(inFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(outFile));
        int T = Integer.parseInt(r.readLine()); // test case

        for(int _i = 0; _i < T; _i++) {
            String[] line = r.readLine().split(" ");
            int N = Integer.parseInt(line[0]); 
            int M = Integer.parseInt(line[1]); 

            // 정답지 작성
            boolean[][] BULBS = new boolean [2][N]; 
            for (int i = 0; i < M; i++) {
                line = r.readLine().split(" ");
                int row = Integer.parseInt(line[0]) - 1; 
                int col = Integer.parseInt(line[1]) - 1; 
                BULBS[row][col] = true;
            }

            // 2x2 window를 옮겨가며 체크
            boolean[][] bulbs = new boolean [2][N];
            int cnt = 0;
            int i = 0;
            for (i = 0; i < N - 2; i++) {
//                System.out.println((up ? "T" : "F") + " " + (down ? "T" : "F"));

                // 아래 위 둘다 같은 경우
                if (BULBS[0][i] == bulbs[0][i] && BULBS[1][i] == bulbs[1][i]) {
                    cnt += bulbs[0][i] ? 1 : 0;
                    continue;
                }

                // 둘 중 하나가 다를 경우
                if ((BULBS[0][i] != bulbs[0][i] && BULBS[1][i] == bulbs[1][i]) ||
                    (BULBS[0][i] == bulbs[0][i] && BULBS[1][i] != bulbs[1][i])) {
                    int baseIdx = bulbs[0][i] == BULBS[0][i] ? 0 : 1; // 기준 위치를 정답이랑 같은 것으로 하자.
                    int oppnIdx = (baseIdx == 0) ? 1 : 0;    // 기준 위치의 아래 또는 위 
                    
                    int nextIdx = i + 1;

                    // 다음 칸에 대한 4가지 케이스
                    // 1. 다음 칸 위/아래가 정답지와 모두 동일한 경우
                    if (BULBS[baseIdx][nextIdx] == bulbs[baseIdx][nextIdx] &&
                        BULBS[oppnIdx][nextIdx] == bulbs[oppnIdx][nextIdx]) {
                        int nextNextIdx = nextIdx + 1;
                        // 1-1. 다다음칸 아래 위 중 하나만 같 경우
                        if (BULBS[baseIdx][nextNextIdx] == bulbs[baseIdx][nextNextIdx] &&
                            BULBS[oppnIdx][nextNextIdx] != bulbs[oppnIdx][nextNextIdx]
                                ) {
                            bulbs[oppnIdx][i] = !bulbs[oppnIdx][i]; // 이 루틴은 사실 필요없음
                            bulbs[oppnIdx][nextNextIdx] = !bulbs[oppnIdx][nextNextIdx]; 
                            cnt += 3;
                            i++; // 2칸을 이동해야 하기 때문
                        } else if (BULBS[baseIdx][nextNextIdx] != bulbs[baseIdx][nextNextIdx] &&
                                   BULBS[oppnIdx][nextNextIdx] == bulbs[oppnIdx][nextNextIdx]) {
                            bulbs[oppnIdx][i] = !bulbs[oppnIdx][i]; // 이 루틴은 사실 필요없음
                            bulbs[baseIdx][nextNextIdx] = !bulbs[baseIdx][nextNextIdx]; 
                            cnt += 3;
                            i++; // 2칸 이동
                        }
                        // 1-2. 다다음칸도 아래 위가 정답지와 같은 경우, 다음 칸만 업뎃하고 1칸 이동
                        else {
                            bulbs[oppnIdx][i] = !bulbs[oppnIdx][i]; // 이 루틴은 사실 필요없음
                            bulbs[oppnIdx][nextIdx] = !bulbs[oppnIdx][nextIdx];
                            cnt++;
                        }
                    } 
                    // 2. 다음 칸 중 하나만 정답지와 같은 경우
                    else if (BULBS[baseIdx][nextIdx] == bulbs[baseIdx][nextIdx] &&
                             BULBS[oppnIdx][nextIdx] != bulbs[oppnIdx][nextIdx] ){
                        bulbs[oppnIdx][i] = !bulbs[oppnIdx][i]; // 이 루틴은 사실 필요없음
                        bulbs[oppnIdx][nextIdx] = !bulbs[oppnIdx][nextIdx];
                        cnt++;
                        i++; // 2칸 이동
                    } else if (BULBS[baseIdx][nextIdx] != bulbs[baseIdx][nextIdx] &&
                               BULBS[oppnIdx][nextIdx] == bulbs[oppnIdx][nextIdx]) {
                        bulbs[oppnIdx][i] = !bulbs[oppnIdx][i]; // 이 루틴은 사실 필요없음
                        bulbs[baseIdx][nextIdx] = !bulbs[baseIdx][nextIdx];
                        cnt += 2;
                        i++; // 2칸 이동
                    }
                    // 3. 다음 칸이 둘다 정답지와 다른 경우
                    else if (BULBS[baseIdx][nextIdx] != bulbs[baseIdx][nextIdx] &&
                             BULBS[oppnIdx][nextIdx] != bulbs[oppnIdx][nextIdx]) {
                        bulbs[oppnIdx][i] = !bulbs[oppnIdx][i]; // 이 루틴은 사실 필요없음
                        bulbs[oppnIdx][nextIdx] = !bulbs[oppnIdx][nextIdx]; 
                        cnt++;
                    }
                }
            }

            if (i == N - 2) {
                if (BULBS[0][i] != bulbs[0][i] && BULBS[1][i] != bulbs[1][i]) { // 둘 다 정답과 다를 경우
                    bulbs[0][i] = !bulbs[0][i]; // 이 루틴은 사실 필요없음
                    bulbs[1][i] = !bulbs[1][i]; // 이 루틴은 사실 필요없음
                    cnt++;
                } else if (BULBS[0][i] == bulbs[0][i] && BULBS[1][i] == bulbs[1][i]) { // 둘 다 정답과 같을 경우
                    // 아무것도 안함
                } else { // 하나만 정답과 다를 경우
                    int baseIdx = bulbs[0][i] == BULBS[0][i] ? 0 : 1; // 기준 위치를 정답이랑 같은 것으로 하자.
                    int oppnIdx = (baseIdx == 0) ? 1 : 0;    // 기준 위치의 아래 또는 위
                    
                    int nextIdx = i + 1;
                    bulbs[oppnIdx][i] = !bulbs[oppnIdx][i];
                    bulbs[oppnIdx][nextIdx] = !bulbs[oppnIdx][nextIdx];
                    cnt++;
                }
                i++;
            }

            if (i == N - 1) {
                // 둘다 다를 때만 cnt 증가
                if (BULBS[0][i] != bulbs[0][i] && BULBS[1][i] != bulbs[1][i]) {
                    cnt++;
                    bulbs[0][i] = !bulbs[0][i];
                    bulbs[1][i] = !bulbs[1][i];
                }
            }

            cnt = (BULBS[0][i] == bulbs[0][i] && BULBS[1][i] == bulbs[1][i]) ? cnt : -1;

            w.write(Integer.toString(cnt));
            w.write("\n");
        }

        r.close();
        w.close();
    }

}
