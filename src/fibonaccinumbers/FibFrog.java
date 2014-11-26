/*
	The Fibonacci sequence is defined using the following recursive formula:
	    F(0) = 0
	    F(1) = 1
	    F(M) = F(M - 1) + F(M - 2) if M >= 2
	A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
	The leaves on the river are represented in a zero-indexed array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
	0 represents a position without a leaf;
	1 represents a position containing a leaf.
	The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
	For example, consider array A such that:
	    A[0] = 0
	    A[1] = 0
	    A[2] = 0
	    A[3] = 1
	    A[4] = 1
	    A[5] = 0
	    A[6] = 1
	    A[7] = 0
	    A[8] = 0
	    A[9] = 0
	    A[10] = 0
	The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.
	For example, given:
	    A[0] = 0
	    A[1] = 0
	    A[2] = 0
	    A[3] = 1
	    A[4] = 1
	    A[5] = 0
	    A[6] = 1
	    A[7] = 0
	    A[8] = 0
	    A[9] = 0
	    A[10] = 0
	the function should return 3, as explained above.
	Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer that can have one of the following values: 0, 1.
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package fibonaccinumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FibFrog {
	int number = 0;
	public static void main(String[] args) {
		int[] A = new int[]{0,0,0,1,1,0,1,0,0,0,0};
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		List<Integer> fibs = getFibonaciUpTo(A.length+1);
		boolean[] visited = new boolean[A.length];
		Stack<Jump> stack= new Stack<Jump>();
		stack.push(new Jump(-1,0));
		while(!stack.isEmpty()) {
			Jump currJump = stack.firstElement();
			stack.remove(0);
			int i = 0;
			while(currJump.pos + fibs.get(i)<= A.length) {
				if (currJump.pos + fibs.get(i) == A.length)
					return currJump.jumps + 1;
				if(A[fibs.get(i)+currJump.pos] == 1 && !visited[currJump.pos + fibs.get(i)]) {
					stack.push(new Jump(fibs.get(i)+currJump.pos, currJump.jumps+1));
					visited[fibs.get(i)+currJump.pos] = true;
				}
				i++;
			}
		}
		return -1;
	}
	
    public static List<Integer> getFibonaciUpTo(int n) {
        List<Integer> fibs = new ArrayList<Integer>();
        fibs.add(0);
        fibs.add(1);
        int i =2;
        while(fibs.get(fibs.size()-1) <= n){
        	fibs.add(fibs.get(i-1)+fibs.get(i-2));
        	i++;
        }
        fibs.remove(0);
        fibs.remove(1);
        return fibs;
    }
    
    public static class Jump {
    	int pos;
    	int jumps;
    	Jump(int p, int j) {
    		pos = p;
    		jumps = j;
    	}
    }
		
}
