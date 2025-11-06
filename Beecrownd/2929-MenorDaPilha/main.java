import java.util.Scanner;

class Celula{
    int valor;
    Celula px;

    public Celula(int valor){
        this.valor = valor;
        this.px = null;
    }
}

class Pilha{
    private Celula topo;

    public Pilha(){
        this.topo = null;
    }

    public void push(int x){
        Celula nova = new Celula(x);
        nova.px = topo;
        topo = nova;

    }
    public int pop(){
        int valorRemovido;

        if(topo == null){
            return -1;
        }else{
            valorRemovido = topo.valor;
            topo = topo.px;
        }

        return valorRemovido;
    }

    public int min(){
        if(topo == null){
            return -1;
        }
        Celula i = topo;
        int menor = i.valor;
        while(i != null){
            if(i.valor < menor){
                menor = i.valor;
            }
            i = i.px;
        }

        return menor;
    }
}

class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pilha pilha = new Pilha();

        while(sc.hasNextInt()){
            int n = sc.nextInt();
            sc.nextLine(); 
            int resul;

            for(int i = 0; i < n; i++){
                String str = sc.next();
                if(str.charAt(0) == 'P' && str.charAt(1) == 'U' && str.charAt(2) == 'S' && str.charAt(3) == 'H'){
                    int x = sc.nextInt();
                    pilha.push(x);
                }else if(str.charAt(0) == 'P' && str.charAt(1) == 'O' && str.charAt(2) == 'P'){
                    resul = pilha.pop();
                    if(resul == -1){
                        System.out.println("EMPTY");
                    }
                }else{
                    resul = pilha.min();
                    if(resul == -1){
                        System.out.println("EMPTY");
                    }else{
                        System.out.println(resul);
                    }
                    
                }
            }
        }
    }
}
