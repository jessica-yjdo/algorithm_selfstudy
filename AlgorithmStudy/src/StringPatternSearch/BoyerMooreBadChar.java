package StringPatternSearch;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Boyer-Moore Algorithm
 * 
 * @author jessica.do
 *
 */
public class BoyerMooreBadChar {
	private HashMap<Character, Integer> badMatchTable;
	
	public BoyerMooreBadChar() {
		this.badMatchTable = new HashMap<Character, Integer>();
	}

	public ArrayList<Integer> findPattern(String text, String pattern) {
		// 1. Check input validity
		if (text.isEmpty() || pattern.isEmpty()) {
			return null;
		}

		// 2. Initialize local variables
		int lengthOfText = text.length();
		int lengthOfPattern = pattern.length();
		int numOfSkips = 0;
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		// 3. Create a bad matched table
		makeBadMatchTable(pattern);
		
		// 4. Compare text and pattern - if not matched, skips according to the table
		for (int i = 0; i <= (lengthOfText-lengthOfPattern); i+=numOfSkips) {
			numOfSkips = 0;
			for (int j = lengthOfPattern-1; j >= 0; j--) {
				if (pattern.charAt(j) != text.charAt(i+j)) {
					if (this.badMatchTable.get(text.charAt(i+j)) != null) {
						numOfSkips = this.badMatchTable.get(text.charAt(i+j));
					} else {
						// If unmatched char is not in the table, then skip as much as pattern length
						numOfSkips = lengthOfPattern;
					}
					//System.out.println("i[" + i + "], numOfSkips = " + numOfSkips);
					break;
				} else {
					if (j == 0) {
						result.add(i);
						System.out.println("Found matched word at " + (i+1) + "th.");
						numOfSkips = 1;
					}
				}
			}
		}
		return result;
	}
	private void makeBadMatchTable(String pattern) {
		int len = pattern.length();
		for (int i = 0; i < len; i++) {
			char actualCharacter = pattern.charAt(i);
			int maxShift = Math.max(1, len - i - 1);
			this.badMatchTable.put(actualCharacter, maxShift);
		}
		printHashMap(badMatchTable);
	}
	
	private void printHashMap(HashMap<Character, Integer> map) {
		System.out.print("[Table] ");
		for(Character key:map.keySet()) {
			System.out.print("[" + key + "]=" + map.get(key) + "/");
		}
		System.out.println();
	}
}
