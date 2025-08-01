package br.com.verdureiralucas.view;

import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.model.TipoItem;

public class MenuAdicionarItem implements Menu<ItemDto> {

    private final ItemController itemController;
    private final Scanner input;

    public MenuAdicionarItem(ItemController itemController, Scanner input) {
        this.itemController = itemController;
        this.input = input;
    }

    @Override
    public ItemDto mostrarMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("                ADICIONAR ITEM                     ");
        System.out.println("---------------------------------------------------");
        System.out.println(" Tipo do item: ");
        TipoItem.pegarValoresTipos();
        TipoItem tipoMenu = TipoItem.values()[input.nextInt()];

        limparScanner();
        System.out.println(" Nome do Item: ");
        String nome = input.nextLine();

        System.out.println(" Descrição do Item: ");
        String descricao = input.nextLine();

        System.out.println(" Preço do Item: ");
        double preco = input.nextDouble();

        System.out.println(" Quantidade do Item: ");
        int qtdItem = input.nextInt();

        System.out.println("---------------------------------------------------");
        return new ItemDto(nome, preco, descricao, qtdItem, tipoMenu);
    }

    @Override
    public Menu<?> executarAcao(ItemDto itemDto) {
        itemController.adicionarItem(itemDto);

        return new MenuResposta(new MenuItemGeral(input), "Adicionado Com sucesso", input);
    }

    @Override
    public void limparScanner() {
        input.nextLine();

    }

}
