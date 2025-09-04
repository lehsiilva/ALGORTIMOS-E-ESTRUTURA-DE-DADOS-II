import java.util.Scanner;

public class Anagrama{
        public static Boolean verificaFim(String palavra, String fimPalavra){
        
        boolean fim = false;

        if(palavra.length() == fimPalavra.length()){ // Verificar tamanho para comparação
            fim = true;
            for(int i = 0; i < fimPalavra.length(); i ++){
                if(palavra.charAt(i) != fimPalavra.charAt(i)){ //Se houver qualquer coisa diferente já é falso
                    fim = false;
                    i = fimPalavra.length();//Parar contador
                }
            }
        }

        return fim;
    }

    public static boolean verifica(String palavra1, String palavra2){
        boolean resul = false;

        if(palavra1.length() != palavra2.length()) {
            return resul;
        }

        int cont = 0;

        for(int i = 0; i < palavra1.length(); i++){
            for(int j = 0; j < palavra2.length(); j++){
                char c1 = palavra1.charAt(i);
                char c2 = palavra2.charAt(j);

                if(c1 == c2 || Math.abs(c1 - c2) == 32){ // diferença entre maiusculas e minusculas por ascii é 32 | math.abs transforma numeros negativos em positivos
                    cont++;
                    j = palavra2.length(); //Se achar para a execução do looping
                }
            }
        }

        if(cont == palavra1.length()){
            resul = true;
        }

        return resul;
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
            String palavra1 = scanner.next();
            String palavra2 = "";

            
            while(!verificaFim(palavra1, "FIM")){

                palavra2 = scanner.next();

                if(verifica(palavra1,palavra2) == true){
                    System.out.println("SIM");
                }else{
                    System.out.println("N\u00C3O");
                }

                palavra1 = scanner.next();
                
            }

        scanner.close();
        
    }
}