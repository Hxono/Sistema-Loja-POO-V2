package loja.model.produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> listarTodos() {
        return produtos;
    }

    public Produto buscarPorCodigo(int codigo) {
        return produtos.stream()
                .filter(p -> p.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }
}
