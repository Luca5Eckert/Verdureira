package br.com.verdureiralucas.exception;

import br.com.verdureiralucas.model.TipoItem;

public class TipoItemNaoExisteEstoqueException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final TipoItem tipoItem;

    public TipoItemNaoExisteEstoqueException(String mensagem, TipoItem tipoItem) {
        super(mensagem);
        this.tipoItem = tipoItem;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

}
