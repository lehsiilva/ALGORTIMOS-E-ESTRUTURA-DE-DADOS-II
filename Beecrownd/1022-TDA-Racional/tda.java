import java.io.IOException;
import java.util.Scanner;
public class tda {
    public static int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (b == 0){
            return a;
        }else{
            return mdc(b, a % b);
        }
    }

    public static void soma(int N1, int D1, int N2, int D2){
        int somar = (N1*D2) + (N2*D1);
        int div = (D1*D2);
        int mdcCima = somar / mdc(somar,div);
        int mdcBaixo = div / mdc(somar,div);
        
        if(mdcCima != 0 && mdcBaixo != 0 ){
            System.out.println(somar + "/" + div + " " +  "=" + " " + mdcCima + "/" + mdcBaixo);
        }else{
            System.out.println(somar + "/" + div + " " +  "=" + " " + somar + "/" + div);
        }

        
    }
    
     public static void subtrai(int N1, int D1, int N2, int D2){
        int sub = (N1*D2) - (N2*D1);
        int div = (D1*D2);
        int mdcCima = sub / mdc(sub,div);
        int mdcBaixo = div / mdc(sub,div);
        
        if(mdcCima != 0 && mdcBaixo != 0 ){
            System.out.println(sub + "/" + div + " " +  "=" + " " + mdcCima + "/" + mdcBaixo);
        }else{
            System.out.println(sub + "/" + div + " " +  "=" + " " + sub + "/" + div);
        }
        
    }
    
     public static void multiplica(int N1, int D1, int N2, int D2){
        int mult = (N1*N2);
        int div = (D1*D2);
        int mdcCima = mult / mdc(mult,div);
        int mdcBaixo = div / mdc(mult,div);
        
        if(mdcCima != 0 && mdcBaixo != 0 ){
            System.out.println(mult + "/" + div + " " +  "=" + " " + mdcCima + "/" + mdcBaixo);
        }else{
            System.out.println(mult + "/" + div + " " +  "=" + " " + mult + "/" + div);
        }
        
    }
    
     public static void divide(int N1, int D1, int N2, int D2){
        int div1 = (N1*D2);
        int div = (N2*D1);
        int mdcCima = div1 / mdc(div1,div);
        int mdcBaixo = div / mdc(div1,div);
 
        if(mdcCima != 0 && mdcBaixo != 0 ){
            System.out.println(div1 + "/" + div + " " +  "=" + " " + mdcCima + "/" + mdcBaixo);
        }else{
            System.out.println(div1 + "/" + div + " " +  "=" + " " + div1 + "/" + div);
        }
        
    }
 
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.next());
        while(n!=0){
            int N1 = Integer.parseInt(scanner.next());
            scanner.next();
            int D1 = Integer.parseInt(scanner.next());
            char operador = scanner.next().charAt(0);
            int N2 = Integer.parseInt(scanner.next());
            scanner.next();
            int D2 = Integer.parseInt(scanner.next());
            switch(operador){
                case '+':
                soma(N1,D1,N2,D2);
                n--;
                break;
                
                case '-':
                subtrai(N1,D1,N2,D2);
                n--;
                break;
                
                case '*':
                multiplica(N1,D1,N2,D2);
                n--;
                break;
                
                case '/':
                divide(N1,D1,N2,D2);
                n--;
                break;
                
            }
            
        }
        scanner.close();
    }
        
}