package PukyungUniv;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
    캘리포니아 주의 모든 도시(?)들의 이름과 위도 및 경도가 명시된 샘플 데이터파일이 주어진다. 이 파일에 등
    장하는 모든 도시들을 정점으로 하고 두 도시간의 거리가 10km 이하이면 에지로 연결하는 그래프의 인접리스
    트를 구성한 후, 사용자가 도시의 이름과 하나의 정수 k를 입력하면 그 도시로 부터 k 홉(hop) 이내에 있는 모
    든 도시들의 이름을 출력하는 프로그램을 작성하라. 여기서 k홉 이내라는 말은 그래프에서 두 노드를 연결하는
    경로의 길이(에지의 개수)가 k 이하라는 의미이다. 인접리스트를 구성하기 위해서는 도시의 이름에 일련번호
    를 부여해야 한다. 이 번호는 데이터 파일에 나오는 순서대로 부여하라. 또한 도시 이름에 부여된 일련 번호를
    빠르게 검색할 수 있어야 한다. 이를 위해서 이진검색트리를 이용하라. 즉 도시이름을 key로, 일련번호를
    value로 해서 <도시이름, 일련번호> 쌍을 이진검색트리에 저장한 후 도시이름으로 검색하면 일련번호를 알 수
    있다. 데이터 파일에서 필드들은 tab문자로 분리되어 있고, 첫 줄은 테이블 해더이므로 무시하면 된다. 동일한
    도시 이름이 중복해서 등장하기도 한다. 이 경우 이름 뒤에 다시 1, 2,... 식으로 번호를 추가하여 구분한다. 다
    음은 위도 경도 값으로 부터 거리를 계산해주는 함수이다. 도시간의 거리를 계산할 때 이 함수를 이용하라. 이
    함수가 반환하는 거리의 단위는 m이다.
*/

public class Ex12_2_CitiesOfCaliforniaWithinKthHop {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Alabama AL Distances.TXT"));
        String line;
        while((line = reader.readLine()) != null) {
            
        }
    }
    
    public static double calDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);  
              
        dist = dist * 60 * 1.1515; 
        dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
        dist = dist * 1000.0;      // 단위  km 에서 m 로 변환
        return dist;
    }
}
