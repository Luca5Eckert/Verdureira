package br.com.verdureiralucas.view;

public interface Menu<T> {

    T mostrarMenu();

    Menu executarAcao(T opcaoSelecionada);

    void limparScanner();

}
