package loja.model.produto;

import java.math.BigDecimal;

public class ProdutoFisico extends Produto {
    public ProdutoFisico(String nome, BigDecimal preco, int estoque) {
        super(nome, preco, estoque);
    }

    @Override
    public String getTipo() {
        return "Físico";
    }
}
