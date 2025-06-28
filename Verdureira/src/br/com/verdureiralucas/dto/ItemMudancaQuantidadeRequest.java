package br.com.verdureiralucas.dto;

public record ItemMudancaQuantidadeRequest(ItemDto itemDto, int quantidade) {

    public ItemDto alterarQuantidade(){
        return new ItemDto(itemDto.nome(), itemDto.preco(), itemDto.descricao(), itemDto.quantidade()+quantidade, itemDto.tipoItem());
    }

}

