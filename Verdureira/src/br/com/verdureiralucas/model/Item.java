package br.com.verdureiralucas.model;

import br.com.verdureiralucas.exception.InsercaoInvalidaException;
import br.com.verdureiralucas.infraestrutura.PatternsUtils;

public class Item {

	private String nome;
	private double preco;
	private String descricao;
	private int quantidade;

	private static final TipoItem tipoItem = TipoItem.FRUTA;

	public Item(String nome, double preco, String descricao, int quantidade, TipoItem tipoItem) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
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

}
