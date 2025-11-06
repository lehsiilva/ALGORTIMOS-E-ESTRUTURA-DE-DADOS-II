import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // número de cartelas
        int K = sc.nextInt(); // números por cartela
        int U = sc.nextInt(); // maior número do sorteio

        int[][] cartelas = new int[N][K];
        int[] contCartela = new int[N]; // quantos números já saíram por cartela
        int[] fimCartela = new int[N];  // índice do sorteio em que a cartela completou

        for (int i = 0; i < N; i++) {
            contCartela[i] = 0;
            fimCartela[i] = -1;
            for (int j = 0; j < K; j++) {
                cartelas[i][j] = sc.nextInt();
            }
        }

        int[] sorteio = new int[U];
        for (int i = 0; i < U; i++) {
            sorteio[i] = sc.nextInt();
        }

        // Marca números sorteados nas cartelas
        for (int s = 0; s < U; s++) {
            for (int c = 0; c < N; c++) {
                if (fimCartela[c] == -1) { // só continua se cartela ainda não terminou
                    for (int j = 0; j < K; j++) {
                        if (cartelas[c][j] == sorteio[s]) {
                            contCartela[c]++;
                        }
                    }
                    if (contCartela[c] == K) {
                        fimCartela[c] = s;
                    }
                }
            }
        }

        // Descobre o menor índice de término
        int menor = U + 1;
        for (int i = 0; i < N; i++) {
            if (fimCartela[i] != -1 && fimCartela[i] < menor) {
                menor = fimCartela[i];
            }
        }

        // Imprime as cartelas vencedoras
        for (int i = 0; i < N; i++) {
            if (fimCartela[i] == menor) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }
}
