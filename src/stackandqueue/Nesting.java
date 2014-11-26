/*
	A string S consisting of N characters is called properly nested if:
	S is empty;
	S has the form "(U)" where U is a properly nested string;
	S has the form "VW" where V and W are properly nested strings.
	For example, string "(()(())())" is properly nested but string "())" isn't.
	Write a function:
	class Solution { public int solution(String S); }
	that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.
	For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.
	Assume that:
	N is an integer within the range [0..1,000,000];
	string S consists only of the characters "(" and/or ")".
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
 */

//SCORE: 100/100
package stackandqueue;

import java.util.Stack;

public class Nesting {
	
	public static void main (String[] args) {
		String S= "((()()))";
		System.out.println(solution(S));
	}
	
	public static int solution(String S) {
		Stack<Character> chars = new Stack<Character>();
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == '(') {
				chars.push(S.charAt(i));
			} else if (S.charAt(i) == ')' && chars.size()>0) {
				chars.pop();
			} else return 0;
		}
		return chars.size()==0?1:0;
	}
}
