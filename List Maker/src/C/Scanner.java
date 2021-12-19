package C;

import M.Word.LANG;
import V.ErrorDisplay;

public class Scanner {
	private static java.util.Scanner sc = null;

	private static boolean isClosed = true;

	public static void newInstance() {
		if (!isClosed)
			sc.close();
		sc = new java.util.Scanner(System.in);
	}

	/**
	 * Closes the scanner.
	 */
	public static void close() {
		if (!isClosed)
			sc.close();
	}

	/**
	 * Reads an input and returns the recognized languaje as LANG enum.
	 * 
	 * Returns LANG.UNKNOWN if can't recognize it.
	 * 
	 * @return LANG enum: ES, EN or UNKNOWN
	 */
	public static LANG readLang() {
		String lang = readLine();
		if (isSpanish(lang))
			return LANG.ES;
		if (isEnglish(lang))
			return LANG.EN;
		return LANG.UNKNOWN;
	}

	private static boolean isSpanish(String lang) {
		return lang.compareToIgnoreCase("es") == 0 || lang.compareToIgnoreCase("español") == 0
				|| lang.compareToIgnoreCase("spanish") == 0;
	}

	private static boolean isEnglish(String lang) {
		return lang.compareToIgnoreCase("en") == 0 || lang.compareToIgnoreCase("english") == 0
				|| lang.compareToIgnoreCase("ingles") == 0 || lang.compareToIgnoreCase("inglés") == 0;
	}

	/**
	 * @return The next line of the input.
	 */
	public static String readLine() {
		String str = sc.nextLine();
		System.out.println();
		return str;

	}

	/**
	 * Reads and input and asks for another one until it can be parsed as an
	 * integer.
	 * 
	 * Displays a message if asking for another input.
	 * 
	 */
	public static int readInt() {
		int i;
		String s;

		s = readLine();
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			ErrorDisplay.notANumber();
			return readInt();
		}
		return i;
	}
}
