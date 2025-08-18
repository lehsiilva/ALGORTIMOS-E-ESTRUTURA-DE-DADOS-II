import java.util.Scanner;

public class Potencia {

    public static int potencia(int a, int b){
        
        if(b == 0){
            return 1;
        }else{
            return a * potencia(a, b -1);
        }
    }
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){

            System.out.println("Digite o valor: ");
            int a = scanner.nextInt();

            System.out.println("Digite a potencia: ");
            int b = scanner.nextInt();

            System.out.println(potencia(a,b));

            scanner.close();
        }
    }
}
