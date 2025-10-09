import java.util.Scanner;

public class PilhaSimples {
    private int tam;
    private int tamTotal;
    private int[]vet;

    public PilhaSimples() {
        this.tam = 0;
        this.tamTotal = 7;
        this.vet = new int[tamTotal];
    }

    public void push(int valor){
        if(tam < tamTotal){
            vet[tam] = valor;
            tam++;
        }else{
            System.out.println("Fila Cheia");
        }
    }

    public void pop(){
        if(tam == 0){
            System.out.println("Lista Vazia");
        }else{
            tam--;
        }
    }

    public void mostrar(){
        if (tam == 0) {
            System.out.println("Pilha Vazia!");
        } else {
            System.out.println("Conteúdo da pilha:");
            for (int i = tam - 1; i >= 0; i--) { //Mostra invertido
                System.out.print(vet[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PilhaSimples pilha = new PilhaSimples();
        int opc;

        do{ 
            System.out.println("----- MENU -----");
            System.out.println("Digite 0 para SAIR");
            System.out.println("Digite 1 para INSERIR");
            System.out.println("Digite 2 para REMOVER");
            System.out.println("Digite 3 para MOSTRAR");
            System.out.print("Digite uma opcao: ");
        
            int valor;
            opc = scanner.nextInt();

            switch (opc) {
                case 1:
                    System.out.print("Digite o valor a ser inserido: ");
                    valor = scanner.nextInt();
                    pilha.push(valor);
                    break;

                case 2:
                    pilha.pop();
                    break;
                case 3:
                    pilha.mostrar();
                    break;

                default:
                    throw new AssertionError();
            }
        } while (opc != 0);
    }
    
}
