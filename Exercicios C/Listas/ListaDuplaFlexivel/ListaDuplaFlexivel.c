#include <stdio.h>
#include <stdlib.h>

typedef struct CelulaDupla{

    int valor;
    struct CelulaDupla *ant;
    struct CelulaDupla *px;

}CelulaDupla;

typedef struct ListaDuplaFlexivel{
    CelulaDupla *inicio;
    CelulaDupla *fim;

}ListaDuplaFlexivel;

ListaDuplaFlexivel *criarLista(){
    CelulaDupla *cabeca = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    ListaDuplaFlexivel *lista = (ListaDuplaFlexivel*)malloc(sizeof(ListaDuplaFlexivel));

    cabeca->valor = 0;
    cabeca->ant = NULL;
    cabeca->px = NULL;

    lista->inicio = NULL;
    lista->fim = NULL;

    return lista;
}

int tamanho(ListaDuplaFlexivel *lista){
    CelulaDupla *i = lista->inicio->px;
    int tam = 0;

    while(i != NULL){
        tam++;
        i = i->px;
    }

    return tam;
}

void inserirInicio(ListaDuplaFlexivel *lista, int x){
    CelulaDupla *celula = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    celula->valor = x;
    celula->ant = lista->inicio;
    celula->px = lista->inicio->px;

    if(lista->inicio != NULL){
        lista->inicio->px->ant = celula;
    }else{
        lista->fim = celula;
    }

    lista->inicio->px = celula;
}
