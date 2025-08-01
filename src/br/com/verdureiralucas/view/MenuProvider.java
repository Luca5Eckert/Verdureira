package br.com.verdureiralucas.view;

public class MenuProvider {

    private Menu menu;

    public MenuProvider(Menu<?> menu) {
        this.menu = menu;
    }

    public void chamarMenu() {
        try {
            Object opcaoSelecionada = exibirMenu();
            executarMenu(opcaoSelecionada);
        } catch (Exception e) {
            limparErroScanner();
            System.err.println(" Entrada invalida");
        }

    }

    private void limparErroScanner() {
        menu.limparScanner();
    }

    private Object exibirMenu() {
        return menu.mostrarMenu();
    }

    public boolean verificarContinuidade() {
        return menu != null;
    }

    private void executarMenu(Object opcaoSelecionada) {
        menu = menu.executarAcao(opcaoSelecionada);
    }

}
