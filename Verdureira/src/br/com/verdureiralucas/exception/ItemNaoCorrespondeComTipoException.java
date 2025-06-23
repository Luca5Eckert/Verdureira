package br.com.verdureiralucas.exception;

import br.com.verdureiralucas.model.TipoItem;

public class ItemNaoCorrespondeComTipoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final TipoItem tipoItem;
	private final TipoItem tipo;

	public ItemNaoCorrespondeComTipoException(String mensagem, TipoItem tipoItem, TipoItem tipo) {
		super(mensagem);
		this.tipoItem = tipoItem;
		this.tipo = tipo;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public TipoItem getTipo() {
		return tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
