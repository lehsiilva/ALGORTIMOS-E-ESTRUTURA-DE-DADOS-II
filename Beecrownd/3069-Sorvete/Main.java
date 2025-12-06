import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int teste = 1;

        int P, S;
        P = sc.nextInt();
        S = sc.nextInt();

        // Loop principal controlado naturalmente
        while (P != 0 || S != 0) {

            boolean[] cobertura = new boolean[P + 1]; // inicializa com false por padrão

            for (int i = 0; i < S; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                for (int j = u; j < v; j++) {
                    cobertura[j] = true;
                }
            }

            System.out.println("Teste " + teste++);
            int i = 0;
            while (i <= P) {
                if (cobertura[i]) {
                    int inicio = i;
                    int fim = i;
                    while (fim <= P && cobertura[fim]) {
                        fim++;
                    }
                    System.out.println(inicio + " " + fim);
                    i = fim; // continua do fim do intervalo
                } else {
                    i++;
                }
            }
            System.out.println();

            // Lê o próximo conjunto de teste
            P = sc.nextInt();
            S = sc.nextInt();
        }

        sc.close();
    }
}
