package br.com.verdureiralucas.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.verdureiralucas.dto.ItemEditDto;
import br.com.verdureiralucas.exception.ItemNaoCorrespondeComTipoException;

public class EstoqueItem {
	private static Map<TipoItem, List<Item>> itens = new HashMap();

	public boolean adicionarItem(TipoItem tipoItem, Item item) {
		if (!validarItemEIgualTipo(tipoItem, item)) {
			throw new ItemNaoCorrespondeComTipoException("Item não corresponde com tipo", item.getTipoItem(), tipoItem);
		}
		return itens.get(tipoItem).add(item);
	}

	public Optional<Item> pegarItem(TipoItem tipoItem, int index) {
		return Optional.ofNullable(itens.get(tipoItem).get(index));
	}

	public Optional<List<Item>> pegarTipoItem(TipoItem tipoItem) {
		return Optional.ofNullable(itens.get(tipoItem));
	}

	public boolean removerItem(TipoItem tipoItem, Item item) {
		return itens.get(tipoItem).remove(item);
	}

	public Item removerItem(TipoItem tipoItem, int indexItem) {
		return itens.get(tipoItem).remove(indexItem);
	}

	public void adicionarTipoItem(TipoItem tipoItem) {
		itens.put(tipoItem, new ArrayList<Item>());
	}

	public void removerTipoItem(TipoItem tipoItem) {
		itens.remove(tipoItem);
	}

	private boolean validarItemEIgualTipo(TipoItem tipoItem, Item item) {
		return item.getTipoItem().equals(tipoItem);
	}

	public void alterarItem(int index, Item itemNovo) {
		itens.get(itemNovo.getTipoItem()).add(index, itemNovo);
	}

	public int pegarNumeroId(Item item) {
		Item itemComparado;
		var listaPercorrer = this.itens.get(item.getTipoItem());

		for (int id = 0; id < listaPercorrer.size(); id++) {
			itemComparado = listaPercorrer.get(id);
			if (item.equals(itemComparado)) {
				return id;
			}
		}

		return -1;
	}

}
