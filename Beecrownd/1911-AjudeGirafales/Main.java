import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        while (N!=0) {
            
        
            String[] nomes = new String[N];
            String[] originais = new String[N];

            for (int i = 0; i < N; i++) {
                nomes[i] = sc.next();
                originais[i] = sc.next();
            }

            int M = sc.nextInt();
            int falsos = 0;

            for (int j = 0; j < M; j++) {
                String nome = sc.next();
                String assin = sc.next();

                int pos = -1;
                for (int i = 0; i < N; i++) {
                    if (nomes[i].equals(nome)) {
                        pos = i;
                        i = N;
                    }
                }

                String original = originais[pos];

                int dif = 0;
                for (int k = 0; k < original.length(); k++) {
                    if (original.charAt(k) != assin.charAt(k)) {
                        dif++;
                    }
                }

                if (dif > 1) {
                    falsos++;
                }
            }

            System.out.println(falsos);
            N = sc.nextInt();
        }

        sc.close();
    }
}
