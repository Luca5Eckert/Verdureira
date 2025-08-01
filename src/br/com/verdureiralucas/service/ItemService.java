package br.com.verdureiralucas.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.dto.ItemEditDto;
import br.com.verdureiralucas.dto.ItemMudancaQuantidadeRequest;
import br.com.verdureiralucas.exception.ItemNaoExisteEstoqueException;
import br.com.verdureiralucas.exception.TipoItemNaoExisteEstoqueException;
import br.com.verdureiralucas.model.EstoqueItem;
import br.com.verdureiralucas.model.Item;
import br.com.verdureiralucas.model.ItemFactory;
import br.com.verdureiralucas.model.TipoItem;

public class ItemService {

    private final EstoqueItem estoque;

    public ItemService(EstoqueItem estoque) {
        this.estoque = estoque;
    }

    public void adicionarItem(ItemDto itemDto) {
        if (estoque.pegarTipoItem(itemDto.tipoItem()).isEmpty()) {
            estoque.adicionarTipoItem(itemDto.tipoItem());
        }

        Item item = transformarDtoEmModel(itemDto);
        estoque.adicionarItem(itemDto.tipoItem(), item);
    }

    public void removerItem(int index, TipoItem tipoItem) {
        verificarSeTipoItemExisteEstoque(tipoItem);

        estoque.removerItem(tipoItem, index);

    }

    public ItemDto pegarItem(int index, TipoItem tipoItem) {
        verificarSeTipoItemExisteEstoque(tipoItem);

        Item item = estoque.pegarItem(tipoItem, index)
                .orElseThrow(() -> new ItemNaoExisteEstoqueException("Tipo do item não possui no estoque"));

        return new ItemDto.ItemDtoBuilder(item).build();
    }

    private void verificarSeTipoItemExisteEstoque(TipoItem tipoItem) {
        estoque.pegarTipoItem(tipoItem).orElseThrow(
                () -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));
    }

    public void aumentarQuantidadeItem(int aumentarEm, int index, TipoItem tipoItem) {
        verificarSeTipoItemExisteEstoque(tipoItem);

        Item item = estoque.pegarItem(tipoItem, index).orElseThrow(
                () -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));
        item.aumentarQuantidade(index);
    }

    public List<ItemDto> pegarTodosItens(TipoItem tipoItem) {
        var lista = estoque.pegarTipoItem(tipoItem).orElseThrow(
                () -> new TipoItemNaoExisteEstoqueException("Tipo do item não possui no estoque", tipoItem));
        return transformarListaModelEmDto(lista);

    }

    private Item transformarDtoEmModel(ItemDto itemDto) {
        return ItemFactory.instanceTo(itemDto.nome(), itemDto.preco(), itemDto.descricao(), itemDto.quantidade(), itemDto.tipoItem());
    }

    private ItemDto transformarModelEmDto(Item item) {
        return new ItemDto.ItemDtoBuilder(item).build();
    }

    private List<ItemDto> transformarListaModelEmDto(List<Item> listaItem) {
        return listaItem.stream().map(this::transformarModelEmDto).collect(Collectors.toList());
    }

    public void removerItem(ItemDto itemDto, TipoItem tipoItem) {
        Item item = transformarDtoEmModel(itemDto);
        estoque.removerItem(tipoItem, item);

    }

    public ItemDto alterarItem(ItemEditDto itemEditDto, ItemDto itemDto) {
        Item itemAntigo = transformarDtoEmModel(itemDto);

        int indexItem = estoque.pegarNumeroId(itemAntigo);

        Item itemNovo = criarItemAlterado(itemDto, itemEditDto);

        estoque.alterarItem(indexItem, itemNovo);

        return transformarModelEmDto(itemNovo);
    }

    private Item criarItemAlterado(ItemDto itemDto, ItemEditDto itemEditDto) {
        return ItemFactory.instanceTo(itemEditDto.nome(), itemEditDto.preco(), itemEditDto.descricao(), itemDto.quantidade(), itemDto.tipoItem());
    }

    public ItemDto alterarQuantidadeItem(ItemMudancaQuantidadeRequest itemMudarQuantidade) {
        Item item = transformarDtoEmModel(itemMudarQuantidade.itemDto());
        int indexItem = estoque.pegarNumeroId(item);

        ItemDto itemDto = itemMudarQuantidade.alterarQuantidade();

        Item itemNovo = ItemFactory.instanceTo(itemDto.nome(), itemDto.preco(), itemDto.descricao(), itemDto.quantidade(), itemDto.tipoItem());

        estoque.alterarItem(indexItem, itemNovo);

        return itemDto;
    }
}
