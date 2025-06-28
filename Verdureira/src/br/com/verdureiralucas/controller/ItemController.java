package br.com.verdureiralucas.controller;

import java.util.List;

import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.dto.ItemEditDto;
import br.com.verdureiralucas.dto.ItemMudancaQuantidadeRequest;
import br.com.verdureiralucas.model.TipoItem;
import br.com.verdureiralucas.service.ItemService;

public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    public void adicionarItem(ItemDto itemDto) {
        itemService.adicionarItem(itemDto);
    }

    public void removerItem(int index, TipoItem tipoItem) {
        itemService.removerItem(index, tipoItem);
    }

    public void removerItem(ItemDto itemDto) {
        itemService.removerItem(itemDto, itemDto.tipoItem());
    }

    public void alterarItem(ItemEditDto itemEditDto, ItemDto itemDto) {
        itemService.alterarItem(itemEditDto, itemDto);
    }

    public ItemDto pegarItem(int index, TipoItem tipoItem) {
        return itemService.pegarItem(index, tipoItem);
    }

    public List<ItemDto> pegarTodosItens(TipoItem tipoItem) {
        return itemService.pegarTodosItens(tipoItem);
    }

    public void alterarEstoque(ItemMudancaQuantidadeRequest itemMudancaQuantidadeRequest) {
        itemService.alterarQuantidadeItem(itemMudancaQuantidadeRequest);
    }
}
