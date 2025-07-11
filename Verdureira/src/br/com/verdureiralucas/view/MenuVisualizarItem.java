package br.com.verdureiralucas.view;

import java.sql.SQLOutput;
import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.dto.ItemMudancaQuantidadeRequest;
import br.com.verdureiralucas.infraestrutura.PatternsUtils;
import br.com.verdureiralucas.model.TipoItem;

public class MenuVisualizarItem implements Menu<String> {

	private final Scanner input;
	private final ItemController itemController;
	private ItemDto itemDto;

	public MenuVisualizarItem(Scanner input, ItemController itemController, ItemDto itemDto) {
		this.input = input;
		this.itemController = itemController;
		this.itemDto = itemDto;
	}

	@Override
	public String mostrarMenu() {
		TipoItem tipoItem = itemDto.tipoItem();
		System.out.println("---------------------------------------------------");
		System.out.println("                   " + tipoItem + "                        ");
		System.out.println("---------------------------------------------------");
		System.out.println(" Nome: " + itemDto.nome());
		System.out.println(" Descrição: " + PatternsUtils.enquadrarTextoEmTamanho(itemDto.descricao(), 40));
		System.out.println("\n Preço: " + itemDto.preco());
		System.out.println(" Quantidade: " + itemDto.quantidade());

		System.out.println("\n 1- Aumentar Estoque    2- Diminuir Estoque");
		System.out.println(" 3- Editar " + tipoItem.getEmString() + "       4- Apagar " + tipoItem.getEmString());
		System.out.println(" 5- Sair");
		System.out.println("---------------------------------------------------");
		return input.nextLine();
	}

	@Override
	public Menu executarAcao(String opcao) {
		return switch (opcao) {
		case "1" -> aumentarItemEstoque();
		case "2" -> diminuirItemEstoque();
		case "3" -> new MenuEditarItem(input, itemController, itemDto);
		case "4" -> apagarItemEstoque();
		case "5" -> new MenuItemGeral(input);
		default -> this;
		};
	}

	private Menu diminuirItemEstoque() {
		System.out.println("---------------------------------------------------");
		System.out.println("               DIMINUIR ITEM                       ");
		System.out.println("---------------------------------------------------");
		System.out.println(" S - Sair ");
		System.out.println(" Você deseja diminuir para quanto: " );
		int subtracao = input.nextInt();
		input.nextLine();

		ItemMudancaQuantidadeRequest itemMudancaQuantidadeRequest = new ItemMudancaQuantidadeRequest(itemDto, 0-subtracao);
		alterarQuantidadeItem(itemMudancaQuantidadeRequest);

		return this;
	}

	private Menu aumentarItemEstoque() {
		System.out.println("---------------------------------------------------");
		System.out.println("              AUMENTAR QUANTIDADE                  ");
		System.out.println("---------------------------------------------------");
		System.out.println(" S - Sair ");
		System.out.println(" Você deseja aumentar para quanto: " );
		int aumenta = input.nextInt();
		input.nextLine();

		ItemMudancaQuantidadeRequest itemMudancaQuantidadeRequest = new ItemMudancaQuantidadeRequest(itemDto, aumenta);
		alterarQuantidadeItem(itemMudancaQuantidadeRequest);

		return this;
	}

	private void alterarQuantidadeItem(ItemMudancaQuantidadeRequest itemMudancaQuantidadeRequest){
		ItemDto item = itemController.alterarEstoque(itemMudancaQuantidadeRequest);
		atualizarItemDto(item);
	}

	private void atualizarItemDto(ItemDto itemDto){
		this.itemDto = itemDto;
	}


	private Menu apagarItemEstoque() {
		System.out.println("---------------------------------------------------");
		System.out.println("                   TEM CERTEZA?                    ");
		System.out.println("---------------------------------------------------");
		System.out.println(" 1 - Continuar ");
		System.out.println(" 2 - Cancelar");
		String inserido = input.nextLine().trim().toUpperCase();

		switch (inserido) {
		case "1" -> {
			itemController.removerItem(itemDto);
			System.err.println("Apagado com sucesso");

			return new MenuItemGeral(input);

		}
		default -> {
			System.err.println("Ação Cancelada");
			return this;
		}
		}

	}

	@Override
	public void limparScanner() {
		input.nextLine();
	}

}
