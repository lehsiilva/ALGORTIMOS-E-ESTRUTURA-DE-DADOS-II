import java.util.Scanner;

public class lab01 {

    public static void maiusculo(String palavra){
    
       
        int cont = 0;

        for(int i = 0; i < palavra.length(); i++){

            if(palavra.charAt(i) >= 65 && palavra.charAt(i) <= 90 ){

                cont++;

            }
            
        }

            System.out.println("Quantidade de Letras MAIUSCULAS: " + cont);

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a palavra");
        String palavra= scanner.nextLine();

        
        
        while (!palavra.equals("FIM")){ //Equals compara o conteudo de dois objetos

            maiusculo(palavra);

            System.out.println("Digite a palavra");
            palavra= scanner.nextLine();
        }

         scanner.close();
        
    }
    
}
