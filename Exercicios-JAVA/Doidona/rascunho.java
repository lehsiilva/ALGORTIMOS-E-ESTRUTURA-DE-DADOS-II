public class rascunho {
    //pesquisar avl alvinegra
    /*if(no == null){
        resp = false;
    }
    if(x == no.elemento){
        resp = true;
    }else if(x < no.elemento){
        resp = recursao(x, i.esq);
    }else{
        resp = recursao(x, i.dir);
    }*/


    /*No rotacaoEsq(No no){
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        no.altura(no);
        noDir.altura(no);

        return noDir;
    }

    No rotacaoDir(No no){
        No noEsq = no.esq;
        No noEsqDir = noEsq.esq;

        noEsq.esq = no;
        no.esq = noEsqDir;

        no.altura(no);
        noEsq.altura(no);

        return noEsq;
    }*/

    /*int altura(No no){
        return 1 + Math.max(altura(no.dir), altura(no.esq));
    }

    int fator(No no){
        if(no == null){
            return 0;
        }

        return altura(no.dir) - altura(no.esq);
    }

    int setNivel(){
        this.nivel = 1 + Math.max(setNivel(dir), setNivel(esq));
    }

    int getNivel(No no){
        return (no == null) ? 0: no.nivel;
    }*/

    /*//avl
    public No balanciar(int x, No no){
        if(no != null){
            int fator = fator(no);
        
             if(fator == 2){
                    int fatorFilhoDir = altura(no.dir.dir) - altura(no.dir.esq)
                    if( fatorFilhoDir == -1){
                        no.dir = rotacaoDir(no.dir);
                    }

                    no = rotacaoEsq(no);
                }else if(fator == -2){
                    int fatorFilhoEsq = altura(no.esq.dir) - altura(no.esq.esq)
                    if( fatorFilhoEsq == 1){
                        no.esq = rotacaoEsq(no.esq);
                    }
                    no = rotacaoDir(no);
                }
            
        return no;
    }*/

    /*No inserirAvl(int x, No i){
        if(i == null){
            i = new No(x);
        }else if(x < i.elemento){
            i.esq = inserirAvl(x, i.esq);
        }else{
            i.dir = inserirAvl(x, i.esq);
        }

        return balanciar(i);
    }*/

    /*int contQuatro(No no){
        int cont = 0;
        if(no == null){ return 0; }
        
        if(no.cor == true){
            if((no.dir != null && no.dir.cor == true) || (no.esq != null && no.esq.cor == true)){
                cont = 1; // Conta 1 se qualquer um dos filhos for Vermelho
            }
        }
        return cont + contQuatro(no.dir) + contQuatro(no.esq);
    }*/

        boolean isAvl(No no){

            if(no == null){
                return true;
            }

            if(fator(no) > 1){
                return false;
            }

            return isAvl(no.dir) && isAvl(no.esq);
        }

    
}
