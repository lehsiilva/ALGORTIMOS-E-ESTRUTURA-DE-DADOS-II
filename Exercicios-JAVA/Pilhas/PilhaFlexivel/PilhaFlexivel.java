import java.util.Scanner;

public class PilhaFlexivel {

    class Celula {
        private int valor;
        private Celula proximo; 

        public Celula(int valor) {
            this.valor = valor;
            this.proximo = null; 
        }

        public int getValor(){
            return valor;
        }
    }

    private Celula topo;

    public PilhaFlexivel() {
        this.topo = null; 
    }



    public void Push(int x){
        Celula novaCelula = new Celula(x); 
        novaCelula.proximo = this.topo;
        this.topo = novaCelula;
    }

    public int Pop(){
        if(topo == null){
            System.out.println("Pilha Vazia");
        }
            int valorRemovido = this.topo.getValor();
            Celula celulaRemovida = this.topo;
            this.topo = this.topo.proximo;
            celulaRemovida.proximo = null;
        

        return valorRemovido;
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

        PilhaFlexivel pilha = new PilhaFlexivel();
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
                    pilha.Push(valor);
                    break;
                }
                case 2:{
                    int resul = pilha.Pop();
                    System.out.println("Valor removido: " + resul);
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