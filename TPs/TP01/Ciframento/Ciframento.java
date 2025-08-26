
import java.util.Scanner;

public class Ciframento {
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
        try(Scanner scanner = new Scanner(System.in)){
            String palavra = scanner.nextLine();

            while(!palavra.equals("FIM")){
                
                String resul = decodificada(palavra);

                System.out.println(resul);
                palavra = scanner.nextLine();

            }
        }
    }
}
