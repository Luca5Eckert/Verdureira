package br.com.verdureiralucas.exception;

public class InsercaoInvalidaException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final String insercao;

    public InsercaoInvalidaException(String mensagem, String insercao) {
        super(mensagem);
        this.insercao = insercao;
    }

    public String getInsercao() {
        return insercao;
    }

}
