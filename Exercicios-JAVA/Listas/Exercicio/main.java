/*Crie uma lista encadeada de números inteiros e implemente métodos para:
Inserir no início
Inserir no fim
Remover um elemento específico
Imprimir a lista
Escreva uma função que retorne o número de elementos da lista.
Escreva uma função que inverta a lista sem criar uma lista auxiliar.
Crie uma função que busque um elemento e retorne sua posição.
Escreva uma função que some todos os elementos da lista. */

class Celula{
    int valor;
    Celula px;

    public Celula(){
        this.valor = 0;
        this.px = null;
    }

    public Celula(int valor){
        this.valor = valor;
        this.px = null;
    }

    public int getValor(){
        return valor;
    }
}

class Lista{

    Celula inicio;
    Celula fim;

    public Lista(){ //celula cabeça
        inicio = new Celula();
        fim = inicio;
    }

    public void InserirInicio(int x){

        Celula tmp = new Celula(x);

        tmp.px = inicio.px;
        inicio.px = tmp;
    }

    public void InserirFim(int x){
        Celula tmp = new Celula(x);
        fim.px = tmp;
        fim = tmp;
        fim.px = null;
    }

    public int removerInicio(){
        if(inicio.px == null){
            System.out.println("Lista Vazia");
            return -1;
        }

        Celula tmp = inicio.px;
        int remover = tmp.getValor();
        inicio.px = tmp.px;

        return remover;
    }

    public int removerFim(){
        if(inicio.px == null){
            System.out.println("Lista Vazia");
            return -1;
        }

        Celula i = inicio;

        while(i.px != fim){
            i = i.px;
        }

        int remover = fim.getValor();
        i.px = null;
        fim = i;

        return remover;
    }

    public int tamanho(){
        Celula i = inicio;
        int tam = 0;
        while(i.px != null){
            tam++;
            i = i.px;
        }

        return tam;
    }

    public int RemoverEscolhendo(int pos){
        int tam = tamanho();

        if(inicio.px == null){
            System.out.println("Lista Vazia");
            return -1;
        }else if(pos > tam){
            System.out.println("Tamanho invalido");
        }else if(pos == 0){
            removerInicio();
        }else if(pos == tam){
            removerFim();
        }
        Celula i = inicio;

        for(int j = 0; j < pos; j++){
            i = i.px;
        }

        Celula tmp = i.px;
        int remover = tmp.getValor();
        i.px = tmp.px;

        return remover;
    }

    public void imprimir(){
        Celula i = inicio.px;

        while(i!= null){
            System.out.println(i.getValor() + " ");
            i = i.px;
        }
    }

    public int numElementos(){
        int elementos = 0;
        Celula i = inicio;
        while(i.px != null){
            elementos++;
            i = i.px;
        }

        return elementos;

    }

    public void inverter() {
        if (inicio.px == null || inicio.px.px == null) {
            return; // lista vazia ou com 1 elemento
        }

        Celula ant = null;
        Celula atual = inicio.px;
        Celula proximo;
        fim = atual; // antigo primeiro será o novo fim

        while (atual != null) {
            proximo = atual.px;
            atual.px = ant;
            ant = atual;
            atual = proximo;
        }

        inicio.px = ant;
    }

    public int buscarElemento(int x){
        int tam = tamanho();
        int pos = 0;

        if(inicio.px == null){
            System.out.println("Lista Vazia");
            return -1;
        }

        Celula i = inicio.px;
        for(int j = 0; j < tam; j++){
            if(x == i.getValor()){
                pos = j;
                j = tam;
            }
            i = i.px;
        }
        return pos;
    }

    public void soma(){
        int somador = 0;
        Celula i = inicio;
        while(i.px != null){
            somador += i.getValor();
            i = i.px;
        }
        System.out.println("Soma: " + somador);
    }
}


class main {
    public static void main(String[] args) {
        
    }
}
