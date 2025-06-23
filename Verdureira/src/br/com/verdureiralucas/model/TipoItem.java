package br.com.verdureiralucas.model;

import java.util.List;

public enum TipoItem {
	FRUTA;

	public static void pegarValoresTipos() {
		for (int item = 0; values().length > item; item++) {
			System.out.println(" " + item + " - " + values()[item]);
		}
	}

}
