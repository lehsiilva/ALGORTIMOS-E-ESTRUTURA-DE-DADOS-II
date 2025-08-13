import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
import java.lang.Math;

public class Main {
 
    public static void main(String[] args) throws IOException {
    
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    
    double pi = 3.14159;
    double raio = scanner.nextDouble();
    double area = pi * Math.pow(raio,2);
    
    System.out.printf("A=%.4f%n", area);
    
    scanner.close();
 
    }
 
}