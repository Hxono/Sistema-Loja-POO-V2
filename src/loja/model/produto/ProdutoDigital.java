package loja.model.produto;

import java.math.BigDecimal;

public class ProdutoDigital extends Produto {
    public ProdutoDigital(String nome, BigDecimal preco) {
        super(nome, preco, Integer.MAX_VALUE);
    }

    @Override
    public String getTipo() {
        return "Digital";
    }
}
