import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean lendo = true;

        while (lendo) {
            String chave = "";
            String frase = "";

            if (sc.hasNextLine()) {
                chave = sc.nextLine();
            } else {
                lendo = false; 
            }

            if (lendo && sc.hasNextLine()) {
                frase = sc.nextLine();
            } else {
                lendo = false; 
            }

            if (lendo) {
                int cont = 0;
                int a = chave.length();
                int b = frase.length();

                for (int i = 0; i < b; i++) {
                    for (int j = 0; j < a; j++) {
                        if (frase.charAt(i) == chave.charAt(j)) {
                            cont++;
                        }
                    }
                }

                System.out.println(cont);
            }
        }

        sc.close();
    }
}
