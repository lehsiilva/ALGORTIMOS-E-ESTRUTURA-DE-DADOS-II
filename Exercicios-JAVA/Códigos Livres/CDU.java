/*Ler do teclado um número inteiro com três dígitos (no formato CDU - centena, dezena
e unidade) e mostrar o número invertido (no formato UDC). O número invertido deve
ser armazenado em outra variável antes de ser mostrado */

import java.util.Scanner;
import java.util.Locale;


public class CDU {
    public static void main(String[]args){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("Digite um numero CDU: ");
        int num = scanner.nextInt();

        int centena = num/100;
        int dezena = (num%100)/10;
        int unidade = num%10;

        int inverso = ((unidade*100) + (dezena * 10) + centena);


        System.out.println("O numero invertido é: " + inverso);

        scanner.close();
    }
    
}
