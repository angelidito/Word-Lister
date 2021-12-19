package M;

import java.net.URI;

public class URIMaker {

	private static final String rae = "https://dle.rae.es/angel%s";
	private static final String wordrefereceEn = "https://www.wordreference.com/definition/%s";
	private static final String wordrefereceEs = "https://www.wordreference.com/definicion/%s";
	private static final String wordrefereceEnToEs = "https://www.wordreference.com/es/translation.asp?tranword=%s";
	private static final String wordrefereceEsToEn = "https://www.wordreference.com/es/en/translation.asp?spen=%s";
	private static final String collinsEsToEn = "https://www.collinsdictionary.com/dictionary/english-spanish/%s";
	private static final String collinsEnToEs = "https://www.collinsdictionary.com/dictionary/spanish-english/%s";
	private static final String collinsEn = "https://www.collinsdictionary.com/dictionary/english/%s";
	private static final String oxfordEn = "https://www.oxfordlearnersdictionaries.com/definition/english/%s";

	public enum DICT {
		WORDREFERENCE, COLLINS, RAE, OXFORD
	}

	/**
	 * Returns the uri of a web page with the definition of the word.
	 * 
	 * @param word       Word to look up.
	 * @param dictionary Dictionary desired to check into
	 * @return an URI or null if there is no option to check the word with the
	 *         specified dictionary
	 */
	public static URI lookUp(Word word, DICT dictionary) {
		return lookUpOrTranslate(word, dictionary, false);
	}

	/**
	 * Returns the uri of a web page with the translation of the word.
	 * 
	 * @param word       Word to look up.
	 * @param dictionary Dictionary desired to check into
	 * @return an URI or null if there is no option to translate the word with the
	 *         specified dictionary
	 */
	public static URI translate(Word word, DICT dictionary) {
		return lookUpOrTranslate(word, dictionary, true);
	}

	/**
	 * Returns the uri of a web page with the definition or translation of the word.
	 * 
	 * @param word
	 * @param dictionary
	 * @param translate
	 * @return an URI or null if there is no option to check or translate the word
	 *         with the specified dictionary
	 */
	private static URI lookUpOrTranslate(Word word, DICT dictionary, boolean translate) {
		Word.LANG lang = word.getLang();
		String format = "https://www.google.com/search?q=%s";
		String arg = word.getWord().replace(" ", "%20");
		URI uri = null;

		if (lang == Word.LANG.EN) {
			switch (dictionary) {
				case OXFORD:
					if (translate)
						return null;
					format = oxfordEn;
					break;

				case COLLINS:
					if (!translate)
						format = collinsEn;
					else
						format = collinsEnToEs;
					break;

				default: // WORDREFERENCE
					if (!translate)
						format = wordrefereceEn;
					else
						format = wordrefereceEnToEs;
					break;
			}
		} else if (lang == Word.LANG.ES) {
			switch (dictionary) {
				case WORDREFERENCE:
					if (!translate)
						format = wordrefereceEs;
					else
						format = wordrefereceEsToEn;
					break;

				case COLLINS:
					if (!translate)
						return null;
					format = collinsEsToEn;
					break;

				default: // RAE
					if (translate)
						return null;
					format = rae;
					break;
			}
		}

		uri = parseUri(format, arg);
		return uri;
	}

	private static URI parseUri(String format, String arg) {

		String url = String.format(format, arg);
		return URI.create(url);
	}

}
