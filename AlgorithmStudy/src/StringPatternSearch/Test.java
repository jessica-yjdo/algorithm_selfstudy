package StringPatternSearch;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
//		String text = "Thisisthetest";
//		String pattern = "test";
		String text = "ababacabacaabacaaba";
		String pattern = "abacaaba";

		RobinKarp rk = new RobinKarp();
		System.out.println("[RobinKarp] START ----------------------------------------");
		ArrayList<Integer> resultRK = rk.findPattern(text, pattern);
		System.out.println("[RobinKarp] Total number of matched string is " + resultRK.size());
		
		System.out.println();
		KMP kmp = new KMP();
		System.out.println("[KMP] START ----------------------------------------------");
		ArrayList<Integer> resultKMP = kmp.findPattern(text, pattern);
		System.out.println("[KMP]Total number of matched string is " + resultKMP.size());
		
		System.out.println();
		BoyerMoore bm = new BoyerMoore();
		System.out.println("[BoyerMoore] START ----------------------------------------");
		ArrayList<Integer> resultBM = bm.findPattern(text, pattern);
		System.out.println("[BoyerMoore]Total number of matched string is " + resultBM.size());	
	}
}
