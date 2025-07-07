package br.com.verdureiralucas.model;

public class Fruta extends Item{

    public Fruta(String nome, double preco, String descricao, int quantidade) {
        super(nome, preco, descricao, quantidade);
    }

    public Fruta(Item item) {
        super(item.getNome(), item.getPreco(), item.getDescricao(), item.getQuantidade());
    }

    @Override
    public TipoItem pegarTipo(){
        return TipoItem.FRUTA;
    }

}
