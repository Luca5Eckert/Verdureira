package br.com.verdureiralucas.model;

public class ItemFactory {

    public static Item instanceTo(String nome, double preco, String descricao, int quantidade, TipoItem tipoItem) {
        return switch(tipoItem){
            case FRUTA -> new Fruta(nome,preco,descricao,quantidade);
        };
    }

}
