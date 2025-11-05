import java.util.Scanner;

class Celula{
    int valor;
    Celula dir;
    Celula esq;
    Celula sup;
    Celula inf;

    public Celula(int valor){
        this.valor = valor;
        this.dir = null;
        this.esq = null;
        this.sup = null;
        this.inf = null;
    }

    public Celula(){ // Celula Cabeça
        this(0);
    }
}

class Matriz{
    private Celula inicio; //celula Cabeça
    private int linha,coluna;

    public Matriz(int linha, int coluna, Scanner sc){
        this.linha = linha;
        this.coluna = coluna;
        
        criarMatriz(linha, coluna, sc);
    }

    public void criarMatriz(int linha, int coluna, Scanner sc){
        inicio = new Celula();
        Celula[][] matriz = new Celula[linha][coluna];

        for(int i = 0; i < linha; i++){
            for(int j = 0; j < coluna; j++){
                int x = sc.nextInt();
                matriz[i][j] = new Celula(x);
            }
        }

        for(int i = 0; i < linha; i++){
            for(int j = 0; j < coluna; j++){
                if(i > 0){
                    matriz[i][j].sup = matriz[i-1][j];
                }

                if(i < linha - 1){
                    matriz[i][j].inf = matriz[i+1][j];
                }

                if(j > 0){
                    matriz[i][j].esq = matriz[i][j-1];
                }   

                if(j < coluna - 1){
                    matriz[i][j].dir = matriz[i][j+1];
                }
            }
        }

        inicio.dir = matriz[0][0];
    }

    public void diagonalPrincipal(){

        for(Celula i = inicio.dir; i != null; i = (i.inf != null ? i.inf.dir : null)){
                System.out.print(i.valor + " ");
        }

        System.out.println();
    }


    public void imprimir(){

        for(Celula i = inicio.dir; i != null; i = i.inf){
            for(Celula j = i; j != null; j = j.dir){
                System.out.print(j.valor + " ");
            }
        }

        System.out.println();
    }

}




















































































