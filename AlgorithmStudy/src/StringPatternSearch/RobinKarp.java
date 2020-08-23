package StringPatternSearch;

import java.util.ArrayList;

/**
 * Robin-Karp Algorithm
 * Time complexity = O(N + M) : N:text length, M:pattern length
 * @author jessica.do
 *
 */
public class RobinKarp {
	public ArrayList<Integer> findPattern(String text, String pattern) {
		// 1. Check input validity
		if (text.isEmpty() || pattern.isEmpty()) {
			return null;
		}
		
		// 2. Initialize local variables
		int lengthOfText = text.length();
		int lengthOfPattern = pattern.length();
		int hashValueOfText = 0;
		int hashValueOfPattern = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();

		// 3. Get pattern hash value
		hashValueOfPattern = getHashValue(pattern, hashValueOfPattern, lengthOfPattern, 0);
		
		// 4. Get text hash values
		for (int i = 0; i <= (lengthOfText-lengthOfPattern); i++) {
			hashValueOfText = getHashValue(text, hashValueOfText, lengthOfPattern, i);
			
			// 5. Compare pattern hash value and text hash value
			if (hashValueOfText == hashValueOfPattern) {
				if (compare(text, pattern, i)) {
					result.add(i);
					System.out.println("Found matched word at " + (i+1) + "th.");
				}
			}	
		}
		return result;
	}
	
	private int getHashValue(String text, int hashOfText, int length, int start) {
		if (text.isEmpty() || length == 0) {
			System.err.println("Input string is empty.");
			return 0;
		}
		int hash = 0;
		int power = 0;
		if (start == 0) {
			power = 1;
			for (int i = 0; i < length; i++) {
				hash += text.charAt(length-1-i)*power;
				power *= 2;
			}
		} else {
			power = 1 << (length-1);
			hash = 2*(hashOfText - text.charAt(start-1)*power) + text.charAt(length-1+start);
		}
		return hash;
	}
	
	private boolean compare(String text, String pattern, int start) {
		if (text.isEmpty() || pattern.isEmpty()) {
			System.err.println("Either input is empty.");
			return false;
		}
		boolean equal = true;
		for (int i = 0; i < pattern.length(); i++) {
			if (text.charAt(start+i) != pattern.charAt(i)) {
				equal = false;
				break;
			}
		}
		return equal;
	}
}
