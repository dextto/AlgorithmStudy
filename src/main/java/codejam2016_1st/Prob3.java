package codejam2016_1st;



import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;




public class Prob3 {
    @Test
    public void test() throws Exception {
//    	assertEquals(1, count("aa"));
//    	assertEquals(0, count("ab"));
        assertEquals(4, count("appall"));
        assertEquals(12, count("mississippi"));
    }


    static int[] arr; // 실제 메모리는 97~122번 인덱스만 사용

    public static void main(String[] args) throws IOException {
        String inFile = args[0];
        String outFile = (inFile.split(".in"))[0] + ".out";

        BufferedReader r = new BufferedReader(new FileReader(inFile));
        BufferedWriter w = new BufferedWriter(new FileWriter(outFile));
        r.readLine(); // test case

        String line;
        while ((line = r.readLine()) != null) {
            int ans = count(line);
            w.write(Integer.toString(ans));
            w.write("\n");
        }

        r.close();
        w.close();
    }


    private static int count(String line) {
//      System.out.println(line);
    	int length = line.length();
    	if (length < 2) return 0;

        int cnt = 0;
        int subLen = 2;
        while (subLen <= length) {
        	arr = new int[123];
        	
            // TODO: 더 개선할 방법
        	//       처음 subLen에서 loop를 돌 때 홀수 영문의 개수를 세어 두고,  
            //       앞/뒤에 계산된 영문이 각각 짝이 되면 홀수 개수를 갱신.
            //       갱신된 홀수 값이 0인지 체크
        	for (int i = 0; i < subLen; i++) {
        		int idx = line.charAt(i);
                arr[idx]++;
        	}

        	boolean isEven = true;
            for (int i = 'a'; i <= 'z'; i++) {
                if (arr[i] % 2 != 0) {
                    isEven = false;
                    break;
                }
            }
            if (isEven) { 
                cnt++;
//                System.out.println(line.substring(0, subLen));
            }

            int prevStartChar;
        	int start = 1;
        	while((start + subLen) <= length) {
        		// 한 칸씩 이동하면서,

        		// 앞쪽 char에 해당하는 값을 하나 감소
        		prevStartChar = line.charAt(start - 1);
        		if (arr[prevStartChar] > 0) {
        			 --arr[prevStartChar];
        		}

        		// 뒤쪽 추가된 char에 해당하는 값을 하나 증가
        		int endChar = line.charAt(start + subLen - 1);
        		arr[endChar]++;

        		if (isEven) { // 이전 단어가 짝수 단어였다면
        			isEven = (arr[prevStartChar] % 2 == 0) && (arr[endChar] % 2 == 0);
        		} else { // 홀수면
        			// 전체 쭉 돌면서 다시 체크
        			isEven = true;
                    for (int ie = 'a'; ie <= 'z'; ie++) {
                        if (arr[ie] % 2 != 0) {
                            isEven = false;
                            break;
                        }
                    }
        		}

        		if (isEven) {
        		    cnt++;
//        		    System.out.println(line.substring(start, start + subLen));
        		}

        		start++;
        	}

        	subLen += 2;
        }

        return cnt;
    }


}
