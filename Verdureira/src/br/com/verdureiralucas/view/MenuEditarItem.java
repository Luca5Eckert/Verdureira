package br.com.verdureiralucas.view;

import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.dto.ItemDto;

public class MenuEditarItem implements Menu {

	private final Scanner input;
	private final ItemController itemController;
	private final ItemDto itemDto;

	private String nome;
	private String descricao;
	private double preco;

	public MenuEditarItem(Scanner input, ItemController itemController, ItemDto itemDto) {
		this.input = input;
		this.itemController = itemController;
		this.itemDto = itemDto;

		nome = itemDto.nome();
		descricao = itemDto.descricao();
		preco = itemDto.preco();
	}

	@Override
	public Object mostrarMenu() {
		return null;
	}

	@Override
	public Menu executarAcao(Object opcaoSelecionada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void limparScanner() {
		// TODO Auto-generated method stub

	}

}
