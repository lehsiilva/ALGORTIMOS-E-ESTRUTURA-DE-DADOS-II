class ListaCircular {
    int[] array;
    int primeiro = 0;
    int ultimo = 0;

    ListaCircular(int tamanho) {
        array = new int[tamanho];
    }

    // Inserir no fim
    void inserirFim(int x) throws Exception {
        if (((ultimo + 1) % array.length) == primeiro)
            throw new Exception("Erro! Lista cheia");
        array[ultimo] = x;
        ultimo = (ultimo + 1) % array.length;
    }

    // Inserir no início
    void inserirInicio(int x) throws Exception {
        if (((ultimo + 1) % array.length) == primeiro)
            throw new Exception("Erro! Lista cheia");
        // move primeiro para trás circularmente
        primeiro = (primeiro - 1 + array.length) % array.length;
        array[primeiro] = x;
    }

    // Remover do início
    int remover() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro! Lista vazia");
        
        int resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length;
        
        return resp;
        
    }

    // Mostrar lista
    void mostrar() {
        int i = primeiro;
        System.out.print("[");
        while (i != ultimo) {
            System.out.print(array[i]);
            if ((i + 1) % array.length != ultimo) System.out.print(", ");
            i = (i + 1) % array.length;
        }
        System.out.println("]");
    }

    // Verificar se está vazia
    boolean vazia() {
        return primeiro == ultimo;
    }

    // Verificar se está cheia
    boolean cheia() {
        return ((ultimo + 1) % array.length) == primeiro;
    }
    public static void main(String[] args) throws Exception {
    ListaCircular lista = new ListaCircular(5); // capacidade 4

    lista.inserirFim(10);
    lista.inserirFim(20);
    lista.inserirInicio(5);  // agora inserção no início
    lista.mostrar();          // [5, 10, 20]

    lista.remover();
    lista.mostrar();          // [10, 20]

    lista.inserirInicio(3);
    lista.inserirFim(30);
    lista.mostrar();          // [3, 10, 20, 30]
}

}

define_MAX;
public static void removeMaiorQueDez(int[]array, int primeiro,int ultimo){
    if(fim == MAX){
        fim = 0;
    }
    if(primeiro == ultimo){
        System.out.println("Fila Cheia");
        return;
    }
    int armazena;
    for(int i = 0; i < MAX; i++){
        if(array[i] > 10){
            armazena = array[i];
            ultimo--;
            array[ultimo - 1];
            i = MAX;
        }
    }

    if(aramazena>=0){
        return armazena;
    }else{
        return -1;
    }
}