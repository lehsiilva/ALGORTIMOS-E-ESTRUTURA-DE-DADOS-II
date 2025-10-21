import java.util.Scanner;

class Celula{
    private int valor;
    Celula px;

    public Celula(int valor){
        this.valor = valor;
        this.px = null;
    }

    public int getValor(){
        return valor;
    }
}

class Pilha{
    private Celula topo;

    public Pilha(){
        this.topo = null;
    }
    

     public void Push(int x){
        Celula celula = new Celula(x);
        celula.px = topo;
        topo = celula;
    }

    public int Pop(){
        if(topo == null){
            System.out.println("Pilha Vazia!");
            return -1;
        }

        int valorRemovido = topo.getValor();
        topo = topo.px;

        return valorRemovido;
    }

    public void mostrar(){
        Celula i = topo;

        System.out.print("Pilha: ");
            while(i != null){
                System.out.print( i.getValor() + " ");
                i = i.px;
            }
            System.out.println();
    }
}

class main{
    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {
            Pilha pilha = new Pilha();
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
                        pilha.Push(valor);
                    }
                    case 2 -> {
                        int resul = pilha.Pop();
                        if (resul != -1){
                            System.out.println("Valor removido: " + resul);
                        }
                    }
                    case 3 -> pilha.mostrar();
                    default -> throw new AssertionError();
                }
                
            } while (opc != 0);
        }
    }
    
}
