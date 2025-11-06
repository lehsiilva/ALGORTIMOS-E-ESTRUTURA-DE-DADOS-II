import java.util.Scanner;

public class main{

    public static void ler(int[] notas, int[] notasOrdenadas, int m, Scanner sc){
        for(int i = 0; i < m; i++){
            notas[i] = sc.nextInt();
            notasOrdenadas[i] = notas[i];
        }
    }

    public static void ordenar(int[] notasOrdenadas, int m){
        for(int i = 0; i < m - 1; i++){
            for(int j = 0; j < m - i - 1; j++){
                if(notasOrdenadas[j] < notasOrdenadas[j+1]){
                    int tmp = notasOrdenadas[j];
                    notasOrdenadas[j] = notasOrdenadas[j+1];
                    notasOrdenadas[j+1] = tmp;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        while(n > 0){
            int m = sc.nextInt();
            int[] notas = new int[m];
            int[] notasOrdenadas = new int[m];
            int cont = 0;

            ler(notas, notasOrdenadas, m, sc);
            ordenar(notasOrdenadas, m);

            for(int i = 0; i < m; i++){
                if(notas[i] == notasOrdenadas[i]){
                    cont++;
                }
            }

                System.out.println(cont);
                n--;
        }

        sc.close();
    }
}