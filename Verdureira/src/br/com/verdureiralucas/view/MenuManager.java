package br.com.verdureiralucas.view;

public class MenuManager {

	private final MenuProvider menuProvider;
	private boolean aplicacaoFuncionando;

	public MenuManager(MenuProvider menuProvider, boolean aplicacaoFuncionando) {
		this.menuProvider = menuProvider;
		this.aplicacaoFuncionando = aplicacaoFuncionando;
	}

	public void chamarMenu() {
		while (aplicacaoFuncionando) {
			executarMenu();
			verificarContinuidade();
		}
	}

	private void verificarContinuidade() {
		aplicacaoFuncionando = menuProvider.verificarContinuidade();
	}

	public void executarMenu() {
		menuProvider.chamarMenu();
	}
}
