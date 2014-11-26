/*
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.
	For example, given:
	  A[0] = 1    
	  A[1] = 3    
	  A[2] = 6
	  A[3] = 4    
	  A[4] = 1    
	  A[5] = 2
	the function should return 5.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package countingelements;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MissingInteger {
	public static void main(String[] args) {
		int[] A = new int[]{1,3,6,4,1,2};
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		ArrayList<Integer> numbers = IntStream.of(A).boxed().filter(x->x>0).sorted().distinct().collect(Collectors.toCollection(ArrayList<Integer>::new));
		if (numbers.size() == 0)
			return 1;
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) != i+1)
				return i+1;
		}
		return numbers.size()+1;
	}
}
