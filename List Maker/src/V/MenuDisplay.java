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
		println("\t4. Guardar y abrir archivo");
		println("\t8. Guardar y cambiar de idioma");
		println("\t9. Guardar");
		println("\t0. Guardar y salir");
		println("\t-1. Salir sin guardar");
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

	public static void wordHasBeenAdded() {
		println();
		println("Añadido.");
		println();
	}

	public static void showWord(String word) {
		print(word);
	}

	public static void showWord(String prefix, String word, String sufix) {
		print(prefix + word + sufix);
	}
}
