package V;

public class ErrorDisplay extends Display {

	protected static void print(Object o) {
		System.err.print(o.toString());
	}

	protected static void println(Object o) {
		System.err.println(o.toString());
	}

	protected static void println() {
		System.err.println();
	}

	/**
	 * Dysplays: El dato introducido no es un número.\nPruebe de nuevo:
	 */
	public static void notANumber() {
		println("El dato introducido no es un número.");
		print("Pruebe de nuevo: ");
	}

	public static void invalidOption() {
		println("Opción inválida...");
	}

	public static void newError(String string) {
		println(string);
	}
}
