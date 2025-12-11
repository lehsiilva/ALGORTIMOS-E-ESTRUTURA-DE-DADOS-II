import java.util.Scanner;

class No{
    public int valor;
    public Main esq;
    public Main dir;

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

    private Main raiz;

    public Arvore(){
        this.raiz = null;
    }

    //Inserir
    public void inserir(int x){
        raiz = inserirRec(raiz,x);
    }

    private Main inserirRec(Main raizAtual, int x){
        if(raizAtual == null){ //condição de parada
            return new Main(x);
        }

        if(x > raizAtual.valor){
            raizAtual.dir = inserirRec(raizAtual.dir, x);
        }else if(x < raizAtual.valor){
            raizAtual.esq = inserirRec(raizAtual.esq, x);
        }

        return raizAtual; //evita duplicações de valores
    }

    public void inserirPai(int x) {
        if (raiz == null) {
            raiz = new Main(x); // raiz da árvore
        } else {
            inserirRecPai(raiz, null, x);
        }
    }

    // Inserção recursiva com passagem de pai
    private void inserirRecPai(Main atual, Main pai, int x) {
        if (atual == null) {
            // insere novo nó no filho correto do pai
            if (x < pai.valor) {
                pai.esq = new Main(x);
            } else {
                pai.dir = new Main(x);
            }
            return;
        }

        if (x < atual.valor) {
            inserirRecPai(atual.esq, atual, x);
        } else if (x > atual.valor) {
            inserirRecPai(atual.dir, atual, x);
        } 
    }

    public boolean pesquisar(int x){
        return pesquisarRec(raiz, x);
    }

    private boolean pesquisarRec(Main raizAtual, int x){
        if(raizAtual == null){
            return false;
        }

        if(x == raizAtual.valor){
            return true;
        }

        if(x < raizAtual.valor){
            return pesquisarRec(raizAtual.esq, x);
        }else{
            return pesquisarRec(raizAtual.dir, x);
        }
    }

    public void CaminharCentral(){
        caminharCentralRec(raiz);
        System.out.println();
    }

    private void caminharCentralRec(Main raizAtual){
        if(raizAtual != null){
            caminharCentralRec(raizAtual.esq);
            System.out.println(raizAtual.valor + " ");
            caminharCentralRec(raizAtual.dir);
        }
    }

    public void CaminharPre(){
        caminharPreRec(raiz);
        System.out.println();
    }

    private void caminharPreRec(Main raizAtual){
        if(raizAtual != null){
            System.out.println(raizAtual.valor + " ");
            caminharPreRec(raizAtual.esq);
            caminharPreRec(raizAtual.dir);
        }
    }

    public void CaminharPos(){
        caminharPosRec(raiz);
        System.out.println();
    }

    private void caminharPosRec(Main raizAtual){
        if(raizAtual != null){
            caminharPosRec(raizAtual.esq);
            caminharPosRec(raizAtual.dir);
            System.out.println(raizAtual.valor + " ");
        }
    }

    public void remover(int valor){
        removerRec(raiz,valor);
    }

    private Main removerRec(Main raizAtual, int valor){
        if (raizAtual == null) {
            return null; // valor não encontrado
        }

        if (valor < raizAtual.valor) {
            raizAtual.esq = removerRec(raizAtual.esq, valor);
        } else if (valor > raizAtual.valor) {
            raizAtual.dir = removerRec(raizAtual.dir, valor);
        } else {
            // Encontrou o nó a remover

            // Caso 1: sem filhos
            if (raizAtual.esq == null && raizAtual.dir == null) {
                return null;
            }

            // Caso 2: um filho só
            if (raizAtual.esq == null) {
                return raizAtual.dir;
            } else if (raizAtual.dir == null) {
                return raizAtual.esq;
            }

            // Caso 3: dois filhos
            // Encontrar o menor valor da subárvore direita
            int maiorValor = maiorEsq(raizAtual.esq);
            raizAtual.valor = maiorValor;
            raizAtual.esq = removerRec(raizAtual.esq, maiorValor);
        }

        return raizAtual;
    }


    public int maiorEsq(Main raizAtual) {
        Main atual = raizAtual;
            while (atual.dir != null) {
                atual = atual.dir;
            }
        return atual.valor;
    }

}

class main{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        Arvore arvore = new Arvore();
        int opc;

        do { 
            System.out.println("----------- MENU -----------");
            System.out.println("Digite 0 para SAIR");
            System.out.println("Digite 1 para INSERIR");
            System.out.println("Digite 2 para PESQUISAR");
            System.out.println("Digite 3 para CAMINHAR CENTRAL");
            System.out.println("Digite 4 para CAMINHAR PRE");
            System.out.println("Digite 5 para CAMINHAR POS");
            System.out.println("Digite 6 para REMOVER");
            System.out.print("Escolha uma opcao: ");

            opc = scanner.nextInt();
            int x;

            switch (opc) {
                case 1: {
                    System.out.print("Digite o valor: ");
                    x = scanner.nextInt();
                    arvore.inserir(x);
                    break;
                }
                case 2:{
                    System.out.print("Digite o valor que procura: ");
                    x = scanner.nextInt();
                    boolean resul = arvore.pesquisar(x);
                    if(resul){
                        System.out.println("Valor " + x + " encontrado");
                    }else{
                        System.out.println("Valor nao encontrado " );
                    }
                    break;
                }
                case 3: 
                    arvore.CaminharCentral();
                    break;
                case 4: 
                    arvore.CaminharPre();;
                    break;
                case 5: 
                    arvore.CaminharPos();
                    break;
                case 6:{
                    System.out.print("Digite o valor a remover: ");
                    x = scanner.nextInt();
                    arvore.remover(x);
                    break;
                }
                case 0:
                    System.out.println("Saindo....");
                    break;
                default:
                    throw new AssertionError();
            }

            
            
        } while (opc != 0);



        scanner.close();
    }
}