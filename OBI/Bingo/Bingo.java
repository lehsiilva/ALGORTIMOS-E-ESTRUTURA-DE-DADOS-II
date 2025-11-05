
import java.util.Scanner;

public class Bingo{

public void preencher(int rodadas, int sorteados, int num, Scanner sc, int[] sortear){

    int[] vet = new int[rodadas*num];
    boolean resul = false;

        //coloca os valores num vetor
        for(int i = 0; i < rodadas; i++){
            int cont = 0;
            for(int j = 0; j < num; j++){
                vet[j] = sc.nextInt();
                resul = sorteadosComp(sorteados,num, sc, vet[j], sortear);
                if(resul){
                    cont++;
                }
                
            }

            if(cont == num){
                System.out.println(i+1);
            }
        }
}

public boolean sorteadosComp(int sorteados, int num, Scanner sc, int vet, int[] sortear){
    boolean input = false;
    int cont = 0;

        for(int j = 0; j < sorteados; j++){
            if(sortear[j] == vet){
                cont++;
                input = true;
                j = sorteados;
            }
        }


        return input;
}
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);


        while(sc.hasNextInt()){
            int rodadas = sc.nextInt();
            int num = sc.nextInt();
            int sorteados = sc.nextInt();

            int[] sortear = new int[sorteados];

            //coloca os valores num vetor
            for(int i = 0; i < sorteados; i++){
                sortear[i] = sc.nextInt();
                    
            }

            preencher(rodadas, sorteados, num, sc, sortear);
            
            
                
            
        }
    }

}
