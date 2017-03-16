package codejam2016_2nd;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;


public class Prob2 {
	private static BufferedReader r;
	private static BufferedWriter w;

	private static int xCitiesNum;
    private static int[] xCities;
    private static int[] xDistances;
    private static int[] xLeftCities; // excludes interchange
    private static int[] xLeftDistances; // excludes interchange
    private static int[] xRightCities; // includes interchange
    private static int[] xRightDistances; // includes interchange

    private static int interIdx;
    private static int yCitiesNum;
    private static int[] yCities;
    private static int[] yDistances;

    private static int[] cities;
    private static int[] distances;


    private static int[][] cache; // i 도시에서 j도시까지 갈 때 최소 기름 값

    public static void main(String[] args) throws IOException {
//        String inFile = args[0];
        String inFile = "Set2.in"; // for test
//        String inFile = "prob2_Set1_ex.in"; // for test
        String outFile = (inFile.split(".in"))[0] + ".out";

        r = new BufferedReader(new FileReader(inFile));
        w = new BufferedWriter(new FileWriter(outFile));
        int cases = Integer.parseInt(r.readLine()); // test case

        while(cases-- > 0) {
            makeXCities();
            makeYCities();
            makeXLeftRightCities();

            initCache();

            // 가로 도시들만 고려했을 때
            cities = xCities;
            distances = xDistances;
            int ans = minGas(0, cities.length - 1);

            // 삼거리에서 세로 도시를 갔다 오는 경로를 추가해서 계산해 본다.
            if (interIdx < xCities.length - 1) {
            	if (interIdx == 0) {
            	    for (int i = 0; i < yCities.length; i++) {
            	        List<Integer> cityList = new ArrayList<>();
            	        List<Integer> distList = new ArrayList<>();
            	        
            	        cityList.add(xCities[0]);

            	        for (int j = 0; j <= i; j++) {
            	            cityList.add(yCities[j]);
            	            distList.add(yDistances[j]);
            	        }
            	        for (int j = i; j >= 0; j--) {
            	            if (j < i) {
            	                cityList.add(yCities[j]);
            	            }
            	            distList.add(yDistances[j]);
            	        }
            	        cityList.addAll(Arrays.stream(xCities)
            	                  .boxed()
            	                  .collect(Collectors.toList()));
            	        distList.addAll(Arrays.stream(xDistances)
            	                .boxed()
            	                .collect(Collectors.toList()));

            	        cities = new int[cityList.size()];
                        distances = new int[distList.size()];
                        for(int k = 0; k < cityList.size(); k++)  {
                            cities[k] = cityList.get(k);
                            if (k < distList.size()) {
                                distances[k] = distList.get(k);
                            }
                        }
            	        
//                        System.out.println(Arrays.toString(cities));
//                        System.out.println(Arrays.toString(distances));

                        initCache();
            	        ans = Math.min(ans, minGas(0, cities.length - 1));
//            	        System.out.println();
            	    }
            	} else {
		            for (int y = 0; y < yCitiesNum; y++) {
		            	List<Integer> cityList = Arrays.stream(xLeftCities)
		            			.boxed()
		            			.collect(Collectors.toList());
		            	cityList.add(xCities[interIdx]);

		            	for (int j = 0; j <= y; j++) {
		            		cityList.add(yCities[j]);
		            	}
		            	for (int j = y - 1; j >= 0; j--) {
		            		cityList.add(yCities[j]);
		            	}

		            	List<Integer> distList = Arrays.stream(xLeftDistances)
		            			.boxed()
		            			.collect(Collectors.toList());
		            	distList.add(yDistances[0]);
		            	for (int j = 1; j <= y; j++) {
		            		distList.add(yDistances[j]);
		            	}
		            	for (int j = y; j >= 0; j--) {
		            		distList.add(yDistances[j]);
		            	}

		            	cityList.addAll(Arrays.stream(xRightCities).boxed().collect(Collectors.toList()));
		            	cities = new int[cityList.size()];
		            	distList.addAll(Arrays.stream(xRightDistances).boxed().collect(Collectors.toList()));
		            	distances = new int[distList.size()];

		            	for(int i = 0; i < cityList.size(); i++)  {
		            		cities[i] = cityList.get(i);
		            		if (i < distList.size()) {
		            			distances[i] = distList.get(i);
		            		}
		            	}

		            	// 새 경로로 계산
		            	initCache();
		            	ans = Math.min(ans, minGas(0, cities.length - 1));
		            }
            	}
            }

            System.out.println(ans);
            w.write(ans + "\n");
        }


        r.close();
        w.close();
    }

