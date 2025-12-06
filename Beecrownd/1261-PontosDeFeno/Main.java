import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.nextLine();

        String[] palavras = new String[M];
        int[] valores = new int[M];

        int i = 0;
        while (i < M) {
            palavras[i] = sc.next();
            valores[i] = sc.nextInt();
            sc.nextLine();
            i++;
        }

        int j = 0;
        while (j < N) {
            int salario = 0;
            String linha = sc.nextLine();

            while (!linha.equals(".")) {
                String[] texto = linha.split(" ");
                int k = 0;
                while (k < texto.length) {
                    int x = 0;
                    while (x < M) {
                        if (texto[k].equals(palavras[x])) {
                            salario += valores[x];
                        }
                        x++;
                    }
                    k++;
                }
                linha = sc.nextLine();
            }

            System.out.println(salario);
            j++;
        }

        sc.close();
    }
}
