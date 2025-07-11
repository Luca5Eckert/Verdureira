package br.com.verdureiralucas.view;

import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.dto.ItemEditDto;

public class MenuEditarItem implements Menu<String> {

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
	public String mostrarMenu() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("                  EDITAR ITEM                      ");
		System.out.println("---------------------------------------------------");
		System.out.println(" 1- Editar Nome");
		System.out.println(" 2- Editar Descrição");
		System.out.println(" 3- Editar Preço");
		System.out.println("\n 4- Confirmar edição");
		System.out.println(" 5- Cancelar edição");
		System.out.println("---------------------------------------------------");
		return input.nextLine().trim().toUpperCase();
	}

	@Override
	public Menu executarAcao(String opcaoEmString) {
		return switch(opcaoEmString){
		case "1" -> editarNome();
		case "2" -> editarDescricao();
		case "3" -> editarPreco();
		case "4" -> confirmarEdicao();
		case "5" -> cancelarEdicao();
		default -> {
				System.out.println("Entrada Invalida");
				yield this;
			}
		};
	}

	private Menu cancelarEdicao() {
		System.out.println("Edição Cancelada");
		return new MenuVisualizarItem(input, itemController,itemDto);
	}

	private Menu confirmarEdicao() {
		ItemEditDto itemEditDto = new ItemEditDto(this.nome,this.preco,this.descricao);

		ItemDto itemAlterado = itemController.alterarItem(itemEditDto, itemDto);

		System.out.println("Edição confirmada com sucesso");

		return new MenuVisualizarItem(input, itemController, itemAlterado);
	}

	private Menu editarPreco() {
		System.out.println("---------------------------------------------------");
		System.out.println("                 EDITAR PREÇO                      ");
		System.out.println("---------------------------------------------------");
		System.out.println(" Antigo Preço: " + preco);
		System.out.println("---------------------------------------------------");
		System.out.print(" Novo Preço: " );
		preco = input.nextDouble();

		limparScanner();

		return this;
	}

	private Menu editarDescricao() {
		System.out.println("---------------------------------------------------");
		System.out.println("                EDITAR DESCRIÇÃO                   ");
		System.out.println("---------------------------------------------------");
		System.out.println(" Antigo descrição: " + descricao);
		System.out.println("---------------------------------------------------");
		System.out.print(" Novo descrição: " );
		descricao = input.nextLine();

		return this;
	}

	private Menu editarNome() {
		System.out.println("---------------------------------------------------");
		System.out.println("                   EDITAR NOME                     ");
		System.out.println("---------------------------------------------------");
		System.out.println(" Antigo nome: " + nome);
		System.out.println("---------------------------------------------------");
		System.out.print(" Novo nome: " );
		nome = input.nextLine();

		return this;
	}

	@Override
	public void limparScanner() {
		input.nextLine();
	}

}
