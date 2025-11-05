
import java.util.Scanner;

//Faça um metodo que retorne a soma dos elementos de uma arvore

class No{
    int valor;
    No esq;
    No dir;

    public No(int valor){
        this.valor = valor;
        this.esq = null;
        this.dir = null;
    }

    public int getValor(){
        return valor;
    }
}

class Arvore{
    private No raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void inserirPai(int x){
        if(raiz == null){
            raiz =  new No(x);
        }else{
            inserirPaiRec(raiz, null, x);
        }
    }

    private void inserirPaiRec(No raiz, No pai, int x){
        if(raiz == null){
            if(x < pai.valor){
                pai.esq = new No(x);
            }else{
                pai.dir = new No(x);
            }
            return;
        }

        if(x < raiz.valor){
            inserirPaiRec(raiz.esq, raiz, x);
        }else if(x > raiz.valor){
            inserirPaiRec(raiz.dir, raiz, x);
        }
    }

    public int SomaElementos(){
        return SomaElementosRec(raiz);
       
    }

    private int SomaElementosRec(No atual){
        if(atual == null){
            return 0;
        }
        
        return atual.valor + SomaElementosRec(atual.esq) + SomaElementosRec(atual.dir);
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arvore arvore = new Arvore();

        arvore.inserirPai(50);
        arvore.inserirPai(30);
        arvore.inserirPai(70);
        arvore.inserirPai(20);
        arvore.inserirPai(40);
        arvore.inserirPai(60);
        arvore.inserirPai(80);
        int soma = arvore.SomaElementos();
        System.out.println("Soma: " + soma);

        scanner.close();
    }
    
}
