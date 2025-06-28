package br.com.verdureiralucas.exception;

public class ItemNaoExisteEstoqueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNaoExisteEstoqueException(String mensagem) {
		super(mensagem);
	}

}
