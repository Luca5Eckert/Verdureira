package br.com.verdureiralucas.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.verdureiralucas.dto.ItemEditDto;
import br.com.verdureiralucas.exception.ItemNaoCorrespondeComTipoException;

public class EstoqueItem {
	private static final Map<TipoItem, List<Item>> itens;

	static {
		itens = new HashMap<>();
		for (TipoItem tipo : TipoItem.values()) {
			itens.put(tipo, new ArrayList<>());
		}
	}

	public boolean adicionarItem(TipoItem tipoItem, Item item) {
		if (!validarItemEIgualTipo(tipoItem, item)) {
			throw new ItemNaoCorrespondeComTipoException("Item não corresponde com tipo", item.pegarTipo(), tipoItem);
		}
		return itens.get(tipoItem).add(item);
	}

	public Optional<Item> pegarItem(TipoItem tipoItem, int index) {
		List<Item> lista = itens.get(tipoItem);
		if (lista != null && index >= 0 && index < lista.size()) {
			return Optional.of(lista.get(index));
		}
		return Optional.empty();
	}

	public Optional<List<Item>> pegarTipoItem(TipoItem tipoItem) {
		return Optional.ofNullable(itens.get(tipoItem));
	}

	public boolean removerItem(TipoItem tipoItem, Item item) {
		return itens.get(tipoItem).remove(item);
	}

	public Item removerItem(TipoItem tipoItem, int indexItem) {
		List<Item> lista = itens.get(tipoItem);
		if (lista != null && indexItem >= 0 && indexItem < lista.size()) {
			return lista.remove(indexItem);
		}
		return null;
	}

	public void adicionarTipoItem(TipoItem tipoItem) {
		itens.putIfAbsent(tipoItem, new ArrayList<>());
	}

	public void removerTipoItem(TipoItem tipoItem) {
		itens.remove(tipoItem);
	}

	private boolean validarItemEIgualTipo(TipoItem tipoItem, Item item) {
		return item.pegarTipo().equals(tipoItem);
	}

	public void alterarItem(int index, Item itemNovo) {
		List<Item> lista = itens.get(itemNovo.pegarTipo());
		if (lista != null && index >= 0 && index < lista.size()) {
			lista.set(index, itemNovo);
		}
	}

	public int pegarNumeroId(Item item) {
		List<Item> listaPercorrer = itens.get(item.pegarTipo());
		if (listaPercorrer == null) {
			return -1;
		}
		for (int id = 0; id < listaPercorrer.size(); id++) {
			Item itemComparado = listaPercorrer.get(id);
			if (item.equals(itemComparado)) {
				return id;
			}
		}
		return -1;
	}
}