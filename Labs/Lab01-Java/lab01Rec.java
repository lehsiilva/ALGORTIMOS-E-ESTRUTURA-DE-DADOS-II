import java.util.Scanner;

public class lab01Rec {

    public static boolean maiusculo(Scanner scanner, String palavra){
        
        int cont = 0;

       if(palavra.equals("FIM")){
            return true;
       }else{

            for(int i = 0; i < palavra.length(); i++){

                if(palavra.charAt(i) >= 65 && palavra.charAt(i) <= 90 ){

                    cont++;

                }
            
            }

            System.out.println("Quantidade de Letras MAIUSCULAS: " + cont);

            System.out.println("Digite a palavra");
            palavra= scanner.nextLine();

            return maiusculo(scanner,palavra); //Chamada recursiva
       }

    }


    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Digite a palavra");
            String palavra= scanner.nextLine();

            maiusculo(scanner,palavra);

            scanner.close();
        }
    }
    
}

