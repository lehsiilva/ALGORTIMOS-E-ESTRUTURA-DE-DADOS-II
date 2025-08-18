import java.util.Scanner;

public class Soma {

    public static int soma(int n){
        if(n == 0){
            return 0;
        }else{
            return n + soma(n-1);
        }
    }
    public static void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){
            int n = scanner.nextInt();
            System.out.println(soma(n));

            scanner.close();

        }
    }
}
