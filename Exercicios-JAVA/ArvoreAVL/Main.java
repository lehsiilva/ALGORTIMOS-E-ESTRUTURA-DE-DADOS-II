
class No{
    public int elemento;
    public No dir, esq;
    public int nivel;

    public No(int elemento){
        this(elemento, null, null, 1);
    }

    public No(int elemento, No dir, No esq, int nivel){
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
        this.nivel = nivel;
    }

    public void setNivel(){
        this.nivel = 1 + Math.max(getNivel(dir),getNivel(esq));
    }

    public int getNivel(No no){
        return (no == null) ? 0 : no.nivel;
    }
}

class AVL{
    public No raiz;

    public AVL(){
        this.raiz = null;
    }

    public boolean pesquisar(int x){
        return pesquisarRec(x, raiz);
    }

    private boolean pesquisarRec(int x, No i){
        boolean resp = false;
        if(i == null){
            resp = false;
        }else if ( x > i.elemento){
            return pesquisarRec(x, i.dir);
        }else if(x < i.elemento){
            return pesquisarRec(x, i. esq);
        }else if (x == i.elemento){
            resp = true;
        }

        return resp;
    }

    public void caminharCentral(){
        System.out.print("[");
        caminharCentralRec(raiz);
        System.out.print("]");
    }

    private void caminharCentralRec(No i){
        if(i != null){
            caminharCentralRec(i.esq);
            System.out.print(i.elemento + "fator: " + (i.getNivel(i.dir) - i.getNivel(i.esq)) );
            caminharCentralRec(i.dir);
        }
    }

    public void caminharPre(){
        System.out.print("[");
        caminharPreRec(raiz);
        System.out.print("]");
    }

    private void caminharPreRec(No i){
        if(i != null){
            System.out.print(i.elemento + "fator: " + (i.getNivel(i.dir) - i.getNivel(i.esq)) );
            caminharPreRec(i.esq);
            caminharPreRec(i.dir);
        }
    }

    public void caminharPos(){
        System.out.print("[");
        caminharPosRec(raiz);
        System.out.print("]");
    }

    private void caminharPosRec(No i){
        if(i != null){
            caminharPosRec(i.esq);
            caminharPosRec(i.dir);
            System.out.print(i.elemento + "fator: " + (i.getNivel(i.dir) - i.getNivel(i.esq)) );
        }
    }

    public void inserir(int x){
        raiz = inserirRec(x, raiz);
    }

    private No inserirRec(int x, No i){
        if(i == null){
            return new No(x);
        }else if(x < i.elemento){
            i.esq = inserirRec(x, i.esq);
        }else if(x > i.elemento){
            i.dir = inserirRec(x, i.dir);
        }

        return balanciar(i);
    }

    private No balanciar(No i){
        if(i != null){
            int fator = i.getNivel(i.dir) - i.getNivel(i.esq);
            if(Math.abs(fator) <=  1){
                i.setNivel();
            }else if(fator == 2){
                int fatorFilhoDir = i.getNivel(i.dir.dir) - i.getNivel(i.dir.esq);

                if(fatorFilhoDir == -1){
                    i.dir = rotacionarDir(i.dir);
                }

                i = rotacionarEsq(i);

            }else if(fator == -2){
                int fatorFilhoDir = i.getNivel(i.esq.dir) - i.getNivel(i.esq.esq);

                if(fatorFilhoDir == 1){
                    i.esq = rotacionarEsq(i.esq);
                }

                i = rotacionarDir(i);
            }
        }

        return i;
    }

    private No rotacionarDir(No i){
        No noEsq = i.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = i;
        i.esq = noEsqDir;
        i.setNivel();
        noEsq.setNivel();

        return noEsq;
    }

    private No rotacionarEsq(No i){
        No noDir = i.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = i;
        i.dir = noDirEsq;
        i.setNivel();
        noDir.setNivel();

        return noDir;
    }

    public void remover(int x){
        raiz = removerRec(x,raiz);
    }

    private No removerRec(int x, No i){
        if(i == null){
            return null;
        }else if(x < i.elemento){
            i.esq = removerRec(x, i.esq);
        }else if(x > i.elemento){
            i.dir = removerRec(x, i.dir);
        }else if(i.dir == null){
            i = i.esq;
        }else if(i.esq == null){
            i = i.dir;
        }else{
            i.esq = maiorEsq(i, i.esq);
        }

        return balanciar(i);
    }

    private No maiorEsq(No i, No j){
        if(j.dir == null){
            i.elemento = j.elemento;
            j = j.esq;
        }else{
           j.dir = maiorEsq(i, j.dir);
        }

        return j;
    }
}

public class Main{
    public static void main(String[] args) {
            try {
                AVL avl = new AVL();
                //int array[] = {4,35,10,13,3,30,15,12,7,40,20};
                int array[] = {1,2,3,4,5,6,7,8,9,10};
                for(int item: array){
                    System.out.println("Inserindo -> " + item);
                    avl.inserir(item);
                    avl.caminharPre();
                }
            } catch (Exception erro) {
                System.out.println(erro.getMessage());
            }
        }
}