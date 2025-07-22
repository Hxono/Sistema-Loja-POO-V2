package loja.model.produto;

import java.math.BigDecimal;

public abstract class Produto {
    private static int contador = 1;
    private final int codigo;
    private String nome;
    private BigDecimal preco;
    private int estoque;

    public Produto(String nome, BigDecimal preco, int estoque) {
        this.codigo = contador++;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return "[" + getTipo() + "] Código: " + codigo + ", Nome: " + nome + ", Preço: R$" + preco + ", Estoque: " + estoque;
    }
}
