import java.util.Scanner;

class CelulaDupla{
    private int valor;
    CelulaDupla px;
    CelulaDupla ant;

    public CelulaDupla(){
        this(0);
    }

    public CelulaDupla(int valor){
        this.valor = valor;
        this.px = null;
        this.ant = null;
    }

    public int getValor(){
        return valor;
    }
    
}

class ListaDupla{
    CelulaDupla inicio;
    CelulaDupla fim;

    public ListaDupla(){ //Celula cabeca
        inicio = new CelulaDupla();
        fim = inicio;
    }

    public int tamanho(){
        int tam = 0;

        CelulaDupla i = inicio.px;

        while( i != null){
            tam++;
            i = i.px;
        }


        return tam;
    }
    public void inserirInicio(int x){
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = inicio;
        tmp.px = inicio.px;

        if(inicio.px != null){
            inicio.px.ant = tmp;
        }else{
            fim = tmp;
        }

        inicio.px = tmp;
    }

    public void inserirFim(int x){
        CelulaDupla tmp = new CelulaDupla(x);
        fim.px = tmp;
        tmp.ant = fim;
        tmp.px = null;
        fim = tmp;

    }

    public void inserirEscolhendo(int x, int pos){
        int tam = tamanho();

        if(inicio == fim){
            System.out.println("Lista vazia");
        }else if (pos < 0 || pos > tam) {
            System.out.println("Posicao invalida");
        }else if (pos == 0) {
            inserirInicio(x);
        }else if (pos == tam) {
            inserirFim(x);
        }else{
            CelulaDupla i = inicio;

            for(int j = 0; j < pos; j++){
                i = i.px;
            }

            CelulaDupla tmp = new CelulaDupla(x);
            tmp.px = i;
            tmp.ant = i.ant;

            i.ant.px = tmp;
            i.ant = tmp;
        }
    }


    public int removerInicio(){

        if(inicio == fim){
            System.out.println("Lista vazia");
            return -1;
        }

        CelulaDupla tmp = inicio.px;
        int remover = tmp.getValor();
        inicio.px = tmp.px;


        if(inicio.px != null){
            inicio.px.ant = inicio;
        }else{
            fim = inicio;
        }

        return remover;

    }

    public int removerFim(){
        
        if(inicio == fim){
            System.out.println("Lista vazia");
            return -1;
        }

        int remover = fim.getValor();
        fim = fim.ant;
        fim.px = null;

        return remover;
    }

    public int removerEscolhendo(int pos){
        int tam = tamanho();
        int elemento = 0;

        pos = pos - 1;

        if(inicio == fim){
            System.out.println("Lista vazia");
            return - 1;
        }else if (pos < 0 || pos == tam) {
            System.out.println("Posicao invalida");
            return -1;
        }else if (pos == 0) {
            elemento = removerInicio();
        }else if (pos == tam - 1) {
            elemento = removerFim();
        }else{
            CelulaDupla i = inicio.px;

            for(int j = 0; j < pos; j++){
                i = i.px;
            }

            elemento = i.getValor();
            i.ant.px = i.px;
            i.px.ant = i.ant;

        }

        return elemento;
    }

    public void mostrar(){
        if(inicio == fim){
            System.out.println("Lista Vazia!");
        }

        CelulaDupla i = inicio.px;

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
            ListaDupla lista = new ListaDupla();
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
