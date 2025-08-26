/*Crie um método iterativo que recebe uma string como parâmetro e retorna a string invertida. Na saída padrão, para cada linha de entrada, escreva uma linha de saída com a string invertida. Por exemplo, se a entrada for abcde, a saída deve ser edcba. */
import java.util.Scanner;

public class Invertido {

    public static String invertido(String palavra){

        int tam = palavra.length() - 1;

        StringBuilder novaPalavra = new StringBuilder(palavra);

        for(int i = 0; i < palavra.length()/2; i++){
            char temp = novaPalavra.charAt(i);
            novaPalavra.setCharAt(i,novaPalavra.charAt(tam));
            novaPalavra.setCharAt(tam,temp);
            tam--;
        }

        return novaPalavra.toString();
    }
    public static void main(String[]args){
        try(Scanner scanner = new Scanner(System.in)){

            String palavra = scanner.nextLine();

            while(!palavra.equals("FIM")){
                System.out.println(invertido(palavra));
                palavra = scanner.nextLine();
            }
        }
    }
}
