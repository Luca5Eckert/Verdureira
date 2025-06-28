package br.com.verdureiralucas.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.exception.InsercaoInvalidaException;
import br.com.verdureiralucas.exception.ItemNaoExisteEstoqueException;
import br.com.verdureiralucas.exception.TipoItemNaoExisteEstoqueException;
import br.com.verdureiralucas.infraestrutura.PatternsUtils;
import br.com.verdureiralucas.model.TipoItem;

public class MenuListarItens implements Menu {

	private final Scanner input;
	private final ItemController itemController;
	private TipoItem tipoSelecionado;

	public MenuListarItens(Scanner input, ItemController itemController) {
		this.input = input;
		this.itemController = itemController;
	}

	@Override
	public Object mostrarMenu() {
		TipoItem tipoItem = mostrarMenuEscolhaItens();
		return mostrarMenuListar(tipoItem);
	}

	private Object mostrarMenuListar(TipoItem tipoItem) {
		var listaItens = pegarItensTipo(tipoItem);
		tipoSelecionado = tipoItem;

		System.out.println("---------------------------------------------------");
		System.out.println("                     LISTAGEM                      ");
		System.out.println("---------------------------------------------------");
		System.out.println(" S - Sair");
		System.out.println(" Itens do tipo " + tipoItem.getEmString() + " :");

		if (listaItens.isPresent()) {
			exibirItens(listaItens.get());
		} else {
			System.out.println("Estoque vazio para o tipo " + tipoItem.getEmString() + ".");
		}

		System.out.println("---------------------------------------------------");

		return input.nextLine().trim().toUpperCase();
	}

	private Optional<List<ItemDto>> pegarItensTipo(TipoItem tipoItem) {
		List<ItemDto> listaItens;
		try {
			listaItens = itemController.pegarTodosItens(tipoItem);
		} catch (ItemNaoExisteEstoqueException | TipoItemNaoExisteEstoqueException ineee) {
			listaItens = null;
		}
		return Optional.ofNullable(listaItens);
	}

	private void exibirItens(List<ItemDto> itens) {
		ItemDto itemDto;

		for (int contador = 0; contador < itens.size(); contador++) {
			itemDto = itens.get(contador);
			System.out.println(" " + contador + " -  " + itemDto.nome() + "    R$" + itemDto.preco());
		}
	}

	private TipoItem mostrarMenuEscolhaItens() {
		System.out.println("---------------------------------------------------");
		System.out.println("                     LISTAGEM                      ");
		System.out.println("---------------------------------------------------");
		System.out.println(" Qual tipo você quer listar: ");
		TipoItem.pegarValoresTipos();
		int escolha = -1;
		try {
			escolha = input.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("Entrada inválida. Por favor, digite um número.");
			throw new InsercaoInvalidaException("Entrada não numérica para escolha de tipo", "Não é um número");
		} finally {
			input.nextLine();
		}

		if (escolha >= TipoItem.values().length || escolha < 0) {
			throw new IllegalArgumentException("Opção de tipo de item inválida. Por favor, escolha um número dentro das opções disponíveis.");
		}

		return TipoItem.values()[escolha];
	}

	@Override
	public Menu executarAcao(Object opcaoSelecionada) {
		String textoEnviado = (String) opcaoSelecionada;

		return switch (textoEnviado) {
			case "S" -> new MenuItemGeral(input);
			default -> verificarinput(textoEnviado);
		};
	}

	private Menu verificarinput(String inserido) {
		boolean eNumero = PatternsUtils.eNumero(inserido);

		if (eNumero) {
			return criarMenuDoItemSelecionado(Integer.parseInt(inserido));
		}

		throw new InsercaoInvalidaException("Entrada inválida. Digite 'S' para sair ou um número para selecionar um item.", inserido);
	}

	private Menu criarMenuDoItemSelecionado(int indexItemSelecionado) {
		Optional<ItemDto> itemdto = pegarItemSelecionado(indexItemSelecionado);

		if (itemdto.isEmpty()) {
			System.out.println("Item selecionado não encontrado. Por favor, verifique o índice e tente novamente.");
			return this;
		}

		return new MenuVisualizarItem(input, itemController, itemdto.get());
	}

	private Optional<ItemDto> pegarItemSelecionado(int indexItemSelecionado) {
		ItemDto itemDto = null;

		try {
			itemDto = itemController.pegarItem(indexItemSelecionado, tipoSelecionado);
		} catch (TipoItemNaoExisteEstoqueException | ItemNaoExisteEstoqueException tineee) {
			System.out.println("Erro ao buscar item: " + tineee.getMessage());
		}
		return Optional.ofNullable(itemDto);
	}

	@Override
	public void limparScanner() {
		input.nextLine();
	}
}