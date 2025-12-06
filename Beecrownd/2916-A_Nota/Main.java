import java.util.Scanner;

public class Main {

    static  int MAX_N = 1000005;
    static  int MOD = 1000000007;
    static int[] notas = new int[MAX_N];

    static void heapify(int n, int i) {
        int maior = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && notas[left] > notas[maior]) {
            maior = left;
        }
        if (right < n && notas[right] > notas[maior]) {
            maior = right;
        }

        if (maior != i) {
            int temp = notas[i];
            notas[i] = notas[maior];
            notas[maior] = temp;

            heapify(n, maior);
        }
    }

    static void heapSort(int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = notas[0];
            notas[0] = notas[i];
            notas[i] = temp;

            heapify(i, 0);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            for (int i = 0; i < n; i++) {
                notas[i] = sc.nextInt();
            }

            heapSort(n);

            int soma = 0;
            int start = n - k;

            for (int i = start; i < n; i++) {
                soma = soma + notas[i];
                if (soma >= MOD) {
                    soma = soma % MOD;
                }
            }

            System.out.println(soma);
        }

        sc.close();
    }
}
