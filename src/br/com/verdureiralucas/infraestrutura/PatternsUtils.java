package br.com.verdureiralucas.infraestrutura;

import java.util.regex.Pattern;

public class PatternsUtils {
	private static final String CARACTERES_INVALIDOS = "^[a-zA-Z0-9_.-]*$";

	private PatternsUtils() {
	}

	public static boolean verificaSeHaCaracteresEspeciais(String texto) {
		return Pattern.matches(CARACTERES_INVALIDOS, texto);
	}

    public static String enquadrarTextoEmTamanho(String texto, int tamanhoPorLinha) {
        if (tamanhoPorLinha <= 0) {
            throw new IllegalArgumentException("O tamanhoPorLinha deve ser maior que zero.");
        }

        int tamanhoComeco = 0;
        int tamanhoDoTexto = texto.length();
        StringBuilder textoFormatado = new StringBuilder();

        while (tamanhoComeco < tamanhoDoTexto) {
        	
            int tamanhoFinal = Math.min(tamanhoComeco + tamanhoPorLinha, tamanhoDoTexto);

            textoFormatado.append(texto.substring(tamanhoComeco, tamanhoFinal));

            if (tamanhoFinal < tamanhoDoTexto) {
                textoFormatado.append("\n"); 
            }

            tamanhoComeco += tamanhoPorLinha;
        }

        return textoFormatado.toString();
    }

	
	public static boolean eNumero(String texto) {
		try {
			Integer.parseInt(texto);
			return true;
		} catch ( NumberFormatException nfe ) {
			return false;
		}
	}
}
