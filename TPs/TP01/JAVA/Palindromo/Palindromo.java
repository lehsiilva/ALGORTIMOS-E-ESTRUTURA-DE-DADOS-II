
import java.util.Scanner;

public class Palindromo {

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

    public static boolean verifica(String palavra){
    
        int qntd = palavra.length();
        int j = qntd - 1;


        for(int i = 0; i < qntd/2; i++){ //Divide o escopo pela metade para realizar as verificações
             if(palavra.charAt(i) != palavra.charAt(j)){
                return false;
             }
             j--;
        }

        return true;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            String palavra = scanner.nextLine();


            while(verificaFim(palavra,"FIM") != true){

                if(verifica(palavra) == true){
                    System.out.println("SIM");
                }else{
                    System.out.println("NAO");
                }

                palavra = scanner.nextLine();
            }

        scanner.close();
    }
    
}
