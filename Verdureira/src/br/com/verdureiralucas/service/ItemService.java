package br.com.verdureiralucas.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.exception.TipoItemNaoExisteEstoqueException;
import br.com.verdureiralucas.model.EstoqueItem;
import br.com.verdureiralucas.model.Item;
import br.com.verdureiralucas.model.TipoItem;

public class ItemService {

	private final EstoqueItem estoque;

	public ItemService(EstoqueItem estoque) {
		this.estoque = estoque;
	}

	public void adicionarItem(ItemDto itemDto) {
		if (estoque.pegarTipoItem(itemDto.tipoItem()).isEmpty()) {
			estoque.adicionarTipoItem(itemDto.tipoItem());
		}

		Item item = transformarDtoEmModel(itemDto);
		estoque.adicionarItem(itemDto.tipoItem(), item);
	}

	public void removerItem(int index, TipoItem tipoItem) {
		verificarSeTipoItemExisteEstoque(tipoItem);

		estoque.removerItem(tipoItem, index);

	}

	public ItemDto pegarItem(int index, TipoItem tipoItem) {
		verificarSeTipoItemExisteEstoque(tipoItem);

		Item item = estoque.pegarItem(tipoItem, index).orElseThrow(
				() -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));

		return new ItemDto(item);
	}

	private void verificarSeTipoItemExisteEstoque(TipoItem tipoItem) {
		estoque.pegarTipoItem(tipoItem).orElseThrow(
				() -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));
	}

	public void aumentarQuantidadeItem(int aumentarEm, int index, TipoItem tipoItem) {
		verificarSeTipoItemExisteEstoque(tipoItem);

		Item item = estoque.pegarItem(tipoItem, index).orElseThrow(
				() -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));
		item.aumentarQuantidade(index);
	}

	public List<ItemDto> pegarTodosItens(TipoItem tipoItem) {
		var lista = estoque.pegarTipoItem(tipoItem).orElseThrow(
				() -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));
		return transformarListaModelEmDto(lista);

	}

	private Item transformarDtoEmModel(ItemDto itemDto) {
		return new Item(itemDto.nome(), itemDto.preco(), itemDto.descricao(), itemDto.quantidade(), itemDto.tipoItem());
	}

	private ItemDto transformarModelEmDto(Item item) {
		return new ItemDto(item);
	}

	private List<ItemDto> transformarListaModelEmDto(List<Item> listaItem) {
		return listaItem.stream().map(this::transformarModelEmDto).collect(Collectors.toList());
	}

}
