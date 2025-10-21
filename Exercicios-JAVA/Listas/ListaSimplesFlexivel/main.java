import java.util.Scanner;
class Celula{
    private int valor;
    Celula px;

    public Celula(){
        this(0);
    }

    public Celula(int valor){
        this.valor = valor;
        this.px = null;
    }

    public int getValor(){
        return valor;
    }
}

class ListaFlexivel{
    private Celula inicio;
    private Celula fim;

    public ListaFlexivel(){ //Celula Cabeça
        inicio = new Celula();
        fim = inicio;
    }

    public int tamanho(){
        int tam = 0;

        Celula i = inicio.px;

        while( i != null){
            tam++;
            i = i.px;
        }


        return tam;
    }
    public void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.px = inicio.px;
        inicio.px = tmp;

        if (fim == inicio) {
        fim = tmp;
    }
        
    }

    public void inserirFim(int x){
        fim.px = new Celula(x);
        fim = fim.px;
    }

    public void inserirEscolhendo(int x, int posicao){
        int tam = tamanho();
        if(posicao < 0 || posicao > tam) {
            System.out.println("Posicao invalida!");
        }else if(posicao == 0){
            inserirInicio(x);
        }else if(posicao == tam){
            inserirFim(x);
        }else{
            Celula i = inicio;
            for (int j = 0; j < posicao; j++) {
                i = i.px;
            }

            Celula tmp = new Celula(x);
            tmp.px = i.px;
            i.px = tmp;
                
        }
    }


    public int removerInicio(){
        if(inicio == fim){
            System.out.println("Lista Vazia!");
            return -1;
        }

        Celula tmp = inicio.px;
        int valorRemovido = tmp.getValor();
        inicio.px = tmp.px;

        if (inicio.px == null) { // lista ficou vazia
            fim = inicio;
        }

        return valorRemovido;
    }

    public int removerFim(){
        if(inicio == fim){
            System.out.println("Lista Vazia!");
            return -1;
        }

        Celula i = inicio;

        while (i.px != fim) { // chega no penúltimo
            i = i.px;
        }

        int valorRemovido = fim.getValor();
        i.px = null;
        fim = i; // atualiza o fim
    

        return valorRemovido;
    }

    public int removerEscolhendo(int posicao){
        int tam = tamanho();
        int elemento = 0;

        if(inicio == fim){
            System.out.println("Lista Vazia!");
            return -1;
        }else if(posicao < 0 || posicao > tam){
            System.out.println("Posicao invalida!");
            return -1;
        }else if(posicao == 0){
            elemento = removerInicio();
        }else if(posicao == tam-1){
            elemento = removerFim(); 
        }else{
            Celula i = inicio;

            for(int j = 0; j < posicao; j++){
                i = i.px;
            }

            Celula tmp = i.px;
            elemento = tmp.getValor();
            i.px = tmp.px;
        }

        return elemento;
    }

    public void mostrar(){
        if(inicio == fim){
            System.out.println("Lista Vazia!");
        }

        Celula i = inicio.px;

        System.out.print("Lista: ");
        while(i != null){
            System.out.print(i.getValor() + " ");
            i = i.px;
        }
        System.out.println();
    }
}

public class main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ListaFlexivel lista = new ListaFlexivel();
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
                int valor, posicao, resul;
                
                switch (opc) {
                    case 1 -> {
                        System.out.print("Digite o valor: ");
                        valor = scanner.nextInt();
                        lista.inserirFim(valor);
                    }
                    case 2 -> {
                        System.out.print("Digite o valor: ");
                        valor = scanner.nextInt();
                        lista.inserirInicio(valor);
                    }
                    case 3 -> {
                        System.out.print("Digite o valor: ");
                        valor = scanner.nextInt();
                        System.out.print("Digite a posição: ");
                        posicao = scanner.nextInt();
                        lista.inserirEscolhendo(valor, posicao);
                    }
                    case 4 -> {
                        resul = lista.removerInicio();
                        if(resul != -1){
                            System.out.println("Valor removido: " + resul);
                        }
                    }
                    case 5 -> {
                        resul = lista.removerFim();
                        if(resul != -1){
                            System.out.println("Valor removido: " + resul);
                        }
                    }
                    case 6 -> {
                        System.out.print("Digite a posição: ");
                        posicao = scanner.nextInt();
                        resul = lista.removerEscolhendo(posicao);
                        if(resul != -1){
                            System.out.println("Valor removido: " + resul);
                        }
                    }
                    case 7 -> lista.mostrar();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
                
            } while (opc != 0);
        }
    }
}
