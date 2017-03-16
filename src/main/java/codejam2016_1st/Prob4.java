package codejam2016_1st;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prob4 {
	static class City {
		int num; // TODO: String 타입으로.. Integer.parseInt하는 비용을 줄일수 있음
		int extension;
		//Set<Road> roads = new HashSet<>();
		List<Road> roads = new ArrayList<>();

		public City(int num) {
			this.num = num;
		}
	}

	static class Road {
		City city1;
		City city2;
		int length;

		public Road(City city1, City city2, int l) {
			this.city1 = city1;
			this.city2 = city2;
			this.length = l;

			city1.roads.add(this);
			city2.roads.add(this);

			String key = (city1.num < city2.num) ?
					Integer.toString(city1.num) + " " + city2.num
					: Integer.toString(city2.num) + " " + city1.num;
		}
	}

	@Test
	public void test() throws Exception {
//		memo.clear();

		City c1 = new City(1);
		City c2 = new City(2);
		City c3 = new City(3);
		City c4 = new City(4);
		City c5 = new City(5);

		Road r1 = new Road(c1, c2, 1);
		Road r2 = new Road(c1, c3, 5);
		Road r3 = new Road(c2, c4, 2);
		Road r4 = new Road(c2, c5, 2);

		assertEquals(3, shortest(c1, c4));
		assertEquals(3, shortest(c5, c1));

		new Road(c1, c3, 2);
		new Road(c1, c4, 1);
		new Road(c1, c5, 5);

		assertEquals(1, shortest(c1, c4));
		assertEquals(3, shortest(c5, c1));
	}

/*
	@Test
	public void test2() throws Exception {
//		memo.clear();

		City c1 = new City(1);
		City c2 = new City(2);
		City c3 = new City(3);
		City c4 = new City(4);
		City c5 = new City(5);
		City c6 = new City(6);

		Road r1 = new Road(c1, c2, 1);
		Road r2 = new Road(c1, c3, 5);
		Road r3 = new Road(c2, c4, 2);
		Road r4 = new Road(c2, c5, 2);
		Road r5 = new Road(c4, c6, 5);

		new Road(c2, c3, 3);
		new Road(c1, c2, 2);
		new Road(c3, c6, 4);

		assertEquals(true, c2.roads.contains(r3));
		assertEquals(true, c4.roads.contains(r3));

		assertEquals(7, shortest(c2, c6));
	}

	@Test
	public void testMemo() throws Exception {
		memo.clear();
		memo.put("1 2", 1);
		memo.put("1 2", 2);
		assertEquals(2, (int)memo.get("1 2"));
	}
*/

	static int min = Integer.MAX_VALUE;
	static List<Integer> results = new ArrayList<>();

	static int shortest(City from, City to) {
	    min = Integer.MAX_VALUE;
		List<City> path = new ArrayList<>(); // TODO: hashset으로 변경
		shortest(path, from, to);
		return min;
	}

	static int shortest(List<City> path, City from, City to) {
	    if (path.contains(from)) {
	        return -1;
	    }
	    
	    path.add(from);
	    //printPath(path);

	    // 마지막에 다다랐을 때
	    if (from.num == to.num) {
	        return getShortestLength(path);
	    }

	    int ret = 0;
		// 다음 도시로 이동
		City next = null;
		for (Road r : from.roads) {
			// 다음으로 이동할 도시 선정
			if (r.city1.num == from.num) next = r.city2;
			if (r.city2.num == from.num) next = r.city1;

			ret = shortest(path, next, to);
			
			if (ret < 0) {
			    continue;
			}

			if (path.size() > 1) {
			    path.remove(path.size() - 1);
			    //printPath(path);
			}
		}

		if (path.size() > 1) {
		    path.remove(path.size() - 1);
		    //printPath(path);
		}

		return ret;
	}
	
	static void printPath(List<City> path) {
	    for (City c : path) {
	        System.out.print(c.num + " - ");
	    }
	    System.out.println();
	}

	private static int getShortestLength(List<City> path) {
	    // TODO 현재 path 값을 다 더한 후 res와 비교해서 min값을 반환
	    int sum = 0;
	    int size = path.size();
	    for (int i = 0; i < size - 1; i++) {
	        sum += getShortestToNext(path.get(i), path.get(i + 1));
	    }
	    min = Math.min(min, sum);
        return min;
    }

	static HashMap<String, Integer> memo = new HashMap<>();
	private static int getShortestToNext(City from, City next) {
	    String key = from.num + "" + next.num;
	    Integer val = memo.get(key);
	    if (val != null) {
	        return val;
	    }

		int ret = Integer.MAX_VALUE;
		for (Road r : from.roads) {
			if (r.city1.num == next.num || r.city2.num == next.num) {
				ret = Math.min(ret, r.length);
			}
		}
		memo.put(key, ret);  
		return ret;
	}

	public static void main(String[] args) throws IOException {
//		String inFile = "sample.in";
		String inFile = "problem_4_Set1.in";
		// String inFile = args[0];
		String outFile = (inFile.split(".in"))[0] + ".out";

		BufferedReader r = new BufferedReader(new FileReader(inFile));
		BufferedWriter w = new BufferedWriter(new FileWriter(outFile));
		int T = Integer.parseInt(r.readLine()); // test case

		String str[];

		// make city map
		for (int t = 0; t < T; t++) {
		    memo = new HashMap<>();
		    
		    str = r.readLine().split(" ");
		    int N = Integer.parseInt(str[0]);
		    int Q = Integer.parseInt(str[1]);
		    
		    List<City> cities = new ArrayList<>(N + 1); // 0번 인덱스는 사용 안함.
			cities.add(new City(0)); // 빈칸 채우기

			City city1 = new City(1);
			cities.add(city1);
			
			if (N == 2) {
			    str = r.readLine().split(" ");
			    int before = Integer.parseInt(str[1]);
			    int after = Integer.parseInt(r.readLine());
			    int shortestSumBefore = 0;
			    int shortestSumAfter = 0;

			    for (int i = 0; i < Q; i++) {
                    str = r.readLine().split(" ");
                    if (str[0] != str[1]) {
                        shortestSumBefore += before;
                        shortestSumAfter += after;
                    }
			    }
			    w.write(shortestSumBefore + " " + shortestSumAfter);
			    w.write("\n");
			} else {
			    for (int i = 2; i < N + 1; i++) { // N - 1번 루프
			        City c = new City(i);
			        cities.add(i, c);
			        str = r.readLine().split(" ");
			        City linkedCity = cities.get(Integer.parseInt(str[0]));
			        int length = Integer.parseInt(str[1]);
			        new Road(c, linkedCity, length);
			    }
    
    			// M 계산
    			List<City> citiesWithOnlyOneRoad = new ArrayList<>();
    			int size = cities.size();
    			for (int i = 2; i < size; i++) {
    			    City c = cities.get(i);
    				if (c.roads.size() == 1) {
    				    String line = r.readLine();
    					c.extension = (Integer.parseInt(line));
    					citiesWithOnlyOneRoad.add(c);
    				}
    			}

    			int shortestSumBefore = 0;
    			// 질문 미리 읽어오기
    			int[] from = new int[Q];
    			int[] to = new int[Q];
    			for (int i = 0; i < Q; i++) {
    				str = r.readLine().split(" ");
    				from[i] = Integer.parseInt(str[0]);
    				to[i] = Integer.parseInt(str[1]);
    
    				// 현재 최단거리 구하기
    				int res = shortest(cities.get(from[i]), cities.get(to[i]));
    				shortestSumBefore += res;
    			}
//    			System.out.println(shortestSumBefore);
    
    			// 1번과 연결
    			for (City c : citiesWithOnlyOneRoad) {
    				new Road(city1, c, c.extension);
    			}
    
    			int shortestSumAfter = 0;
    			// 이후 최단거리 구하기
    			for (int i = 0; i < Q; i++) {
    				int res = shortest(cities.get(from[i]), cities.get(to[i]));
    				shortestSumAfter += res;
    			}
    			//System.out.println(shortestSumAfter);
    			w.write(Integer.toString(shortestSumBefore) + " "
    			        + Integer.toString(shortestSumAfter));
    			w.write("\n");
			}
		}

		r.close();
		w.close();
	}
}
