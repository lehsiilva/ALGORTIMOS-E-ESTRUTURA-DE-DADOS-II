import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] paises = new String[n];
        int[] ouro = new int[n];
        int[] prata = new int[n];
        int[] bronze = new int[n];

        for (int i = 0; i < n; i++) {
            paises[i] = sc.next();
            ouro[i] = sc.nextInt();
            prata[i] = sc.nextInt();
            bronze[i] = sc.nextInt();
            sc.nextLine();
        }

        // Ordenação manual
        for (int i = 0; i < n - 1; i++) {
            int pos = i;
            for (int j = i + 1; j < n; j++) {
                if (ouro[j] > ouro[pos] ||
                    (ouro[j] == ouro[pos] && prata[j] > prata[pos]) ||
                    (ouro[j] == ouro[pos] && prata[j] == prata[pos] && bronze[j] > bronze[pos]) ||
                    (ouro[j] == ouro[pos] && prata[j] == prata[pos] && bronze[j] == bronze[pos] && 
                     paises[j].compareTo(paises[pos]) < 0)) {
                    pos = j;
                }
            }

            // Troca se necessário
            if (pos != i) {
                String tempPais = paises[i]; paises[i] = paises[pos]; paises[pos] = tempPais;
                int temp = ouro[i]; ouro[i] = ouro[pos]; ouro[pos] = temp;
                temp = prata[i]; prata[i] = prata[pos]; prata[pos] = temp;
                temp = bronze[i]; bronze[i] = bronze[pos]; bronze[pos] = temp;
            }
        }

        // Imprime resultado
        for (int i = 0; i < n; i++) {
            System.out.printf("%s %d %d %d%n", paises[i], ouro[i], prata[i], bronze[i]);
        }

        sc.close();
    }
}
