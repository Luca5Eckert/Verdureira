package br.com.verdureiralucas.model;

public class ProdutoDto {

    int id;
    String nome;
    String tipo;
    int litros;
    int fdfsa;

    public ProdutoDto(ProdutoDtoBuilder produtoDtoBuilder){
        this.id = produtoDtoBuilder.id;
        this.nome = produtoDtoBuilder.nome;
        this.litros = produtoDtoBuilder.litros;
    }

    public static class ProdutoDtoBuilder {

        int id;
        String nome;

        int litros;
        int fdfsa;

        public ProdutoDtoBuilder(int id, String nome){
            this.id = id;
            this.nome = nome;
        }

        public ProdutoDtoBuilder setLitros(int litros){
            this.litros = litros;
            return this;
        }
        public ProdutoDtoBuilder setfdfsa(){
            this.fdfsa = 42342;
            return this;
        }


        public ProdutoDto build(){
            return new ProdutoDto(this);
        }





    }

    public static void main(String[] args) {
        ProdutoDto fruta = new ProdutoDtoBuilder(1,"sdfgs").build();

    }



}
