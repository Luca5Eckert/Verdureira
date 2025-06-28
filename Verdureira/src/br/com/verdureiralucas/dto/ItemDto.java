package br.com.verdureiralucas.dto;

import br.com.verdureiralucas.model.Item;
import br.com.verdureiralucas.model.TipoItem;

public record ItemDto(String nome, double preco, String descricao, int quantidade, TipoItem tipoItem) {

	public ItemDto(Item item) {
		this(item.getNome(), item.getPreco(), item.getDescricao(), item.getQuantidade(), item.getTipoItem());
	}
	
	

}
