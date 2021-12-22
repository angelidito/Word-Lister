package M;

import java.util.ArrayList;

import M.Word.LANG;

/**
 * Lists of words.
 * This way, a mess cannot be made by removing or adding wrong data.
 */
public class WordLists {

	private static ArrayList<Word> ESList = new ArrayList<Word>();
	private static ArrayList<Word> ENList = new ArrayList<Word>();

	public static void add(int index, Word word) {
		getList(word.getLang()).add(word);
	}

	public static ArrayList<Word> getList(LANG lang) {
		switch (lang) {
			case ES:
				return ESList;
			case EN:
				return ENList;
			default:
				return null;
		}

	}
}
