import java.util.ArrayList;
import java.util.Scanner;

public class Produto {
    private String nome;
    private double valor;
    private int quantidadeEstoque;

    public Produto(String nome, double valor, int quantidadeEstoque) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void escreverDados(int indice) {
        System.out.println("\nProduto #" + (indice + 1));
        System.out.println("Nome: " + nome);
        System.out.println("Valor unitário: R$ " + valor);
        System.out.println("Quantidade em estoque: " + quantidadeEstoque);
        System.out.println("Valor total em estoque: R$ " + calcularValorTotal());
    }
    public double calcularValorTotal() {
        return valor * quantidadeEstoque;
    }

    public void alterarQuantidade(int quantidade) {
        this.quantidadeEstoque += quantidade;

        if (this.quantidadeEstoque < 0) {
            this.quantidadeEstoque = 0;
            System.out.println("⚠ Estoque não pode ser negativo. Ajustado para 0.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Adicionar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Alterar quantidade de um produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite o valor unitário: ");
                    double valor = sc.nextDouble();

                    System.out.print("Digite a quantidade em estoque: ");
                    int quantidade = sc.nextInt();

                    Produto p = new Produto(nome, valor, quantidade);
                    listaProdutos.add(p);

                    System.out.println("✅ Produto adicionado com sucesso!");
                    break;

                case 2:
                    if (listaProdutos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (int i = 0; i < listaProdutos.size(); i++) {
                            listaProdutos.get(i).escreverDados(i);
                        }
                    }
                    break;

                case 3:
                    if (listaProdutos.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        System.out.println("\nEscolha o produto para alterar:");
                        for (int i = 0; i < listaProdutos.size(); i++) {
                            System.out.println((i + 1) + " - " + listaProdutos.get(i).nome);
                        }

                        System.out.print("Número do produto: ");
                        int indice = sc.nextInt() - 1;

                        if (indice >= 0 && indice < listaProdutos.size()) {
                            System.out.print("Digite a quantidade a alterar (+ para adicionar, - para remover): ");
                            int alteracao = sc.nextInt();
                            listaProdutos.get(indice).alterarQuantidade(alteracao);
                            System.out.println("✅ Quantidade alterada com sucesso!");
                        } else {
                            System.out.println("Produto inválido.");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
