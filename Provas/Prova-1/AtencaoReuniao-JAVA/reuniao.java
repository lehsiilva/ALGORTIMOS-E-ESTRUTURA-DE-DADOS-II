import java.util.Scanner;
    public class reuniao{

        public static int tempoCalculado(int n, int k){
            int temp = (k - (n-1))/n;

            if(temp < 1){
                temp = 1;
            }

            return temp;
        }

        public static void main(String [] args){
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextInt()){
                int n = scanner.nextInt();
                int k = scanner.nextInt();
                
                int resp = tempoCalculado(n,k);

                System.out.println(resp);

            }
            scanner.close();
        }
    }

















