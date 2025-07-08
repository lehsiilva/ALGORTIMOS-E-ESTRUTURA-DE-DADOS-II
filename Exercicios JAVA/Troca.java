/*Ler dois números reais e salvá-los nas variáveis A e B. Em seguida, troque os valores
das duas variáveis de forma que a variável A passe a ter o valor da variável B e
vice-versa. No final, mostre os valores finais. */

import java.util.Scanner;
import java.util.Locale;

public class Troca {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Digite o valor de A: ");
        double a = scanner.nextDouble();

        System.out.print("Digite o valor de B: ");
        double b = scanner.nextDouble();

        System.out.printf("A antes da troca: %.2f \n", a);
        System.out.printf("B antes da troca: %.2f \n\n", b);

        double troca;

        troca = a;
        a = b;
        b = troca;

        System.out.printf("A depois da troca: %.2f \n", a);
        System.out.printf("B depois da troca: %.2f \n", b);


        scanner.close();
    }
}
