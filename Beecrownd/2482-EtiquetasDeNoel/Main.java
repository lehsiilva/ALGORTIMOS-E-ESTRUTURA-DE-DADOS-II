import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        String[] idioma = new String[N];
        String[] traducao = new String[N];

        for (int i = 0; i < N; i++) {
            idioma[i] = sc.nextLine();
            traducao[i] = sc.nextLine();
        }

        int M = sc.nextInt();
        sc.nextLine();

        String[] nomeCrianca = new String[M];
        String[] linguaCrianca = new String[M];

        for (int i = 0; i < M; i++) {
            nomeCrianca[i] = sc.nextLine();
            linguaCrianca[i] = sc.nextLine();
        }

        for (int i = 0; i < M; i++) {

            System.out.println(nomeCrianca[i]);

            for (int j = 0; j < N; j++) {
                if (linguaCrianca[i].equals(idioma[j])) {
                    System.out.println(traducao[j]);
                }
            }

            System.out.println(); 
        }

        sc.close();
    }
}
