/*O Professor solicitou que você escreva um programa que converta uma expressão na forma infixa (como usualmente conhecemos) para uma expressão na forma posfixa. Como você sabe, os termos in (no meio) e pos (depois) se referem à posição dos operadores. O programa terá que lidar somente com operadores binários +,-,*,/,^, parênteses, letras e números. Um exemplo seria uma expressão como:
(A*B+2*C^3)/2*A. O programa deve converter esta expressão (infixa) para a expressão posfixa: AB*2C3^*+2/A*
Entrada
A primeira linha da entrada contém um valor inteiro N (N < 1000), que indica o número de casos de teste. Cada caso de teste a seguir é uma expressão válida na forma infixa, com até 300 caracteres.
Saída
Para cada caso, apresente a expressão convertida para a forma posfixa.*/
import java.util.Scanner;

class Celula{
    char elemento;
    Celula px;

    public Celula(char elemento){
        this.elemento = elemento;
        this.px = null;
    }

}

class Pilha{
    private Celula topo;
    int topoCont = 0;

    public Pilha(){
        this.topo = null;
    }

    public void push(char elemento){
            Celula tmp = new Celula(elemento);
            tmp.px = topo;
            topo = tmp;
            topoCont++;
        
    }
    
    public void pop(){
        System.out.print(topo.elemento);
        topo = topo.px;
    }

    

    public void preencherPilha(String str){
        int tam = str.length();
        char consultaTopo = topo.elemento;
        for(int i = 0; i < tam; i++){
            char string = str.charAt(i);

            if(((string == 'A' || string == 'Z') || (string == 'a' || string == 'z')) || (string == '0' || string == '9') ){
                System.out.print(string);
            }else if((consultaTopo == '*' || consultaTopo == '/') && (string == '-' || string == '+') ){
                pop();
                push(string);
            }else if ((consultaTopo == '^') && (string == '*' || string == '/' ||string == '-' || string == '+') ) {
                pop();
                push(string);
            }else if((consultaTopo == '*' || consultaTopo == '/' ||consultaTopo == '-' || consultaTopo == '+') && (string == '^')){
                System.out.print(string);
            }
        }
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pilha pilha = new Pilha();
        
        //while(scanner.hasNextInt()){
            int n = scanner.nextInt();

            for(int i = 0; i < n; i++){
                //if(scanner.hasNextLine()){
                    String str = scanner.nextLine();
                    pilha.preencherPilha(str);
                    
                //}
            }
            
            
        //}

    }
    

}
