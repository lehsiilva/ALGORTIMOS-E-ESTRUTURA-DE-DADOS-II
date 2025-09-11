import java.util.Scanner;
public class Soma {
    public static int Somar(int num){
        if(num/10 == 0){ //compara se o resultado é 0
            return num;
        }else{
            int n = num/10; //guarda o resultado das divisoes
            return num%10 + Somar(n);  //recursão para soma
        }
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String digito = scanner.nextLine();
        int num;
        

        while(digito.charAt(0) != 'F' && digito.charAt(1) != 'I' && digito.charAt(2) != 'M'){
            num = Integer.parseInt(digito); // transforma String em inteiro
            int resul = Somar(num);
            System.out.println(resul);
            digito = scanner.nextLine();
        }

        scanner.close();
    }
}