package br.com.verdureiralucas.model;

import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.exception.InsercaoInvalidaException;
import br.com.verdureiralucas.infraestrutura.PatternsUtils;

public class Item {

	private String nome;
	private double preco;
	private String descricao;
	private int quantidade;

	private TipoItem tipoItem;

	public Item(String nome, double preco, String descricao, int quantidade, TipoItem tipoItem) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tipoItem = tipoItem;
	}

	public Item(ItemDto itemDto) {
		this.nome = itemDto.nome();
		this.preco = itemDto.preco();
		this.descricao = itemDto.descricao();
		this.quantidade = itemDto.quantidade();
		this.tipoItem = itemDto.tipoItem();
	}

	public void aumentarQuantidade(int aumentarEm) {
		this.quantidade += aumentarEm;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setNome(String nome) {
		if (nome.isBlank() || nome.isEmpty() || PatternsUtils.verificaSeHaCaracteresEspeciais(nome)) {
			throw new InsercaoInvalidaException("Inserção invalida", nome);
		}
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}


	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Item)) {
			return false;
		}

		Item item = (Item) object;

		if (item == this) {
			return true;
		}

		if (this.nome.equals(item.getNome()) && this.preco == item.getPreco() && this.descricao.equals(item.getDescricao()) && this.quantidade == item.getQuantidade() && this.tipoItem.equals(item.tipoItem)) {
			return true;
		}

		return false;
	}

}
