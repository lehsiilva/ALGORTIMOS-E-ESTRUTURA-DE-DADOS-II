import java.util.Scanner;

public class FilaFlexivel {
    class Celula{
        private int valor;
        private Celula proximo;

        public Celula(int valor){
            this.valor = valor;
            this.proximo = null;
        }

        public int getValor(){
            return valor;
        }
    }

    private Celula inicio;
    private Celula fim;

    public FilaFlexivel(){
        this.inicio = null;
        this.fim = null;
    }

    public void enqueue(int valor){

        Celula celula = new Celula(valor);

        if(inicio == null){

            inicio = celula;

        }else{

            fim.proximo = celula;
        }

        fim = celula;
    }

    public int dequeue(){
        if(this.inicio == null){
            System.out.println("Fila Vazia");
        }

        int valor = this.inicio.getValor();
        this.inicio = this.inicio.proximo;

        if(this.inicio == null){
            this.fim = null;
        }


        return valor;

    }

    public void mostrar(){
        if(inicio == null){
            System.out.println("Fila Vazia");
        }

        Celula i = inicio;

        System.out.print("Fila: ");
        while(i != null){
            System.out.print(i.getValor() + " ");
            i = i.proximo;
        }
        System.out.println();
    }


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
                case 1:{
                    System.out.print("Digite o valor: ");
                    int valor = scanner.nextInt();
                    fila.enqueue(valor);
                    break;
                }
                case 2:{
                    int resul = fila.dequeue();
                    System.out.println("Valor removido: " + resul);
                    break;
                }
                case 3:
                    fila.mostrar();
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (opc != 0);

    }

}
