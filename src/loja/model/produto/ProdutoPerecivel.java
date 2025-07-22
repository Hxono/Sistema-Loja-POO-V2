package loja.model.produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private LocalDate validade;

    public ProdutoPerecivel(String nome, BigDecimal preco, int estoque, LocalDate validade) {
        super(nome, preco, estoque);
        this.validade = validade;
    }

    public LocalDate getValidade() {
        return validade;
    }

    @Override
    public String getTipo() {
        return "Perecível";
    }

    @Override
    public String toString() {
        return super.toString() + ", Validade: " + validade;
    }
}
