
public class Doidona {
    private No raiz;

    public boolean pesquisar(String nome){
        boolean resp = false;
        int i = 0;
        char c = nome.charAt(i);
        boolean verifica = raiz.pesquisarArvore(c,raiz);
        boolean verificaHash = raiz.hashT1(c);
        boolean verificaRehash = raiz.rehashT1(c);
        




        return resp;
    }
}


class No{
    public char caracter;
    public T1 t1;
    public No esq,dir;

    public boolean pesquisarArvore(char x, No i){ // pesquisar na arvore
        boolean resp = false;

        if(i == null){
            resp = false;
        }else if(x == i.caracter){
            resp = true;
        }else if(x < i.caracter){
            resp = pesquisarArvore(x,i.esq);
        }else{
            resp = pesquisarArvore(x,i.dir);
        }
        return resp;

    }
}

class T1{
    public String[] palavra;
    public T2 t2;

    private boolean PesquisarHash(char x){
        boolean resp = false;
        int pos = hashT1(x);
        int repos = rehashT1(x);

        if(palavra[pos] == x){
            resp = true;
        }else if()

        return resp;

    }

    int hashT1(char x){
        return x%Tam1;
    }

    int rehashT1(char x){
        return ++x%Tam1;
    }
}

class T2{
    public CelulaT2 celulast2[];
}

class CelulaT2{
    public Celula inicio;
    public Celula fim;
}

class Celula{
    public String palavra;
    public Celula prox;
}