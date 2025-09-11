import java.util.Scanner;
public class Grid {
    public static void preencher(Scanner scanner,int[]array, int n){
        for(int i = 0; i < n; i++){
            array[i] = scanner.nextInt();
        }
    }

 
    public static int Ordenar(int[] array, int n){
        int cont = 0;

        for(int i = 1; i < n; i++){
            int tmp = array[i];
            int j = i - 1;
            while((j >=0) && (array[j]> tmp)){
                array[j+1] = array[j];
                cont++;
                j--;
            }
            array[j+1] = tmp;
        }

        return cont;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextInt()){
            int n = scanner.nextInt();

            int[] array = new int[n];
            preencher(scanner, array, n);

            int[] arrayChegada = new int[n];
            preencher(scanner, arrayChegada, n);

            int chegada = Ordenar(array, n);
            int largada = Ordenar(arrayChegada,n);
            
            int operacao = largada - chegada;

            System.out.println(Math.abs(operacao)); //Numeros negativos para positivos
        }

        scanner.close();
    }
}
