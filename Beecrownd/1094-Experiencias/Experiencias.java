import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Experiencias {
 
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            
            int coelho = 0;
            int rato = 0;
            int sapo = 0;
            int totalCobaias = 0;
            int qntd;
            
            for(int i = 0; i < n; i++){
                qntd = scanner.nextInt();
                char cobaia = scanner.next().charAt(0);
                
                if(cobaia == 'C' || cobaia == 'c'){
                    coelho+=qntd;
                    totalCobaias+=qntd;
                }else if(cobaia == 'R' || cobaia == 'r'){
                    rato+=qntd;
                    totalCobaias+=qntd;
                }else if(cobaia == 'S' || cobaia == 's'){
                    sapo+=qntd;
                    totalCobaias+=qntd;
                }
                
            }
            
            System.out.println("Total: " + totalCobaias + " cobaias" );
            System.out.println("Total de coelhos: " + coelho);
            System.out.println("Total de ratos: " + rato);
            System.out.println("Total de sapos: " + sapo);
            System.out.printf(Locale.US,"Percentual de coelhos: %.2f %% %n",((float)coelho/totalCobaias) * 100);
            System.out.printf(Locale.US,"Percentual de ratos: %.2f %% %n",((float)rato/totalCobaias) * 100);
            System.out.printf(Locale.US,"Percentual de sapos: %.2f %% %n",((float)sapo/totalCobaias) * 100);
        }
        
        scanner.close();
 
    }
 
}