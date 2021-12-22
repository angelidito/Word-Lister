package M;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import M.Word.LANG;

public class Lister {

	String listFilesPathFormat;
	private ArrayList<Word> ESList;
	private ArrayList<Word> ENList;

	public Lister(String listFilesPathFormat) throws Exception {
		ESList = new ArrayList<Word>();
		ENList = new ArrayList<Word>();
		this.listFilesPathFormat = listFilesPathFormat;
		init(LANG.ES);
		init(LANG.EN);
	}

	private void init(LANG lang) throws Exception {

		File file = new File(getFilePath(lang));

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException("No se ha podido encontrado ni podido crear el archivo " + file.getAbsolutePath()
						+ "\n" + e.toString());
			}
			return;
		}
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader buff = new BufferedReader(fReader);
			String word;

			while ((word = buff.readLine()) != null)
				if (!word.isEmpty())
					addWord(new Word(word, lang));
			buff.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("No se ha cargado nada al arrayList ya que no existe el archivo fuente.");

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Algo ha ido ma al leer el archivo " + file.getAbsolutePath() + "\n"
					+ e.toString());
		}
	}

	public String getFilePath(LANG lang) {
		return String.format(listFilesPathFormat, lang);
	}

	/**
	 * Same as String.compareTo, but ingoring case and Spanish tildes and accents.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int compareStrings(String s1, String s2) {

		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		s1 = noTildes(s1);
		s2 = noTildes(s2);
		// if (s1.equalsIgnoreCase("jab") || s2.equalsIgnoreCase("jab"))
		System.out.println(s1 + " - " + s2);
		for (int i = 0; i < s1.length() && i < s2.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if ((int) c1 == (int) c2) {
				continue;
			} else {
				return (int) c1 - (int) c2;
			}
		}

		if (s1.length() < s2.length()) {
			return (s1.length() - s2.length());
		} else if (s1.length() > s2.length()) {
			return (s1.length() - s2.length());
		} else {
			return 0;
		}
	}

	/**
	 * Returns the input string but with no accents nor tildes.
	 * 
	 * @param str Input string.
	 * @return The input string but with no accents nor tildes.
	 */
	private static String noTildes(String str) {
		String original = "ÁÉÍÓÚÑáéíóúñ";
		String ascii = "AEIOUÑaeiouñ";
		String output = str;

		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}

	/**
	 * Capitalice a Word.
	 * 
	 * @param word Word to capitalize.
	 */
	private void capitalize(Word word) {
		String output = word.getWord();

		if (output.length() < 2)
			output = output.toUpperCase();
		else
			output = output.substring(0, 1).toUpperCase() + output.substring(1).toLowerCase();
		word.setWord(output);
	}

	/**
	 * Returns the position where the word shoud be at on the list.
	 * 
	 * If the word is in the list, -1 will be returned if the word is already on the
	 * list.
	 * 
	 * @param list List to check on.
	 * @param word Word to check.
	 * @return The position the word should be at on the list, or -1 if it is
	 *         already on it.
	 */
	private static int findPosition(ArrayList<Word> list, Word word) {
		int index = 0;
		int compareResult = -1;

		while (index < list.size() && compareResult < 0) {
			// if (list.size() >= 249 && index >= list.size() - 2)
			// index = index + 1 - 1;
			// compareResult =
			// list.get(index).getWord().compareToIgnoreCase(word.getWord());
			if (word.getWord().equalsIgnoreCase("jab"))
				index = index + 1 - 1;

			compareResult = compareStrings(list.get(index).getWord(), word.getWord());
			if (compareResult == 0)
				index = -1;
			else if (compareResult < 0)
				index++;
		}
		return index;
	}

	public int addWord(Word word) {
		int index;
		ArrayList<Word> list = WordLists.getList(word.getLang());

		index = findPosition(list, word);
		if (index != -1) {
			capitalize(word);
			WordLists.add(index, word);
		}
		return index;
	}

	/**
	 * Add the
	 * 
	 * @param wordArray
	 * @param lang
	 */
	public void addWords(String[] wordArray, LANG lang) {
		for (String word : wordArray) {
			word = word.trim();
			if (word.equals(""))
				continue;
			addWord(new Word(word, lang));
		}
	}

	public void save(LANG lang) throws IOException {
		File file = new File(getFilePath(lang));

		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new IOException(String.format("Error al crear el archivo %s", file.getAbsolutePath()));
		}
		try {
			FileWriter fWriter = new FileWriter(file);
			ArrayList<Word> wordList = WordLists.getList(lang);
			int size = wordList.size();
			int index = 0;

			fWriter.write("");
			while (index < size)
				fWriter.append(wordList.get(index++).getWord() + "\n");
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e.toString());
		}
	}

	public void clean(LANG lang) {
		if (lang == LANG.ES)
			ESList.clear();
		else if (lang == LANG.EN)
			ENList.clear();
	}

}
