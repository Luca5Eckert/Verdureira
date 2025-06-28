package br.com.verdureiralucas.model;

public enum TipoItem {
	FRUTA("fruta");

	private final String emString;

	public String getEmString() {
		return emString;
	}

	TipoItem(String emString) {
		this.emString = emString;
	}

	public static void pegarValoresTipos() {
		for (int item = 0; values().length > item; item++) {
			System.out.println(" " + item + " - " + values()[item]);
		}
	}

}
