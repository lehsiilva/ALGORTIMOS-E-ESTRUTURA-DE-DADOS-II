
import java.util.Scanner;

public class Palindromo {

    public static boolean verifica(String palavra){
    
        int qntd = palavra.length();
        int j = qntd - 1;


        for(int i = 0; i < qntd/2; i++){
             if(palavra.charAt(i) != palavra.charAt(j)){
                return false;
             }
             j--;
        }

        return true;

    }

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){

            String palavra = scanner.nextLine();


            while(!palavra.equals("FIM")){

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
    
}
