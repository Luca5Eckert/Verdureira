package br.com.verdureiralucas.dto;

import br.com.verdureiralucas.model.Item;
import br.com.verdureiralucas.model.TipoItem;

public record ItemDto( String nome, double preco, String descricao, int quantidade, TipoItem tipoItem) {

    public ItemDto(ItemDtoBuilder itemDtoBuilder) {
        this(itemDtoBuilder.nome, itemDtoBuilder.preco, itemDtoBuilder.descricao, itemDtoBuilder.quantidade, itemDtoBuilder.tipoItem);
    }

    public static class ItemDtoBuilder{
        public final String nome;
        public final double preco;
        public final String descricao;
        public final int quantidade;
        public final TipoItem tipoItem;

        public ItemDtoBuilder(String nome, double preco, String descricao, int quantidade, TipoItem tipoItem) {
            this.nome = nome;
            this.preco = preco;
            this.descricao = descricao;
            this.quantidade = quantidade;
            this.tipoItem = tipoItem;
        }

        public ItemDtoBuilder(Item item) {
            this.nome = item.getNome();
            this.preco = item.getPreco();
            this.descricao = item.getDescricao();
            this.quantidade = item.getQuantidade();
            this.tipoItem = item.pegarTipo();
        }

        public ItemDto build(){
            return new ItemDto(this);
        }

    }



}
