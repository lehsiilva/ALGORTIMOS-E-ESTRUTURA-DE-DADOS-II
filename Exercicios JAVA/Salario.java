/*Ler o valor do salário mínimo e o valor do salário de uma pessoa, calcular e mostrar
quantos salários mínimos essa pessoa ganha. */

import java.util.Locale;
import java.util.Scanner;

public class Salario {
    public static void main (String[] args){

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);;

        System.out.println("Digite o valor do salario: ");
        double salarioBase = scanner.nextDouble();

        double minimo = 1518.00;

        if(salarioBase >= minimo){  

            double total = (salarioBase / minimo);
            System.out.printf("O funcionario recebe %.2f salarios minimos", total);

        }else{
            System.out.println("Salario Inferior a Minimo");
        }
        
        scanner.close();
        
    }
}


