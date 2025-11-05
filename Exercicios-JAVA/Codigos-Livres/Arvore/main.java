/* Considere a implementação de uma BST em que os nos armazenam numeros inteiros. Na BST vista em sala de aula, não eram aceitos elementos repetidos. Para este exercicios, voce deve modificar a BST para que, ao inserir um numero repetido , em vez de ignorar a inserção, a arvore armazene a quantidade de vezes que cada numero foi inserido. */

import java.util.Scanner;

class No{
    int valor;
    int cont;
    No esq;
    No dir;

    public No(int valor){
        this.valor = valor;
        this.cont = 1;
        this.esq = null;
        this.dir = null;
    }

    public int getValor(){
        return valor;
    }
}

class arvore{
    private No raiz;

    public arvore(){
        this.raiz = null;
    }

    public void inserir(int x){
        raiz = inserirRec(raiz,x);
    }

    private No inserirRec(No raiz, int x){

        if(raiz == null){
            return new No(x);
        }

        if(x < raiz.valor){
            raiz.esq = inserirRec(raiz.esq, x);

        }else if(x > raiz.valor) {
            raiz.dir = inserirRec(raiz.dir, x);
        }else{
            raiz.cont++;
        }

        return raiz;
    }

    public void mostrarCentral() {
    mostrarCentralRec(raiz);
}

private void mostrarCentralRec(No raiz) {
    if (raiz != null) {
        mostrarCentralRec(raiz.esq);
        System.out.println(raiz.valor + " (" + raiz.cont + "x)");
        mostrarCentralRec(raiz.dir);
    }
}

}
class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        arvore bst = new arvore();

        System.out.println("Digite números (negativo para parar):");
        int num;
        while ((num = scanner.nextInt()) >= 0) {
            bst.inserir(num);
        }

        System.out.println("\nElementos da árvore (valor + quantidade):");
        bst.mostrarCentral();

        scanner.close();
    }
}
