/*
	An integer M and a non-empty zero-indexed array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.
	A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.
	For example, consider integer M = 6 and array A such that:
	    A[0] = 3
	    A[1] = 4
	    A[2] = 5
	    A[3] = 5
	    A[4] = 2
	There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
	The goal is to calculate the number of distinct slices.
	Write a function:
	class Solution { public int solution(int M, int[] A); }
	that, given an integer M and a non-empty zero-indexed array A consisting of N integers, returns the number of distinct slices.
	If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
	For example, given integer M = 6 and array A such that:
	    A[0] = 3
	    A[1] = 4
	    A[2] = 5
	    A[3] = 5
	    A[4] = 2
	the function should return 9, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	M is an integer within the range [0..100,000];
	each element of array A is an integer within the range [0..M].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package caterpillar;

public class CountDistinctSlices {
	
	public static void main(String[] args) {
		int[] A = new int[]{3, 4, 5, 5, 2};
		int M = 6;
		System.out.println(solution(A, M));
	}
	
	public static int solution(int[] A, int M) {
		return caterpillarMethod(A, M);

	}
	
	public static int caterpillarMethod(int[] A, int M) {	
		int res = 0; 
		int front = 0; 
		int back =0;
		boolean[] seen = new boolean [M+1];
		while(front<A.length && back<A.length) {
			while(front<A.length && !seen[A[front]]) {
				res += front - back + 1;
				seen[A[front]] = true;
				front++;
			}
			while(front<A.length && back<A.length && A[back] != A[front]) {
				seen[A[back]] = false;
				back++;
			}
			seen[A[back]] = false;
			back++;
		}
		return Math.min(res, 1000000000);
	}
}
