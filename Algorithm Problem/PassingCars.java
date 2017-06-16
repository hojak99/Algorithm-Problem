﻿/*
 * N개의 요소로 이루어진 비어있징 않은 배열 A가 주어진다. 배열 A의 연이은 요소들은 도로에서 연속 된 자동차를 나타낸다.
 * 배열 A의 인덱스 값이 0과 1일 경우에 다음과 같다.
 * 0 : 동쪽으로 가는 자동차를 나타냄
 * 1 : 서쪽으로 가는 자동차를 나타냄
 * 
 * 목표는 지나가는 차량을 카운트 하는 것이다. P는 동쪽으로 가는 자동차, Q는 서쪽으로 가는 자동차를 나타내며
 * 0<=P<Q<N 인 한 쌍의 자동차 (P, Q)가 지나가고 있다.
 * 
 * 예를 들면
 * A[] = {0, 1, 0, 1, 1} 일 때
 * (A[0], A[1]), (A[0], A[3]), (A[0], A[4]), (A[2], A[3]), (A[2], A[4]) 
 * 즉 , (0, 1), (0, 3), (0, 4), (2, 3), (2, 4) 로 다섯 쌍의 자동차가 있다.
 * 
 * 만약 지나간 자동차의 상이 1,000,000,000 을 초과하면 -1 을 반환해야 한다.
 * 
 * 
 * 풀이 :
 * 솔직히 아직 나도 잘 이해가 가지 않는 문제이다. O(N)의 시간 복잡도를 가지며 for문을 한 개 밖에 쓰지 않는 알고리즘을 생각하지 못해서
 * 결국 구글링 해서 찾았다. 
 * */

public class PassingCars {

	public static void main(String[] args) {
		int A[] = {0,1,0,1,1};
		System.out.println(solution(A));
	}
	
	public static int solution(int []A){
		
		int count = 0;
		int result = 0;
		
		for(int i=0; i<A.length; ++i){
			if(A[i] == 0){
				count += 1;
			}
			
			if(A[i] == 1){
				result += count;
			}
			
			if(result > 1000000000){
				return -1;
			}
		}

		return result;
	}
}