import java.util.Scanner;
class Celula{
    private int valor;
    Celula proximo;

    public Celula(){
        this(0);
    }

    public Celula(int valor){
        this.valor = valor;
        this.proximo = null;
    }

    public int getValor(){
        return valor;
    }
}

class FilaFlexivel{
    private Celula inicio;
    private Celula fim;

    public FilaFlexivel(){
        inicio = new Celula();
        fim = inicio;
    }

    public void enqueue(int valor){

        fim.proximo = new Celula(valor);
        fim = fim.proximo;
    }

    public int dequeue(){
        if(inicio == fim){
            System.out.println("Fila Vazia");
        }

        Celula tmp = inicio.proximo;
        int valor = tmp.getValor();
        inicio.proximo = tmp.proximo;

        return valor;

    }

    public void mostrar(){
        if(inicio == fim){
            System.out.println("Fila Vazia");
        }

        Celula i = inicio.proximo;

        System.out.print("Fila: ");
        while(i != null){
            System.out.print(i.getValor() + " ");
            i = i.proximo;
        }
        System.out.println();
    }
}

public class main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FilaFlexivel fila = new FilaFlexivel();
        int opc;

        do { 
            System.out.println("----- MENU -----");
            System.out.println("Digite 0 para SAIR");
            System.out.println("Digite 1 para INSERIR");
            System.out.println("Digite 2 para DELETAR");
            System.out.println("Digite 3 para MOSTRAR");
            System.out.print("Escolha uma opcao: ");

            opc = scanner.nextInt();

            switch(opc) {
                case 1 -> {
                    System.out.print("Digite o valor: ");
                    int valor = scanner.nextInt();
                    fila.enqueue(valor);
                }
                case 2 -> {
                    int resul = fila.dequeue();
                    System.out.println("Valor removido: " + resul);
                }
                case 3 -> fila.mostrar();
                default -> throw new AssertionError();
            }
            
        } while (opc != 0);

        scanner.close();

    }

}
