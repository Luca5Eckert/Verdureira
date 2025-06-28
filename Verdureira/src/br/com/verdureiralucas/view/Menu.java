package br.com.verdureiralucas.view;

public interface Menu {

	Object mostrarMenu();

	Menu executarAcao(Object opcaoSelecionada);

	void limparScanner();

}
