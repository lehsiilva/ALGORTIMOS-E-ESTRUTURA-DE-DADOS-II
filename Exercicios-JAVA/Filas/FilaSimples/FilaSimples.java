import java.util.Scanner;

public class FilaSimples {
    private int tam;
    private int tamTotal;
    private int[] vet;

    public FilaSimples() {
        this.tam = 0;
        this.tamTotal = 7;
        this.vet = new int[tamTotal];
    }

    public void enqueue(int valor){
        if(tam < tamTotal){
            vet[tam] = valor;
            tam++;
        }else{
            System.out.println("Fila Cheia");
        }
    }

    public void dequeue() {
        if (tam == 0) {
            System.out.println("Fila vazia!");
        } else {
            
            for (int i = 0; i < tam - 1; i++) {
                vet[i] = vet[i + 1];
            }
            tam--;
        }
    }

    public void mostrar(){
      
            for(int i = 0; i < tam; i++){
                System.out.print(vet[i] + " ");
            }
          
            System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FilaSimples fila = new FilaSimples();
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
                    System.out.println("Digite o valor a ser inserido");
                    valor = scanner.nextInt();
                    fila.enqueue(valor);
                    break;

                case 2:
                    fila.dequeue();
                    break;
                case 3:
                    fila.mostrar();
                    break;

                default:
                    throw new AssertionError();
            }
        } while (opc != 0);
    }

    
}