	private static void initCache() {
		int n = xCitiesNum + yCitiesNum * 2;
		cache = new int[n][n];
	}

	@Before
	public void setUp() {
		cache = new int[1000][1000];
	}

	@Test
	public void test() {
		cities = new int[] { 1, 50, 0 };
		distances = new int[] { 1, 1 };
		assertEquals(2, minGas(0, cities.length - 1));
	}
	@Test
	public void test2() {
		cities = new int[] { 2, 50, 1, 50, 0 };
		distances = new int[] { 100, 1, 1, 1 };
		assertEquals(204, minGas(0, cities.length - 1));
	}

	// c1번째 도시에서 c2 도시까지 갈 때, 가장 적은 기름 값
	// c1: c1 도시 인덱스 (include)
	// c2: c2 도시 인덱스 (exclude)
    private static int minGas(int c1, int c2) {
    	if (cache[c1][c2] != 0) {
    		return cache[c1][c2];
    	}

    	if (c1 == c2 - 1) {
    		return cache[c1][c2] = cities[c1] * distances[c1];
    	}

    	// c1의 기름값으로 c1부터 종착지까지 가는 경우
    	int ret = Integer.MAX_VALUE;
    	int sum = 0;
    	for (int i = c1; i < c2; i++) {
    		sum += cities[c1] * distances[i];
    	}
    	ret = (cache[c1][c2] = Math.min(ret, sum));

    	for (int inter = c1 + 1; inter < c2; inter++) {
    		ret = (cache[c1][c2] = Math.min(ret, minGas(c1, inter) + minGas(inter, c2)));
    	}
    	return ret;
	}


	private static void makeXCities() throws IOException {
        xCitiesNum = Integer.parseInt(r.readLine());
        xCities = new int[xCitiesNum];
        xDistances = new int[xCitiesNum - 1]; // 다음 도시까지의 거리
        String[] line = r.readLine().split("\\s");

        int idxCities = 0;
        int idxDistances = 0;
        for (int i = 0; i < line.length; i++) {
            if (i % 2 == 0) {
                xCities[idxCities] = Integer.parseInt(line[i]);
                idxCities++;
            } else {
                xDistances[idxDistances] = Integer.parseInt(line[i]);
                idxDistances++;
            }
        }
    }

    private static void makeXLeftRightCities() {
    	xLeftCities = Arrays.copyOf(xCities, interIdx);
    	xRightCities = Arrays.copyOfRange(xCities, interIdx, xCitiesNum);

    	xLeftDistances = Arrays.copyOf(xDistances, interIdx);
    	xRightDistances = Arrays.copyOfRange(xDistances, interIdx, xCitiesNum - 1);
	}

    private static void makeYCities() throws IOException {
        String[] line = r.readLine().split("\\s");
        interIdx = Integer.parseInt(line[0]) - 1;
        yCitiesNum = Integer.parseInt(line[1]);
        yCities = new int[yCitiesNum];
        yDistances = new int[yCitiesNum]; // 다음 도시까지의 거리

        line = r.readLine().split("\\s");
        int idxCities = 0;
        int idxDistances = 0;
        for (int i = 0; i < line.length; i++) {
            if (i % 2 == 0) {
                yDistances[idxDistances] = Integer.parseInt(line[i]);
                idxDistances++;
            } else {
                yCities[idxCities] = Integer.parseInt(line[i]);
                idxCities++;
            }
        }
    }
}
