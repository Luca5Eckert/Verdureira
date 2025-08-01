package br.com.verdureiralucas.model;

public class Verdura extends Item {

    public Verdura(String nome, double preco, String descricao, int quantidade) {
        super(nome, preco, descricao, quantidade);
    }

    @Override
    public TipoItem pegarTipo() {
        return TipoItem.VERDURA;
    }
}
