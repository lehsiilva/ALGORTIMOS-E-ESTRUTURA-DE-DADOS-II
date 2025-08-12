import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
    
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    int x = a+b;
    
    System.out.println("X = " + x);
 
    }
 
}