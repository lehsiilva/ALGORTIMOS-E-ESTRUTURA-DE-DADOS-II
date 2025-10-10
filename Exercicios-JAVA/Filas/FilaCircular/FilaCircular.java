import java.util.Scanner;

public class FilaCircular {
    private int inicio;
    private int fim;
    private int totalTam;
    private int[]vet;

    public FilaCircular() {
        this.inicio = 0;
        this.fim = 0;
        this.totalTam = 5;
        this.vet = new int[totalTam + 1];
    }

    public void Inserir(int valor){
        if((fim + 1) % vet.length == inicio){
            System.out.println("Fila Cheia!");
        }else{
            vet[fim] = valor;
            fim = (fim + 1) % vet.length;
        }
    }

    public int Remover() {
    if (inicio == fim) {
        System.out.println("Fila Vazia");
        return -1; 
    } else {
        int valor = vet[inicio];
        inicio = (inicio + 1) % vet.length;
        return valor;
    }
}

    public void mostrar(){

        int i = inicio;
        while (i != fim) {
            System.out.print(vet[i] + " ");
            i = (i + 1) % vet.length;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaCircular fila = new FilaCircular();
        int opcao;

        do {
            System.out.println("\n===== MENU FILA CIRCULAR =====");
            System.out.println("1 - Inserir elemento");
            System.out.println("2 - Remover elemento");
            System.out.println("3 - Mostrar fila");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a inserir: ");
                    int valor = scanner.nextInt();
                    fila.Inserir(valor);
                    break;

                case 2:
                    int resul = fila.Remover();
                    System.out.println("Valor removido: " + resul);
                    break;

                case 3:
                    System.out.println("Fila atual:");
                    fila.mostrar();
                    break;

                case 0:
                    System.out.println("Encerrando programa...");
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}


/* Exemplo:
 * (fim + 1) % tamArray == inicio   ** array cheio **
 * inicio == fim    ** array vazio **
 * para percorrer: (fim + 1) % tamArray ou (inicio + 1) % tamArray
 * 0 % 5 = 0
 * 1 % 5 = 1
 * 2 % 5 = 2
 * 3 % 5 = 3
 * 4 % 5 = 4
 */

