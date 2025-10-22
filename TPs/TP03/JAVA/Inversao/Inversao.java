import java.util.Scanner;
public class Inversao {

    // Função recursiva para inverter a palavra
    public static String invertido(String palavra){
        char[] array = palavra.toCharArray(); // converte String para char

        inverterRec(array, 0, palavra.length() - 1); 

        palavra = String.valueOf(array); // converte char para String

        return palavra;
    }

    //swap
    private static void inverterRec(char[] array, int i, int j){
        if(i >= j){ // condição de parada -> quando já percorreu até o meio
            return;
        }
        char troca = array[i]; 
        array[i] = array[j];
        array[j] = troca;
        inverterRec(array, i + 1, j - 1); // chamada recursiva para aproximar os índices
    }

    public static void main(String[] args){
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
