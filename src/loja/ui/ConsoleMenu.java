package loja.ui;

import loja.model.produto.*;
import loja.model.cliente.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleMenu {
    private ProdutoService produtoService = new ProdutoService();
    private ClienteService clienteService = new ClienteService();
    private Scanner scanner = new Scanner(System.in);

    //--------------MENU----------------------
    public void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- MENU PRODUTOS ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Alterar Produto");
            System.out.println("3. Cadastrar Cliente");
            System.out.println("4. Alterar Cliente");
            System.out.println("5. Criar Nota de Compra");
            System.out.println("6. Listar Notas Emitidas");
            System.out.println("7. Listar Produtos");
            System.out.println("8. Listar Clientes");
            System.out.println("0. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer
            if (opcao < 0 || opcao > 8) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> atualizarProduto();
                case 3 -> cadastrarCliente();
                case 4 -> atualizarCliente();
                //case 5 -> criarNotaCompra();
                //case 6 -> listarNotasEmitidas();
                case 7 -> listarProdutos();
                case 8 -> listarClientes();
            }
        } while (opcao != 0);
    }


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

    private void cadastrarCliente() {
        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente novoCliente = new Cliente(id, nome, endereco, telefone);
        clienteService.adicionar(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }
    private void listarClientes() {
        System.out.println("\n--- CLIENTES CADASTRADOS ---");
        for (Cliente c : clienteService.listarTodos()) {
            System.out.println(c);
        }
    }
    private void atualizarCliente() {
        System.out.print("Digite o ID do cliente que deseja alterar: ");
        String id = scanner.nextLine();

        Cliente cliente = clienteService.buscarPorId(id);

        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        System.out.println("Cliente encontrado: " + cliente);

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();
        cliente.setNome(novoNome);

        System.out.print("Novo endereço: ");
        String novoEndereco = scanner.nextLine();
        cliente.setEndereco(novoEndereco);

        System.out.print("Novo telefone: ");
        String novoTelefone = scanner.nextLine();
        cliente.setTelefone(novoTelefone);

        System.out.println("Cliente atualizado com sucesso!");
    }
}