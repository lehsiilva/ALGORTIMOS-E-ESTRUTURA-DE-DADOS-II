import java.util.Random;
import java.util.Scanner;

public class Aleatorio {

    public static String sorteio(String palavra, Random gerador){
        
        char a = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
        char b = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

        StringBuilder novaPalavra = new StringBuilder(palavra); //construir e modificar textos de forma eficiente.

        for(int i = 0; i < palavra.length(); i++){
            if(novaPalavra.charAt(i) == a){
                novaPalavra.setCharAt(i, b); //altera diretamente o caractere que está naquela posição
            }
        }

        return novaPalavra.toString(); // StringBuilder nao é uma string entt devemos converter 
    }
    public static void main(String[] args){
        try(Scanner scanner = new Scanner(System.in)){

            String palavra = scanner.nextLine();

			Random gerador = new Random();
        	gerador.setSeed(4);

            while(!palavra.equals("FIM")){ 
                
                System.out.println(sorteio(palavra, gerador));
                palavra = scanner.nextLine();
            }
        }
    }
}
