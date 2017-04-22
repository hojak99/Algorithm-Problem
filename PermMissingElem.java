import java.util.*;

/*
 * �迭 A���� �迭 ũ�� + 1 ��ŭ�� ���� �߿��� ���� ���ڸ� ã��
 * A[] = {2,3,1,5} ��� : 4
 * 
 * Ǯ�� : 
 * �迭 ũ���� + 1 ��ŭ�� ���� �߿��� ���� ���ڸ� ã�� ���̱� ������ ���� ���������� �迭�� ũ��� 4�̴�.
 * �׷��ٸ� 4 + 1 = 5 ������ ���ڰ� �ִµ� �� �� ���� ���ڸ� ã�� ���̱� ������ boolean �迭�� �̿���
 * true/false �� �̿��� ���� ã�µ� boolean �� �迭 ũ�⸦ A.length+2 �� �� ������ 
 * 5������ ���ڰ� �ִµ� 5�� �ε����� �������� ���ϱ⿡ �ٽ� + 1 �� �ؼ� �迭 ũ�⸦ �����ߴ�.
 * 
 * */

public class PermMissingElem {
	public static void main(String[] args){
		int []A = {1,2,3,5};
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A){
		
		boolean result[] = new boolean[A.length+2];
		
		for(int i=0; i<A.length; ++i){
			result[A[i]] = true;
		}
		
		for(int i=1; i<result.length; i++){
			if(!result[i]){
				return i;
			}
		}
		
		return 0;
	}
}
