import java.util.Scanner;
public class Inversao {
    public static String invertido(String palavra){

        char[]array = palavra.toCharArray(); // converte String para char

        int j = palavra.length() - 1; //ultima posicao do array

        for(int i = 0; i < palavra.length()/2; i++){ // array dividido pela metade para comparar
            char troca = array[i]; //Swap
            array[i] = array[j];
            array[j] = troca;
            j--;
        }

        palavra = String.valueOf(array); //converte char para String

        return palavra;
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String palavra = scanner.nextLine();

        while(!(palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' && palavra.length() == 3)) { 
            String resul = invertido(palavra);
            System.out.println(resul);
            palavra = scanner.nextLine();
        }
        scanner.close();
    }
}
