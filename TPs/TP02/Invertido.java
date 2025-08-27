import java.util.Scanner;

public class Invertido {

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

    public static String invertido(String palavra){

        int tam = palavra.length() - 1;

        StringBuilder novaPalavra = new StringBuilder(palavra);// Permite alterar cada posição da String

        for(int i = 0; i < palavra.length()/2; i++){
            char temp = novaPalavra.charAt(i);
            novaPalavra.setCharAt(i,novaPalavra.charAt(tam)); //Verificar se pode 
            novaPalavra.setCharAt(tam,temp);
            tam--;
        }

        return novaPalavra.toString();
    }
    public static void main(String[]args){
        try(Scanner scanner = new Scanner(System.in)){

            String palavra = scanner.nextLine();

            while(verificaFim(palavra,"FIM") != true){
                System.out.println(invertido(palavra));
                palavra = scanner.nextLine();
            }
        }
    }
}
