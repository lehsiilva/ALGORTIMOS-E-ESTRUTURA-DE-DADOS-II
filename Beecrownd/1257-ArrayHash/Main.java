import java.io.IOException;
import java.util.Scanner;
 

public class Main {

    public static int VerificaPos(char letra){
    char[] vet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    int i = 0;
    int pos = 0;
        while(i < 26){
            if(letra == vet[i]){
                pos = i;
                i = 26;
            }
            i++;
        }
    
    return pos;
}
 
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while(n != 0){
            int soma = 0;
            
            int l = sc.nextInt();
                    
            sc.nextLine();
                    
            String[] vet = new String[l];
                    
                for(int i = 0; i < l; i++){
                    vet[i] = sc.nextLine();
            }
            
            for(int i = 0; i < l; i++){
                for(int j = 0; j < vet[i].length(); j++){
                    char letra = vet[i].charAt(j);
                    int elemento = VerificaPos(letra);
                    int cont = elemento + i + j;
                    soma+= cont;
                }
            }

            System.out.println(soma);
            n--;
        }
    }

    
}