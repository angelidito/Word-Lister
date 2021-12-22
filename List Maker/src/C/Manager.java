package C;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import M.Lister;
import M.WordLists;
import M.Word.LANG;
import V.ErrorDisplay;
import V.MenuDisplay;

public class Manager {

	private static String listFilesPathFormat = "palabras%s.txt";
	private static Lister lister;
	private static LANG lang = LANG.UNKNOWN;

	public static void main(String[] args) {
		Scanner.newInstance();
		boolean exit = true;

		do {
			if (lang == LANG.UNKNOWN) {
				MenuDisplay.chooseLang();
				lang = Scanner.readLang();
			}
			if (lang == LANG.UNKNOWN)
				continue;

			try {
				lister = new Lister(listFilesPathFormat);
				exit = mainMenu();
			} catch (Exception e) {
				ErrorDisplay.newError(e.toString() + "\n Se va a finalizar el programa.");
				e.printStackTrace();
				exit = true;
			}
		} while (!exit);
	}

	/**
	 * Main menu once
	 */
	private static boolean mainMenu() {
		boolean exit = false;
		int op = -99;

		do {
			MenuDisplay.mainMenu(lang, WordLists.getList(lang).size());
			op = Scanner.readInt();
			switch (op) {
				case 1:
					addMenu();
					break;
				case 4:
					open(new File(lister.getFilePath(lang)));
					break;
				case 9:
					op = 0;
					lang = LANG.UNKNOWN;
					break;
				case 0:
					exit = true;
					break;
				// case -99:
				// lister.clean(lang);
				default:
					break;
			}
		} while (op != 0);

		return exit;
	}

	private static void saveData() {
		try {
			lister.save(lang);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void open(File file) {
		try {
			java.awt.Desktop.getDesktop().open(file);
		} catch (IOException e) {
			ErrorDisplay.newError("FILE COULD NOT BE OPENED.");
			ErrorDisplay.newError(e.toString());
			e.printStackTrace();
		}
	}

	private static void addMenu() {
		String possibleWords = "";
		boolean exit = false;

		MenuDisplay.addMenu(lang);
		do {
			possibleWords = Scanner.readLine();
			if (possibleWords.equalsIgnoreCase("!salir") || possibleWords.equalsIgnoreCase("!exit"))
				exit = true;
			else {
				lister.addWords(possibleWords.split(";"), lang);
				saveData();
				MenuDisplay.wordHasBeenAdded();
			}
		} while (!exit);
	}

	/**
	 * Opens the specified URI in the browser.
	 * 
	 * @param uri URI to open.
	 */
	private static void browse(URI uri) {
		try {
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			ErrorDisplay.newError("BROWSER COULD NOT BE OPENED.");
			ErrorDisplay.newError(e.toString());
			e.printStackTrace();
		}
	}
}
