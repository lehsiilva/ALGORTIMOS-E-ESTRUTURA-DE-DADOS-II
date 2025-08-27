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

    public static String inverterStr(String palavra){

        String novaPalavra = "";

        for(int i = palavra.length() - 1; i >= 0; i--){ //Ler a sting de traz pra frente
            
            novaPalavra += palavra.charAt(i);
            
        }

        palavra = novaPalavra;

        return palavra ;
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

            String palavra = scanner.nextLine();

            while(verificaFim(palavra,"FIM") != true){
                System.out.println(inverterStr(palavra));
                palavra = scanner.nextLine();
            }
        scanner.close();
    }
}
