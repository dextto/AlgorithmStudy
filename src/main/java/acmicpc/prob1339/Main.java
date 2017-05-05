package acmicpc.prob1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader r;
    static Map map; // key : 문자열의 길이, val: 같은 길이를 같은 문자열들의 배열
    static int num = 9;
    static int[] nums = new int[26]; // A~Z가 가지는 숫자값. 인덱스 0는 A.

    public static void main(String[] args) throws IOException {
        r = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap<Integer, List<String>>();
        Arrays.fill(nums, -1);
        List<String> inputs = new ArrayList<>();
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n; i++) {
            String s = r.readLine();
            inputs.add(s);
            int key = s.length();
            List<String> list = (List<String>) map.get(key);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(s);
            map.put(key, list);
        }

        // 가장 길이가 긴 (key값이 큰) 순서대로 inputs를 정렬
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, (Integer o1, Integer o2) -> (o1 < o2) ? 1 : (o1 == o2 ? 0 : -1));
        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);
            int nextKey = (i < keys.size() - 1) ? keys.get(i + 1) : -1;
            List<String> list = (List<String>) map.get(key);
            String[] str = new String[list.size()];
            list.toArray(str);
            find(str, nextKey);

            if (nextKey != -1) {
                List<String> nextList = (List<String>) map.get(nextKey);
                for (String s : list) {
                    nextList.add(s.substring(key - nextKey));
                }
            }
        }

        //System.err.println(Arrays.toString(nums));
        // 결과 출력
        long sum = 0;
        for (String s : inputs) {
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'A';
                int num = nums[idx];
                sum += num * (long) Math.pow(10, s.length() - (i + 1));
            }
        }
        System.out.println(sum);

        r.close();
    }

    // str[]: 같은 길이를 같은 문자열의 배열
    // 문자열들의 가장 앞쪽의 문자의 값(9~0)을 구한다.
    static void find(String str[], int nextStrSize) {
        if (str == null || str[0].length() == 0) return;
        int l = str[0].length();
        if (l == nextStrSize) return;

        int point[] = new int[26];

        for (String s : str) {
            for (int i = 0; i < l; i++) {
                char c = s.charAt(i);
                if (nums[c - 'A'] > -1) continue;

                point[c - 'A'] += (l - i);
            }
        }

        int max = -1;
        for (int i = 0; i < point.length; i++) {
            if (point[i] >= max) {
                max = point[i];
            }
        }
        List<Integer> maxIndexes = new ArrayList<>();
        for (int i = 0; i < point.length; i++) {
            if (point[i] == max) {
                maxIndexes.add(i);
            }
        }

        for (Integer idx : maxIndexes) {
            if (nums[idx] == -1) {
                nums[idx] = num--;
            }
        }

        for (String s : str) {
            char c = s.charAt(0);
            if (nums[c - 'A'] == -1) {
                nums[c - 'A'] = num--;
            }
        }

        if (l > nextStrSize) {
            List<String> list = new ArrayList<>();
            Arrays.stream(str).forEach(s -> list.add(s.substring(1)));
            String[] s = new String[list.size()];
            list.toArray(s);
            find(s, nextStrSize);
        }
    }

}
