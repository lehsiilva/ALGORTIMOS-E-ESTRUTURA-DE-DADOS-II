import java.util.Scanner;

public class lab01Rec {

    public static int maiusculo(String palavra, int i){
        
       if(i == palavra.length()){ // Caso base
            return 0;

       }else if(palavra.charAt(i) >= 65 && palavra.charAt(i) <= 90){
            return 1 + maiusculo(palavra, i + 1); //Chamada recursiva

       }else{
            return maiusculo(palavra, i + 1); //Chamada recursiva
       }

    }


    public static void main(String[] args) {
        
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Digite a palavra");
            String palavra = scanner.nextLine();

            while (!palavra.equals("FIM")){ //Equals compara o conteudo de dois objetos

                int resul = maiusculo(palavra,0);

                System.out.println("A palavra possui " + resul + " letras MAIUSCULAS");

                System.out.println("Digite a palavra");
                palavra = scanner.nextLine();
            }

            scanner.close();
        }
    }
    
}

