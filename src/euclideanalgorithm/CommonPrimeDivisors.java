/*
	A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
	A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20.
	You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.
	For example, given:
	N = 15 and M = 75, the prime divisors are the same: {3, 5};
	N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
	N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
	Write a function:
	class Solution { public int solution(int[] A, int[] B); }
	that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.
	For example, given:
	    A[0] = 15   B[0] = 75
	    A[1] = 10   B[1] = 30
	    A[2] = 3    B[2] = 5
	the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
	Assume that:
	Z is an integer within the range [1..6,000];
	each element of arrays A, B is an integer within the range [1..2147483647].
	Complexity:
	expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package euclideanalgorithm;

public class CommonPrimeDivisors {

	public static void main(String[] args) {
		int[] A = new int[]{15,10,3};
		int[] B = new int[]{75,30,5};
		System.out.println(solution(A,B));
		
	}
	
	private static int solution(int[] A, int[] B) {
		int res=0;
		for (int i = 0; i < A.length; i++) {
			int x=A[i];
			int y=B[i];
			int gcd = gcd(x, y, 1);
			int gcdTmp=0;
			while(x!=1) {
				gcdTmp = gcd(x, gcd, 1);
				if(gcdTmp==1)
					break;
				x /= gcdTmp;
			}
			if (x!=1)
				continue;
			
			while(y!=1) {
				gcdTmp = gcd(y,gcd,1);
				if (gcdTmp==1)
					break;
				y /= gcdTmp;
			}
			if (y!=1)
				continue;
			res++;
		}
		return res;
	}
	
	private static int gcd(int a, int b, int res) {
		if (a==b)
			return res*a;
		else if (a%2==0 && b%2==0)
			return gcd (a/2, b/2, res*2);
		else if (a%2==0)
			return gcd (a/2, b, res);
		else if (b%2==0)
			return gcd (a, b/2, res);
		else if (a>b)
			return gcd (a - b, b, res);
		else 
			return gcd (a, b - a, res);		
	}
}
