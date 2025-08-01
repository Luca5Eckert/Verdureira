package br.com.verdureiralucas.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import br.com.verdureiralucas.controller.ItemController;
import br.com.verdureiralucas.dto.ItemDto;
import br.com.verdureiralucas.exception.InsercaoInvalidaException;
import br.com.verdureiralucas.exception.ItemNaoExisteEstoqueException;
import br.com.verdureiralucas.exception.TipoItemNaoExisteEstoqueException;
import br.com.verdureiralucas.infraestrutura.TextUtils;
import br.com.verdureiralucas.model.TipoItem;

public class MenuListarItens implements Menu<String> {

    private final Scanner input;
    private final ItemController itemController;
    private TipoItem tipoSelecionado;

    public MenuListarItens(Scanner input, ItemController itemController) {
        this.input = input;
        this.itemController = itemController;
    }

    @Override
    public String mostrarMenu() {
        TipoItem tipoItem = mostrarMenuEscolhaItens();
        return mostrarMenuListar(tipoItem);
    }

    private String mostrarMenuListar(TipoItem tipoItem) {
        var listaItens = pegarItensTipo(tipoItem);
        tipoSelecionado = tipoItem;

        System.out.println("---------------------------------------------------");
        System.out.println("                     LISTAGEM                      ");
        System.out.println("---------------------------------------------------");
        System.out.println(" S - Sair");
        System.out.println(" Itens do tipo " + tipoItem.getEmString() + " :");

        if (listaItens.isPresent() && !listaItens.get().isEmpty()) {
            exibirItens(listaItens.get());
        } else {
            System.out.println("Estoque vazio para o tipo " + tipoItem.getEmString() + ".");
        }

        System.out.println("---------------------------------------------------");

        return input.nextLine().trim().toUpperCase();
    }

    private TipoItem mostrarMenuEscolhaItens() {
        while (true) {
            try {
                System.out.println("---------------------------------------------------");
                System.out.println("                     LISTAGEM                      ");
                System.out.println("---------------------------------------------------");
                System.out.println(" Qual tipo você quer listar: ");
                TipoItem.pegarValoresTipos();

                String linha = input.nextLine().trim();

                if (linha.isEmpty()) {
                    throw new InsercaoInvalidaException("Entrada vazia não é permitida", "");
                }

                int escolha = Integer.parseInt(linha);

                if (escolha >= TipoItem.values().length || escolha < 0) {
                    throw new IllegalArgumentException("Opção inválida. Escolha um número entre 0 e " + (TipoItem.values().length - 1));
                }

                return TipoItem.values()[escolha];

            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
            } catch (IllegalArgumentException | InsercaoInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Optional<List<ItemDto>> pegarItensTipo(TipoItem tipoItem) {
        try {
            List<ItemDto> listaItens = itemController.pegarTodosItens(tipoItem);
            return Optional.ofNullable(listaItens);
        } catch (ItemNaoExisteEstoqueException | TipoItemNaoExisteEstoqueException e) {
            return Optional.empty();
        }
    }

    private void exibirItens(List<ItemDto> itens) {
        if (itens.isEmpty()) {
            return;
        }

        for (int contador = 0; contador < itens.size(); contador++) {
            ItemDto itemDto = itens.get(contador);
            System.out.printf(" %d - %s    R$%.2f%n",
                    contador,
                    itemDto.nome(),
                    itemDto.preco());
        }
    }

    @Override
    public Menu<?> executarAcao(String textoEnviado) {
        if (textoEnviado == null || textoEnviado.isEmpty()) {
            return this;
        }

        if (textoEnviado.equals("S")) {
            return new MenuItemGeral(input);
        }

        return verificarInput(textoEnviado);
    }

    private Menu<?> verificarInput(String inserido) {
        if (inserido.equals("S")) {
            return new MenuItemGeral(input);
        }

        if (!TextUtils.eNumero(inserido)) {
            System.out.println("Entrada inválida. Digite 'S' para sair ou um número para selecionar um item.");
            return this;
        }

        try {
            return criarMenuDoItemSelecionado(Integer.parseInt(inserido));
        } catch (NumberFormatException e) {
            System.out.println("Erro ao processar o número. Por favor, tente novamente.");
            return this;
        }
    }

    private Menu<?> criarMenuDoItemSelecionado(int indexItemSelecionado) {
        Optional<ItemDto> itemDto = pegarItemSelecionado(indexItemSelecionado);

        if (itemDto.isEmpty()) {
            System.out.println("Item selecionado não encontrado. Por favor, verifique o índice e tente novamente.");
            return this;
        }

        return new MenuVisualizarItem(input, itemController, itemDto.get());
    }

    private Optional<ItemDto> pegarItemSelecionado(int indexItemSelecionado) {
        try {
            ItemDto itemDto = itemController.pegarItem(indexItemSelecionado, tipoSelecionado);
            return Optional.ofNullable(itemDto);
        } catch (TipoItemNaoExisteEstoqueException | ItemNaoExisteEstoqueException e) {
            System.out.println("Erro ao buscar item: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void limparScanner() {
        if (input.hasNextLine()) {
            input.nextLine();
        }
    }
}