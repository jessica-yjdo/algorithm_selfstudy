package StringPatternSearch;

import java.util.ArrayList;

/**
 * KMP(Knuth-Morris-Pratt) Algorithm
 * Time complexity = O(N + M) : N:text length, M:pattern length
 * @author jessica.do
 *
 */
public class KMP {
	private ArrayList<Integer> badMatchTable;
	
	public KMP() {
		this.badMatchTable = new ArrayList<Integer>();
	}

	public ArrayList<Integer> findPattern(String text, String pattern) {
		// 1. Check input validity
		if (text.isEmpty() || pattern.isEmpty()) {
			return null;
		}

		// 2. Initialize local variables
		int lengthOfText = text.length();
		int lengthOfPattern = pattern.length();
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		// 3. Create a bad matched table
		makeBadMatchTable(pattern);
		
		// 4. Compare text and pattern by each pointer
		int pPointer=0;  // patternPointer
		for (int tPointer = 0; tPointer < lengthOfText; tPointer++) {  // textPointer
			// 4-1. If pattern is not matched with text, then patternPointer is changed accordin to the table
			while(pPointer > 0 && text.charAt(tPointer) != pattern.charAt(pPointer)) {
				pPointer = this.badMatchTable.get(pPointer-1);
			}

			// 4-2. If pattern is matched with text, keep going or done
			if (text.charAt(tPointer) == pattern.charAt(pPointer)) {
				if (pPointer == lengthOfPattern-1) { // pPointer goes to the end.
					System.out.println("Found matched word at " + (tPointer - lengthOfPattern + 2) + "th.");
					result.add(pPointer);
					pPointer = this.badMatchTable.get(pPointer);
				} else {
					pPointer = pPointer + 1; // just matched! Increase pPointer.
				}
			}
		}
		return result;
	}

	private void makeBadMatchTable(String pattern) {
		if (pattern.isEmpty()) {
			System.err.println("Input string is empty.");
			return;
		}
		
		int lengthOfPattern = pattern.length();
		this.badMatchTable.add(0, 0);

		int prefixPointer = 0;
		for (int suffixPointer = 1; suffixPointer < lengthOfPattern; suffixPointer++) {
			while(prefixPointer > 0 && pattern.charAt(suffixPointer) != pattern.charAt(prefixPointer)) {
				prefixPointer = this.badMatchTable.get(prefixPointer-1);
			}
			
			if (pattern.charAt(suffixPointer) == pattern.charAt(prefixPointer)) {
				prefixPointer = prefixPointer + 1;
				this.badMatchTable.add(suffixPointer, prefixPointer);
			} else {
				this.badMatchTable.add(suffixPointer, 0);
			}
		}
		printArrayList(this.badMatchTable);
	}
	
	private void printArrayList(ArrayList<Integer> list) {
		System.out.print("[Table] ");
		for (Integer i:list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
