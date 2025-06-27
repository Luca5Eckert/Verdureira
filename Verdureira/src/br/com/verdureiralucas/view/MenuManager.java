package br.com.verdureiralucas.view;

public class MenuManager {

	private final MenuProvider menuProvider;

	public MenuManager(MenuProvider menuProvider) {
		this.menuProvider = menuProvider;
	}

	public void chamarMenu() {
		while (verificarContinuidade()) {
			executarMenu();
			verificarContinuidade();
		}
	}

	private boolean verificarContinuidade() {
		return menuProvider.verificarContinuidade();
	}

	public void executarMenu() {
		menuProvider.chamarMenu();
	}
}
