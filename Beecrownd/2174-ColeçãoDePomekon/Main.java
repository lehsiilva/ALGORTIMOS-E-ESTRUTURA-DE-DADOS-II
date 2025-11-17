import java.util.Scanner;

class No{
    String palavra;
    public No esq;
    public No dir;
    
    public No(String palavra){
        this.palavra = palavra;
        this.esq = null;
        this.dir = null;
    }
}

class Arvore{
    public No raiz;
    
    public Arvore(){
        this.raiz = null;
    }
    
    public void inserir(String palavra){
        raiz = inserirRec(raiz,palavra);
    }
    
    public No inserirRec(No raiz, String palavra){
        if(raiz == null){
            return new No(palavra);
        }
        
        if(palavra.compareTo(raiz.palavra) > 0){
            raiz.dir = inserirRec(raiz.dir,palavra);
        } else if(palavra.compareTo(raiz.palavra) < 0){
            raiz.esq = inserirRec(raiz.esq,palavra);
        }
           
        return raiz; 
    }
    
    public int contar(No raiz) {
        if (raiz == null) {
            return 0;
        }
        return 1 + contar(raiz.esq) + contar(raiz.dir);
    }

}

public class Main {
 
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        Arvore arvore = new Arvore();
        
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            scanner.nextLine();
            
            for(int i = 0; i < n; i++){
                String palavra = scanner.nextLine();
                arvore.inserir(palavra);
            }
            
            int cont = arvore.contar(arvore.raiz);
            int soma = 151 - cont;
            System.out.println("Falta(m) " + soma + " pomekon(s)." );
        }
        
        scanner.close();
    }
}
