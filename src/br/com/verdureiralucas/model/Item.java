package br.com.verdureiralucas.model;

import br.com.verdureiralucas.exception.InsercaoInvalidaException;
import br.com.verdureiralucas.infraestrutura.TextUtils;

public abstract class Item {

	private String nome;
	private double preco;
	private String descricao;
	private int quantidade;

	public Item(String nome, double preco, String descricao, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}

	public void aumentarQuantidade(int aumentarEm) {
		this.setQuantidade(this.quantidade + aumentarEm);
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}


    public void setNome(String nome) {
		if (nome.isBlank() || TextUtils.verificaSeHaCaracteresEspeciais(nome)) {
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


	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Item item)) {
			return false;
		}

        if (item == this) {
			return true;
		}

        return this.nome.equals(item.getNome()) && this.preco == item.getPreco() && this.descricao.equals(item.getDescricao()) && this.quantidade == item.getQuantidade();
    }

	public abstract TipoItem pegarTipo();
}
