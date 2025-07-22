package loja.ui;

import loja.model.produto.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleMenu {
    private ProdutoService produtoService = new ProdutoService();
    private Scanner scanner = new Scanner(System.in);

    //--------------MENU----------------------

    private void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        BigDecimal preco = scanner.nextBigDecimal();

        System.out.println("Tipo (1-Físico | 2-Digital | 3-Perecível): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Produto novo = null;
        if (tipo == 1) {
            System.out.print("Estoque: ");
            int estoque = scanner.nextInt();
            novo = new ProdutoFisico(nome, preco, estoque);

        } else if (tipo == 2) {
            novo = new ProdutoDigital(nome, preco);

        } else if (tipo == 3) {
            System.out.print("Estoque: ");
            int estoque = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Validade (AAAA-MM-DD): ");
            LocalDate validade = LocalDate.parse(scanner.nextLine());
            novo = new ProdutoPerecivel(nome, preco, estoque, validade);
        }

        produtoService.adicionar(novo);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void listarProdutos() {
        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        for (Produto p : produtoService.listarTodos()) {
            System.out.println(p);
        }
    }
    private void atualizarProduto() {
        System.out.print("Digite o código do produto que deseja alterar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Produto produto = produtoService.buscarPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }
        System.out.println("Produto encontrado: " + produto);

        System.out.println("O que você deseja atualizar?");
        System.out.println("1. Preço");
        System.out.println("2. Estoque");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> {
                System.out.print("Novo preço: ");
                BigDecimal novoPreco = scanner.nextBigDecimal();
                produto.setPreco(novoPreco);
                System.out.println("Preço atualizado com sucesso!");
            }
            case 2 -> {
                if (produto.getTipo().equals("Digital")) {
                    System.out.println("Produto digital tem estoque infinito. Não é possível alterar.");
                    return;
                }
                System.out.print("Novo estoque: ");
                int novoEstoque = scanner.nextInt();
                produto.setEstoque(novoEstoque);
                System.out.println("Estoque atualizado com sucesso!");
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}