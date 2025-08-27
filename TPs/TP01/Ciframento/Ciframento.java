import java.util.Scanner;

public class Ciframento {
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

    public static String decodificada(String palavra){
       
        String dec = "";

        for(int i = 0; i < palavra.length(); i++){

            if(palavra.charAt(i) < 127){ //verifica se a letra ou numero é um caractere especial (Tabela ascii)

                dec += (char)(palavra.charAt(i) + 3); //faz o ciframento

            }else{

                dec += palavra.charAt(i);//se for um caracter especial printa ele mesmo
            }
        }

        return dec;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
            String palavra = scanner.nextLine();

            while(verificaFim(palavra,"FIM") != true){
                
                String resul = decodificada(palavra);

                System.out.println(resul);
                palavra = scanner.nextLine();

            }
        scanner.close();
    }
}
