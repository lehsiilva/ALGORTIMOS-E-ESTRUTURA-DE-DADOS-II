import java.io.IOException;
import java.util.Scanner;

public class Times {
    public static class Pessoa{
        String nome;
        int idade;
        
        Pessoa(String nome, int idade){
            this.nome = nome;
            this.idade = idade;
        }
    }
 
    public static void ler(Scanner scanner,Pessoa[] pessoas, int n){
        for(int i = 0; i < n; i++){
            String nome = scanner.next();
            int idade = scanner.nextInt();
            scanner.nextLine(); 
            pessoas[i] = new Pessoa(nome, idade);
        }
    }
     
    public static void OrdenarIdades(Pessoa[] pessoas, int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n-1; j++){
                if(pessoas[j].idade < pessoas[j+1].idade){
                    Pessoa tmp = pessoas[j];
                    pessoas[j] = pessoas[j+1];
                    pessoas[j+1] = tmp;
                }
            }
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNextInt()){
            
            int n = scanner.nextInt();
            scanner.nextLine(); 
            
            Pessoa[] pessoas = new Pessoa[n];
            ler(scanner,pessoas,n);
            OrdenarIdades(pessoas,n);
            int num = n/3;
            int j = 0;
            
            for(int i = 0 ; i < n/3; i++){
                System.out.println("Time " + (j+1));
                for(int k = i; k < n; k+=num){
                    System.out.println(pessoas[k].nome + " " + pessoas[k].idade);
                }
                j++;
            }
        }
        
        scanner.close();
    }
 
}