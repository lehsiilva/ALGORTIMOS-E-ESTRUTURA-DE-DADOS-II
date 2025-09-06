/*Crie um método recursivo, em Java, que receba como parâmetro uma String palavra e um numero inteiro index e imprima cada um dos caracteres que compõe a palavra em ordem inversa. Ex: "CEU", o metodo imprime "UEC" */

import java.util.Scanner;
public class Inverso {
    public static void inverter(String str, int i){
        char a = str.charAt(i);
        if(i == 0){
            System.out.print(a);
            return;
        }else{
            System.out.print(a);
            inverter(str, i-1);
        }
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int i = str.length() - 1;

        inverter(str,i);
    }
}
