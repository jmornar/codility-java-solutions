/*
	You are given a non-empty zero-indexed array A consisting of N integers.
	For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
	For example, consider integer N = 5 and array A such that:
	    A[0] = 3
	    A[1] = 1
	    A[2] = 2
	    A[3] = 3
	    A[4] = 6
	For the following elements:
	A[0] = 3, the non-divisors are: 2, 6,
	A[1] = 1, the non-divisors are: 3, 2, 3, 6,
	A[2] = 2, the non-divisors are: 3, 3, 6,
	A[3] = 3, the non-divisors are: 2, 6,
	A[6] = 6, there aren't any non-divisors.
	Write a function:
	class Solution { public int[] solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
	The sequence should be returned as:
	a structure Results (in C), or
	a vector of integers (in C++), or
	a record Results (in Pascal), or
	an array of integers (in any other programming language).
	For example, given:
	    A[0] = 3
	    A[1] = 1
	    A[2] = 2
	    A[3] = 3
	    A[4] = 6
	the function should return [2, 4, 3, 2, 0], as explained above.
	Assume that:
	N is an integer within the range [1..50,000];
	each element of array A is an integer within the range [1..2 * N].
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package sieveoferastothenes;

import java.util.Arrays;

public class CountNonDivisible {
	
	public static void main (String[] args) {
		int[] A = new int[] {3,1,2,3,6};
		System.out.println(Arrays.toString(solution(A)));
	}
	
	public static int[] solution(int[] A) {
	    int[][] D = new int[2*A.length + 1][2];
	    int[] res = new int[A.length];
	    for (int i = 0; i < A.length; i++) {
			D[A[i]][0]++;
			D[A[i]][1] = -1;
		}
	    for (int i = 0; i < A.length; i++) {
	    	if(D[A[i]][1]==-1) {
	    		D[A[i]][1]=0;
	    		for (int j = 1; j*j <= A[i]; j++) {
					if(A[i] % j == 0) {
						D[A[i]][1]+= D[j][0];
						if (A[i]/j != j)
							D[A[i]][1]+= D[A[i]/j][0];
					}
					
				}
			}
		}
	    for (int i = 0; i < A.length; i++) {
			res[i] = A.length - D[A[i]][1]; 
		}
	    return res;
	} 	
}
