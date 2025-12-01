import java.util.Scanner;

public class Concurso{

    public static void lernotas(Scanner scanner,int n, int notas[]){
        for(int i = 0; i < n; i++){
            notas[i] = scanner.nextInt();
        }
    }

    public static void Ordena(int n, int notas[]){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-1; j++){
                if(notas[j] < notas[j+1]){
                    int tmp= notas[j];
                    notas[j]=notas[j+1];
                    notas[j+1] = tmp;
                    
                }
            }
        }
       
    }

    public static int notaCorte(int notas[], int k, int n){
        int corte = 0;
        int j = 0;
        
        for(int i = 0; i < k; i++){
            corte = notas[j];
            j++;
        }

        return corte;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int notas[] = new int[n];
        lernotas(scanner,n, notas);
        Ordena(n,notas);
        int notaDeCorte = notaCorte(notas, k, n);
        System.out.println(notaDeCorte);

        scanner.close();
    }
}