import java.util.Scanner;

// Classe Lista
class Lista {
    private int tam;
    private int tamTotal;
    private int[] vet;

    public Lista() {
        this.tam = 0;
        this.tamTotal = 7;
        this.vet = new int[tamTotal];
    }

    // INSERIR NO FIM (normal)
    public void inserir(int valor) {
        if (tam < tamTotal) {
            vet[tam] = valor;
            tam++;
        } else {
            System.out.println("Lista cheia!");
        }
    }

    // INSERIR NO INICIO
    public void inserirInicio(int valor) {
        if (tam < tamTotal) {
            for (int j = tam; j > 0; j--) {
                vet[j] = vet[j - 1];
            }
            vet[0] = valor;
            tam++;
        } else {
            System.out.println("Lista cheia!");
        }
    }

    // INSERIR EM POSIÇÃO ESCOLHIDA
    public void inserirEscolhendo(int valor, int posicao) {
        if (tam >= tamTotal) {
            System.out.println("Lista cheia!");
            return;
        }
        if (posicao < 0 || posicao > tam) {
            System.out.println("Posição inválida!");
            return;
        }
        for (int j = tam; j > posicao; j--) {
            vet[j] = vet[j - 1];
        }
        vet[posicao] = valor;
        tam++;
    }

    // REMOVER DO INICIO
    public void removerInicio() {
        if (tam == 0) {
            System.out.println("Lista vazia!");
            return;
        }
        for (int j = 0; j < tam - 1; j++) {
            vet[j] = vet[j + 1];
        }
        tam--;
    }

    // REMOVER DO FIM
    public void removerFim() {
        if (tam == 0) {
            System.out.println("Lista vazia!");
            return;
        }
        tam--;
    }

    // REMOVER EM POSIÇÃO ESCOLHIDA
    public void removerEscolhendo(int posicao) {
        if (tam == 0) {
            System.out.println("Lista vazia!");
            return;
        }
        if (posicao < 0 || posicao >= tam) {
            System.out.println("Posição inválida!");
            return;
        }
        for (int j = posicao; j < tam - 1; j++) {
            vet[j] = vet[j + 1];
        }
        tam--;
    }

    // MOSTRAR LISTA
    public void mostrar() {
        if (tam == 0) {
            System.out.println("Lista vazia!");
            return;
        }
        System.out.print("Lista: ");
        for (int j = 0; j < tam; j++) {
            System.out.print(vet[j] + " ");
        }
        System.out.println();
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista lista = new Lista();
        int opc;

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("0 - SAIR");
            System.out.println("1 - INSERIR NORMAL (FIM)");
            System.out.println("2 - INSERIR NO INICIO");
            System.out.println("3 - INSERIR ESCOLHENDO POSICAO");
            System.out.println("4 - REMOVER INICIO");
            System.out.println("5 - REMOVER FIM");
            System.out.println("6 - REMOVER ESCOLHENDO POSICAO");
            System.out.println("7 - MOSTRAR LISTA");
            System.out.print("Escolha uma opcao: ");

            opc = scanner.nextInt();
            int valor, posicao;

            switch (opc) {
                case 1:
                    System.out.print("Digite o valor: ");
                    valor = scanner.nextInt();
                    lista.inserir(valor);
                    break;
                case 2:
                    System.out.print("Digite o valor: ");
                    valor = scanner.nextInt();
                    lista.inserirInicio(valor);
                    break;
                case 3:
                    System.out.print("Digite o valor e a posição: ");
                    System.out.print("Digite a posição: ");
                    valor = scanner.nextInt();
                    posicao = scanner.nextInt();
                    lista.inserirEscolhendo(valor, posicao);
                    break;
                case 4:
                    lista.removerInicio();
                    break;
                case 5:
                    lista.removerFim();
                    break;
                case 6:
                    System.out.print("Digite a posição: ");
                    posicao = scanner.nextInt();
                    lista.removerEscolhendo(posicao);
                    break;
                case 7:
                    lista.mostrar();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opc != 0);

        scanner.close();
    }
}
