package loja.model.nota;

import loja.model.cliente.Cliente;
import loja.model.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Nota {
    private static int contador = 1;

    private String id;
    private LocalDate dataEmissao;
    private Cliente cliente;
    private List<ItemNota> produtos;

    public Nota(Cliente cliente){
        this.id = String.format("N%05d", contador++);
        this.dataEmissao = LocalDate.now();
        this.cliente = cliente;
        this.produtos = new ArrayList<>();
    }

    public String getId(){
        return id;
    }

    public LocalDate getDataEmissao(){
        return dataEmissao;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public List<ItemNota> getProdutos(){
        return produtos;
    }

    public void adicionarProduto(Produto produto, int quantidade){
        if(quantidade <= 0 ){
            System.out.println("ERRO: A quantidade de prosutos não pode ser inferior a zero!!!");
            return;
        }

        ItemNota novoProduto = new ItemNota(produto, quantidade);
        this.produtos.add(novoProduto);
    }

    public BigDecimal calcularSubTotal(){
        BigDecimal subTotal = BigDecimal.ZERO;
        
        for(ItemNota produto : produtos){
            subTotal = subTotal.add(produto.calcularSubtotalProduto()); 
        }
        return subTotal;
    }

    public BigDecimal calcularTotal(){
        return calcularSubTotal();
    }

    public void exibirResumo(){
        System.out.println(">>> NOTA DE COMPRA <<<");
        System.out.println("ID da Nota: " + id);
        System.out.println("Data da Emissão: " + dataEmissao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Cliente: " + cliente.getNome() + " (ID: " + cliente.getId() + ")");
        System.out.println("\nITENS:");

        if(produtos.isEmpty()){
            System.out.println(" Nenhum item adicionado.");
        } else {
            for(int i = 0; i < produtos.size(); i++){
                ItemNota produto = produtos.get(i);
                System.out.println(String.format(" %d. %s (Qtd: %d) - Preço Unit: %.2f - Subtotal Produto: %.2f", 
                    i+1, 
                    produto.getProduto().getNome(), 
                    produto.getQuantidade(), 
                    produto.getProduto().getPreco(), 
                    produto.calcularSubtotalProduto()));
            }
            System.out.println("\n--------------------");
            System.out.println("SUBTOTAL: R$ " + String.format("%.2f", calcularSubTotal()));
            System.out.println("TOTAL: R$ " + String.format("%.2f", calcularTotal()));
            System.out.println("--------------------");
        }
        
    }
}