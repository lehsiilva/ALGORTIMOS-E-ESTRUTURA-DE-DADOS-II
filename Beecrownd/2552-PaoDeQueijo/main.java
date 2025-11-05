import java.util.Scanner;

class Celula {
    int valor;
    Celula dir, esq, sup, inf;

    public Celula(int valor) {
        this.valor = valor;
        this.dir = null;
        this.esq = null;
        this.sup = null;
        this.inf = null;
    }
}

class Matriz {
    private Celula inicio;
    int linha, coluna;

    public Matriz(int linha, int coluna, Scanner scanner) {
        this.linha = linha;
        this.coluna = coluna;
        criarMatriz(linha, coluna, scanner);
    }

    private void criarMatriz(int linha, int coluna, Scanner scanner) {
        Celula[][] mat = new Celula[linha][coluna];

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int x = scanner.nextInt();
                mat[i][j] = new Celula(x);
            }
        }

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if (i > 0) mat[i][j].sup = mat[i - 1][j];
                if (i < linha - 1) mat[i][j].inf = mat[i + 1][j];
                if (j > 0) mat[i][j].esq = mat[i][j - 1];
                if (j < coluna - 1) mat[i][j].dir = mat[i][j + 1];
            }
        }

        inicio = mat[0][0];
    }

    public void paoDeQueijo() {
        for (Celula linhaAtual = inicio; linhaAtual != null; linhaAtual = linhaAtual.inf) {
            for (Celula atual = linhaAtual; atual != null; atual = atual.dir) {
                if (atual.valor == 1) {
                    System.out.print("9");
                } else {
                    int cont = 0;
                    if (atual.sup != null && atual.sup.valor == 1) cont++;
                    if (atual.inf != null && atual.inf.valor == 1) cont++;
                    if (atual.esq != null && atual.esq.valor == 1) cont++;
                    if (atual.dir != null && atual.dir.valor == 1) cont++;
                    System.out.print(cont);
                }
            }
            System.out.println();
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            Matriz matriz = new Matriz(n, m, scanner);
            matriz.paoDeQueijo();
        }

        scanner.close();
    }
}