/*class Celula {
    int valor;
    Celula cima, baixo, dir, esq;

    public Celula(int valor) {
        this.valor = valor;
        this.cima = null;
        this.baixo = null;
        this.dir = null;
        this.esq = null;
    }
}

class MatrizFlexivel {
    private Celula inicio; // canto superior esquerdo
    private int linhas, colunas;

    // Construtor padrão 3x3
    public MatrizFlexivel() {
        this(3, 3);
    }

    public MatrizFlexivel(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        criarMatriz(linhas, colunas);
    }

    // Cria matriz inicial
    private void criarMatriz(int linhas, int colunas) {
        Celula[][] mat = new Celula[linhas][colunas];

        // Criação das células
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                mat[i][j] = new Celula(0);
            }
        }

        // Liga as células
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i > 0) mat[i][j].cima = mat[i - 1][j];
                if (i < linhas - 1) mat[i][j].baixo = mat[i + 1][j];
                if (j > 0) mat[i][j].esq = mat[i][j - 1];
                if (j < colunas - 1) mat[i][j].dir = mat[i][j + 1];
            }
        }

        inicio = mat[0][0];
    }

    // Imprime a matriz
    public void imprimir() {
        Celula linhaPtr = inicio;
        while (linhaPtr != null) {
            Celula colPtr = linhaPtr;
            while (colPtr != null) {
                System.out.print(colPtr.valor + "\t");
                colPtr = colPtr.dir;
            }
            System.out.println();
            linhaPtr = linhaPtr.baixo;
        }
    }

    // Seta valor
    public void setValor(int linha, int coluna, int valor) {
        Celula ptr = inicio;
        for (int i = 0; i < linha; i++) ptr = ptr.baixo;
        for (int j = 0; j < coluna; j++) ptr = ptr.dir;
        ptr.valor = valor;
    }

    // Retorna valor
    public int getValor(int linha, int coluna) {
        Celula ptr = inicio;
        for (int i = 0; i < linha; i++) ptr = ptr.baixo;
        for (int j = 0; j < coluna; j++) ptr = ptr.dir;
        return ptr.valor;
    }

    // Preenche toda a matriz com um valor
    public void preencherMatriz(int valor) {
        Celula linhaPtr = inicio;
        while (linhaPtr != null) {
            Celula colPtr = linhaPtr;
            while (colPtr != null) {
                colPtr.valor = valor;
                colPtr = colPtr.dir;
            }
            linhaPtr = linhaPtr.baixo;
        }
    }

    // Adiciona linha no final
    public void adicionarLinha() {
        Celula linhaPtr = inicio;

        // Vai até a última linha
        while (linhaPtr.baixo != null) linhaPtr = linhaPtr.baixo;

        Celula novaLinhaPtr = null;
        Celula anteriorCol = linhaPtr;

        for (int j = 0; j < colunas; j++) {
            Celula nova = new Celula(0);

            if (novaLinhaPtr == null) {
                novaLinhaPtr = nova; // início da nova linha
            } else {
                nova.esq = novaLinhaPtr;
                novaLinhaPtr.dir = nova;
                novaLinhaPtr = nova;
            }

            // Liga com a célula de cima
            if (anteriorCol != null) {
                nova.cima = anteriorCol;
                anteriorCol.baixo = nova;
                anteriorCol = anteriorCol.dir;
            }
        }

        linhas++;
    }

    // Adiciona coluna no final
    public void adicionarColuna() {
        Celula linhaPtr = inicio;

        for (int i = 0; i < linhas; i++) {
            Celula nova = new Celula(0);

            // Vai até a última célula da linha
            Celula ultima = linhaPtr;
            while (ultima.dir != null) ultima = ultima.dir;

            ultima.dir = nova;
            nova.esq = ultima;

            // Liga com a célula de cima, se existir
            if (i > 0) {
                Celula cima = linhaPtr.esq;
                if (cima != null) {
                    while (cima.dir != null) cima = cima.dir;
                    cima.baixo = nova;
                    nova.cima = cima;
                }
            }

            linhaPtr = linhaPtr.baixo;
        }

        colunas++;
    }

    // Remove linha
    public void removerLinha(int numeroLinha) {
        if (numeroLinha < 0 || numeroLinha >= linhas) return;

        Celula linhaPtr = inicio;
        for (int i = 0; i < numeroLinha; i++) linhaPtr = linhaPtr.baixo;

        Celula colPtr = linhaPtr;
        while (colPtr != null) {
            if (colPtr.cima != null) colPtr.cima.baixo = colPtr.baixo;
            if (colPtr.baixo != null) colPtr.baixo.cima = colPtr.cima;

            if (numeroLinha == 0 && colPtr == inicio) inicio = colPtr.baixo;

            colPtr = colPtr.dir;
        }

        linhas--;
    }

    // Remove coluna
    public void removerColuna(int numeroColuna) {
        if (numeroColuna < 0 || numeroColuna >= colunas) return;

        Celula linhaPtr = inicio;
        while (linhaPtr != null) {
            Celula colPtr = linhaPtr;
            for (int j = 0; j < numeroColuna; j++) colPtr = colPtr.dir;

            if (colPtr.esq != null) colPtr.esq.dir = colPtr.dir;
            if (colPtr.dir != null) colPtr.dir.esq = colPtr.esq;

            if (numeroColuna == 0 && linhaPtr == inicio) inicio = colPtr.dir;

            linhaPtr = linhaPtr.baixo;
        }

        colunas--;
    }
}

public class main {
    public static void main(String[] args) {
        MatrizFlexivel matriz = new MatrizFlexivel(3, 3);

        matriz.setValor(0, 0, 1);
        matriz.setValor(1, 1, 5);
        matriz.setValor(2, 2, 9);

        System.out.println("Matriz inicial:");
        matriz.imprimir();

        // Adicionar linha e coluna
        matriz.adicionarLinha();
        matriz.adicionarColuna();
        matriz.setValor(3, 3, 7);
        System.out.println("\nApós adicionar linha e coluna:");
        matriz.imprimir();

        // Remover linha e coluna
        matriz.removerLinha(1);
        matriz.removerColuna(0);
        System.out.println("\nApós remover linha 1 e coluna 0:");
        matriz.imprimir();

        // Preencher toda a matriz com 2
        matriz.preencherMatriz(2);
        System.out.println("\nApós preencher toda a matriz com 2:");
        matriz.imprimir();
    }
}*/
