//Faça um metodo que mostre a soma dos elementos da fila

import java.util.Scanner;

public class SomaElementosFila {
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

    public SomaElementosFila() {
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

    public void inverter(){

        if (this.inicio == null || this.inicio == this.fim) {
            return;
        }

    
        Celula anterior = null;
        Celula atual = this.inicio;
        Celula proximo = null;

        
        Celula novoFim = this.inicio;

        
        while (atual != null) {
            
            proximo = atual.proximo; 
            
          
            atual.proximo = anterior; 
            
        
            anterior = atual;
            atual = proximo;
        }

       
        this.inicio = anterior; 
        this.fim = novoFim; 

    }

    public int dequeue(){
        if(this.inicio == null){
            this.fim = null;
            System.out.println("Fila Vazia");
        }

        int celulaRemovida = this.inicio.getValor();
        this.inicio = this.inicio.proximo;

        return celulaRemovida;
    }

    public int soma(){

        Celula i = inicio;
        int soma = 0;

        while(i != null){
            soma += i.getValor();

            i = i.proximo;
        }

        return soma;

    }

    public void mostrar(){
        Celula i = inicio;

        if(this.inicio == null){
            System.out.println("Fila Vazia");
        }else{
            System.out.print("Fila: ");
            while(i != null){
                System.out.print(i.getValor() + " ");

                i = i.proximo;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SomaElementosFila fila = new SomaElementosFila();

        int opc;

        do { 
            System.out.println("----- MENU -----");
            System.out.println("Digite 0 para SAIR");
            System.out.println("Digite 1 para INSERIR");
            System.out.println("Digite 2 para DELETAR");
            System.out.println("Digite 3 para MOSTRAR SOMA");
            System.out.println("Digite 4 para MOSTRAR");
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
                case 3:{
                    int resul = fila.soma();
                    System.out.println("Soma: " + resul);
                    break;
                }
                case 4:
                    fila.mostrar();
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (opc != 0);

    }
}
