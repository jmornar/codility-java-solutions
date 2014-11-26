/*
	A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
	A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
	You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.
	Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.
	For example, consider an integer N = 26 and arrays P, Q such that:
	    P[0] = 1    Q[0] = 26
	    P[1] = 4    Q[1] = 10
	    P[2] = 16   Q[2] = 20
	The number of semiprimes within each of these ranges is as follows:
	(1, 26) is 10,
	(4, 10) is 4,
	(16, 20) is 0.
	Write a function:
	class Solution { public int[] solution(int N, int[] P, int[] Q); }
	that, given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.
	For example, given an integer N = 26 and arrays P, Q such that:
	    P[0] = 1    Q[0] = 26
	    P[1] = 4    Q[1] = 10
	    P[2] = 16   Q[2] = 20
	the function should return the values [10, 4, 0], as explained above.
	Assume that:
	N is an integer within the range [1..50,000];
	M is an integer within the range [1..30,000];
	each element of arrays P, Q is an integer within the range [1..N];
	P[i] ≤ Q[i].
	Complexity:
	expected worst-case time complexity is O(N*log(log(N))+M);
	expected worst-case space complexity is O(N+M), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//Score: 100/100
package sieveoferastothenes;

import java.util.Arrays;

public class CountSemiprimes {
	
	public static void main (String[] args) {
		int[] A = new int[] {1,4,16};
		int[] B = new int[]{26,10,20};
		int N = 26;
		System.out.println(Arrays.toString(solution(A,B,N)));
		
	}
	
	public static int[] solution(int[] A, int[] B, int N) {
		int[] factArray = factorizationArray(N);
		int[] semiPrimes = new int[factArray.length];
		for (int i = 0; i < semiPrimes.length; i++) {
			if (factArray[i] != 0 && factArray[i/factArray[i]] == 0)
				semiPrimes[i] = 1;
		}
		int[] semiPrimesPreSum = prefixSum(semiPrimes);
		int[] res = new int[A.length];
		for (int i = 0; i < B.length; i++) {
			res[i] = semiPrimesPreSum[B[i]] - semiPrimesPreSum[A[i]-1];
		}
		return res;
	}

	//preparing array for factorization (array with primes)
	public static int[] factorizationArray(int n) {
		int[] F = new int[n+1];
		for (int i = 2; i*i <= n; i++) {
			if (F[i] == 0) {
				for (int k = i*i; k<=n; k+=i) {
					if (F[k] == 0)
						F[k] = i;
				}
			}
		}
		return F;
	}
	public static int[] prefixSum(int[] A) {
		int[] prefSum = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			if (i==0)
				prefSum[i] =  A[i];
			else 
				prefSum[i] = prefSum[i-1] + A[i];
		}
		return prefSum;
	}
}
