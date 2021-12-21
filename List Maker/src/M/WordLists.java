package M;

import java.util.ArrayList;

import M.Word.LANG;

/**
 * TODO: in order to meke the lists (almost) read only, they shold be at their
 * own class.
 */
public class WordLists {

	private static ArrayList<Word> ESList;
	private static ArrayList<Word> ENList;

	public static void add(int index, Word word) {
		getList(word.getLang()).add(word)
	}

	public ArrayList<Word> getList(LANG lang) {
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
