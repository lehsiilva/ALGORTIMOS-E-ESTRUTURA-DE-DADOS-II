import java.util.Scanner;

public class PilhaFlexivelInversa {
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

    private Celula topo;

    public PilhaFlexivelInversa(){
        this.topo = null;
    }

    public void push(int valor){
        Celula celula = new Celula(valor);
        celula.proximo = this.topo; 
        this.topo = celula;                              
    }

    public void inverter(){
        Celula anterior = null;
        Celula atual = this.topo;
        Celula proximo = null;

        while(atual != null){
            proximo = atual.proximo;
            atual.proximo = anterior;

            anterior = atual;
            atual = proximo;
        }

        this.topo = anterior;

    }

    public void mostrar(){

        Celula i = this.topo;

        while (i != null) {
            System.out.print(i.getValor() + " ");
            i = i.proximo;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PilhaFlexivelInversa pilha = new PilhaFlexivelInversa();
        int opc;

        do { 
            System.out.println("----- MENU -----");
            System.out.println("Digite 0 para SAIR");
            System.out.println("Digite 1 para INSERIR");
            System.out.println("Digite 2 para INVERTER");
            System.out.println("Digite 3 para MOSTRAR");
            System.out.print("Escolha uma opcao: ");

            opc = scanner.nextInt();

            switch(opc) {
                case 1:{
                    System.out.print("Digite o valor: ");
                    int valor = scanner.nextInt();
                    pilha.push(valor);
                    break;
                }
                case 2:{
                    pilha.inverter();                           
                    break;
                }
                case 3:
                    pilha.mostrar();
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (opc != 0);

    }
}
