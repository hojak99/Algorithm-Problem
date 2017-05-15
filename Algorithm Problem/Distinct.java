import java.util.*;

/*
 * N���� ��ҷ� �̷���� 0 �ε����� �迭 A�� �־����µ� �迭 A�� ������ �ִ� ������ ���� ���� ��ȯ�ؾ� �Ѵ�.
 * ���� ���,
 * A[] = {2, 1, 1, 2, 3, 1} �� ��
 * 3�� ��ȯ�ؾ� �Ѵ�. �ֳ��ϸ� �迭 A�� ������ ���� ���� 3�̱� �����ε� �� ������ ���� 1, 2, 3�̱� �����̴�.
 * 
 * Ǯ�� :
 * �� ������ �ص��� ������. �켱 HashSet�� ����ؼ� �迭 A�� �ε��� ������ ���� ������ �Ǵ��� ���ԵǾ� ���� ������
 * HashSet�� �߰��ϴ� ������ �ؼ� count �� ���� ���縦 �ذ��ߴ�.
 * */
public class Distinct {

	public static void main(String[] args) {
		int A[] = {2,1,1,2,3,1};
		System.out.println(solution(A));
	}
	
	public static int solution(int A[]) {
	
		Set<Integer> resultSet = new HashSet<>();
		
		int count = 0;
		
		for(int i=0; i<A.length; ++i){
			if(!resultSet.contains(A[i])){
				resultSet.add(A[i]);
				count+=1;
			}
		}
		
		return count;
	}

}
