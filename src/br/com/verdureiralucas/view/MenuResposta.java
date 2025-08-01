package br.com.verdureiralucas.view;

import java.awt.im.InputContext;
import java.util.Scanner;

public class MenuResposta implements Menu<String> {

    private final Scanner input;

    private final Menu<?> menu;
    private final String mensagemErro;

    public MenuResposta(Menu<?> menu, String mensagemErro, Scanner input) {
        this.menu = menu;
        this.mensagemErro = mensagemErro;
        this.input = input;
    }

    @Override
    public String mostrarMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("                      RESULTADO                    ");
        System.out.println("---------------------------------------------------");

        System.out.println(" " + mensagemErro);

        System.out.println(" 1 - Continuar ");
        return input.nextLine();
    }

    @Override
    public Menu<?> executarAcao(String opcaoSelecionada) {
        return menu;
    }

    @Override
    public void limparScanner() {
        if (input.hasNext()) {
            input.nextLine();
        }
    }

}
