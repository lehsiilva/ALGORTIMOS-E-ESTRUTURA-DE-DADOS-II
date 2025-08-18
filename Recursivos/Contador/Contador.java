import java.util.Scanner;

public class Contador {

    public static void imprimir(int n){
        if(n == 1){
            System.out.print(n + " ");
        }else{
            imprimir(n-1);
            System.out.print(n + " ");
        }
    }
    public static void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            int n = scanner.nextInt();
            imprimir(n);

            scanner.close();
        }
    }
}
