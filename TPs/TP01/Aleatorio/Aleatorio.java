import java.util.Random;
import java.util.Scanner;

public class Aleatorio {

    public static Boolean verificaFim(String palavra, String fimPalavra){
        
        boolean fim = false;

        if(palavra.length() == fimPalavra.length()){ // Verificar tamanho para comparação
            fim = true;
            for(int i = 0; i < fimPalavra.length(); i ++){
                if(palavra.charAt(i) != fimPalavra.charAt(i)){ //Se houver qualquer coisa diferente já é falso
                    fim = false;
                    i = fimPalavra.length();//Parar contador
                }
            }
        }

        return fim;
    }

    public static String sorteio(String palavra, Random gerador){
        
        char a = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));
        char b = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

        String novaPalavra = "";

        for(int i = 0; i < palavra.length(); i++){
            
            if(palavra.charAt(i) == a){
                novaPalavra += b;
            }else{
                novaPalavra += palavra.charAt(i);
            }
        }

        return novaPalavra;  
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

            String palavra = scanner.nextLine();

			Random gerador = new Random();
        	gerador.setSeed(4);

            while(verificaFim(palavra,"FIM") != true){ 
                
                System.out.println(sorteio(palavra, gerador));
                palavra = scanner.nextLine();
            }
        scanner.close();
    }
}
