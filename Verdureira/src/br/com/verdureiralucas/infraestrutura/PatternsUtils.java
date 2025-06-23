package br.com.verdureiralucas.infraestrutura;

import java.util.regex.Pattern;

public class PatternsUtils {
	private static final String CARACTERES_INVALIDOS = "^[a-zA-Z0-9_.-]*$";

	private PatternsUtils() {
	}

	public static boolean verificaSeHaCaracteresEspeciais(String texto) {
		return Pattern.matches(CARACTERES_INVALIDOS, texto);
	}
}
