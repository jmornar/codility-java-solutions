/*
	A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
	For example, array A such that:
	    A[0] = 4
	    A[1] = 2
	    A[2] = 2
	    A[3] = 5
	    A[4] = 1
	    A[5] = 5
	    A[6] = 8
	contains the following example slices:
	slice (1, 2), whose average is (2 + 2) / 2 = 2;
	slice (3, 4), whose average is (5 + 1) / 2 = 3;
	slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
	The goal is to find the starting position of a slice whose average is minimal.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
	For example, given array A such that:
	    A[0] = 4
	    A[1] = 2
	    A[2] = 2
	    A[3] = 5
	    A[4] = 1
	    A[5] = 5
	    A[6] = 8
	the function should return 1, as explained above.
	Assume that:
	N is an integer within the range [2..100,000];
	each element of array A is an integer within the range [−10,000..10,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100 (both solutions)
package prefixsums;


public class MinAvgTwoSlice {
	public static void main(String[] args) {
		int[] A = new int[]{4,2,2,5,1,5,8}; 
		System.out.println(solution1(A));
	}

	//using caterpillar method (min value will be inside 2 or 3 element slices)
	public static int solution(int[] A) {
		int front=1;
		int back= 0;
		int res = 0;
		int curr = A[0]+A[1];
		double min = (double)curr/2;
		double tmpMin = min;
		
		while (true) {
			if (front - back == 1) {
				front++;
				if (front == A.length)
					return res;
				curr += A[front];
			}
			else {
				curr -= A[back];
				back++;
			}

			tmpMin=(double)curr/(front-back+1);
			if (tmpMin < min) {
				res = back;
				min = tmpMin;
			}
		}
	}
	
	public static int solution1(int[] A) {
		int res = 0;
		double min = (double)(A[0]+A[1])/2;
		
		for (int j = 0; j < A.length-2; j++) {
			if ((double)(A[j] + A[j+1]) / 2 < min){
				min = (double)(A[j] + A[j+1]) / 2;
				res=j;
			}
			if ((double)(A[j] + A[j+1] + A[j+2]) / 3 < min){
				min = (double)(A[j] + A[j+1] + A[j+2]) / 3;
				res=j;
			}
		}
		
		if ((double)(A[A.length-1] + A[A.length-2])/2 < min)
			return A.length - 2;
		return res;
	}

}
