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
				throw new Exception("No se ha podido encontrado ni podido crear el archivo " + file.getAbsolutePath()
						+ "\n" + e.toString());
			}
			return;
		}
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader buff = new BufferedReader(fReader);
			String word;

			while ((word = buff.readLine()) != null)
				addWord(new Word(word, lang));
			buff.close();
			fReader.close();
		} catch (FileNotFoundException e) {
			throw new Exception("No se ha cargado nada al arrayList ya que no existe el archivo fuente.");

		} catch (IOException e) {
			throw new Exception("Algo ha ido ma al leer el archivo " + file.getAbsolutePath() + "\n" + e.toString());
		}
	}

	public String getFilePath(LANG lang) {
		return String.format(listFilesPathFormat, lang);
	}

	public int addWord(Word word) {
		int index;
		ArrayList<Word> list = getList(word.getLang());

		index = findPosition(list, word);
		if (index != -1) {
			capitalize(word);
			list.add(index, word);
		}
		try {
			save(word.getLang());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return index;
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

	private static int findPosition(ArrayList<Word> list, Word word) {
		int index = 0;
		int compareResult = -1;

		while (index < list.size() && compareResult < 0) {
			// compareResult =
			// list.get(index).getWord().compareToIgnoreCase(word.getWord());
			compareResult = compareStrings(list.get(index).getWord(), word.getWord());
			if (compareResult == 0)
				index = -1;
			else if (compareResult < 0)
				index++;
		}
		return index;
	}

	public static int compareStrings(String s1, String s2) {

		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		s1 = noTildes(s1);
		s2 = noTildes(s2);
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

	private static String noTildes(String str) {
		String original = "áéíóú";
		String ascii = "aeiou";
		String output = str;

		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}

	private void capitalize(Word word) {
		String output = word.getWord();

		output = output.substring(0, 1).toUpperCase() + output.substring(1).toLowerCase();
		word.setWord(output);
	}

	public void addWords(String possibleWords, LANG lang) {
		String[] possibleWordsArray = possibleWords.split(";");

		for (String word : possibleWordsArray) {
			word = word.trim();
			if (word.compareTo("") == 0)
				continue;
			addWord(new Word(word, lang));
		}
	}

	public void save(LANG lang) throws Exception {
		File file = new File(getFilePath(lang));

		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new Exception(String.format("Error al crear el archivo %s", file.getAbsolutePath()));
		}
		try {
			FileWriter fWriter = new FileWriter(file);
			ArrayList<Word> wordList = getList(lang);
			int size = wordList.size();
			int index = 0;

			fWriter.write("");
			while (index < size)
				fWriter.append(wordList.get(index++).getWord() + "\n");
			fWriter.close();
		} catch (IOException e) {
			throw new Exception(e.toString());
		}
	}

	public void clean(LANG lang) {
		if (lang == LANG.ES)
			ESList.clear();
		else if (lang == LANG.EN)
			ENList.clear();
	}

}
