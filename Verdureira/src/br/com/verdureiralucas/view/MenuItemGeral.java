package br.com.verdureiralucas.view;

import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.model.EstoqueItem;
import br.com.verdureiralucas.service.ItemService;

public class MenuItemGeral implements Menu {

	private final Scanner input;

	public MenuItemGeral(Scanner input) {
		this.input = input;
	}

	@Override
	public Object mostrarMenu() {
		System.out.println("---------------------------------------------------");
		System.out.println("                     MENU ITEM                     ");
		System.out.println("---------------------------------------------------");
		System.out.println(" 1- Adicionar Item ");
		System.out.println(" 2- Remover Item ");
		System.out.println(" 3- Exibir Item ");
		System.out.println("---------------------------------------------------");
		return input.nextLine().toUpperCase().trim();
	}

	@Override
	public Menu executarAcao(Object opcaoSelecionada) {
		String opcaoString = (String) opcaoSelecionada;

		return switch (opcaoString) {
		case "1" -> new MenuAdicionarItem(new ItemController(new ItemService(new EstoqueItem())), input);
		default -> this;
		};
	}

}
