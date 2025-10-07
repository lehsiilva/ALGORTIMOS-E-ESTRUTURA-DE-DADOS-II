import java.util.Scanner;

public class Produto {
    private String nome;
    private double preco;
    private int qntd;

    // Construtor padrão
    Produto() {
        this.nome = "";
        this.preco = 0.0;
        this.qntd = 0;
    }

    // Sets
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    // Gets
    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQntd() {
        return qntd;
    }

    // Métodos de estoque
    public void adicionarEstoque(int adicionar) {
        qntd += adicionar;
        System.out.println(adicionar + " unidades adicionadas. Quantidade atual: " + qntd);
    }

    public void removerEstoque(int remover) {
        if (remover <= qntd) {
            qntd -= remover;
            System.out.println(remover + " unidades removidas. Quantidade atual: " + qntd);
        } else {
            System.out.println("Erro: quantidade a remover é maior que a disponível.");
        }
    }

    public double valorTotalEmEstoque() {
        return preco * qntd;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Produto produto = new Produto();

        produto.setNome("Bolinha");
        produto.setPreco(2.99);

        int opc;
        do {
            System.out.println("\nProduto: " + produto.getNome());
            System.out.println("Preço: R$" + produto.getPreco());
            System.out.println("Quantidade em estoque: " + produto.getQntd());
            System.out.println("Valor total em estoque: R$" + produto.valorTotalEmEstoque());
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Adicionar estoque");
            System.out.println("2 - Remover estoque");
            System.out.println("3 - Ver valor total");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    System.out.print("Digite quantos deseja adicionar: ");
                    int adicionar = scanner.nextInt();
                    produto.adicionarEstoque(adicionar);
                    break;
                case 2:
                    System.out.print("Digite quantos deseja remover: ");
                    int remover = scanner.nextInt();
                    produto.removerEstoque(remover);
                    break;
                case 3:
                    System.out.println("Valor total em estoque: R$" + produto.valorTotalEmEstoque());
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opc != 0);

        scanner.close();
    }
}
