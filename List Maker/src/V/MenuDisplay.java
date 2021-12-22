package V;

import M.Word.LANG;

public class MenuDisplay extends Display {

	public static void chooseLang() {
		println("\n\n\n¿Con qué idioma va a trabajr?\n¿Español o inglés? [es/en]");
	}

	public static void mainMenu(LANG lang, int size) {
		lang(lang, size);
		println("\nEscoja una opción:");
		println("\t1. Añadir");
		// println("\t2. Definir");
		// println("\t3. Traducir");
		println("\t4. Abrir archivo");
		println("\t9. Cambiar de idioma");
		println("\t0. Salir");
		println("");
	}

	public static void addMenu(LANG lang) {
		lang(lang);
		println("\nPara añadir un término nuevo escríbalo y pulse enter.\nPara añadir varios a la vez separe los términos por un punto y coma, \";\".\nIntroduzca el comando \"!salir\" cuando no quiera seguir añadiendo nuevos términos.");
		println();
	}

	private static void lang(LANG lang, int size) {
		println("Lang: " + lang + "\tTotal palabras: " + size);
	}

	private static void lang(LANG lang) {
		println("Lang: " + lang);
	}

	public static void wordHasBeenAdded(int addedWords) {
		println();
		if (addedWords == 1)
			println("Se ha añadido una (1) palabra.");
		else if (addedWords > 1)
			println("Se han añadido " + addedWords + " palabras.");
		else
			println("Ninguna palabra nueva.");
		println();
		println();
	}

	public static void showWord(String word) {
		print(word);
	}

	public static void showWord(String prefix, String word, String sufix) {
		print(prefix + word + sufix);
	}
}
