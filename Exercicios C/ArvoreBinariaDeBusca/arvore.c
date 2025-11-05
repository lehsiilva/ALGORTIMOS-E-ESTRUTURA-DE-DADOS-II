#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct No{
   int valor;
   struct No *dir;
   struct No *esq;
}No;


typedef struct Arvore{
    struct No *raiz;
}Arvore;


Arvore *CriarArvore(){
    Arvore *arvore = (Arvore*)malloc(sizeof(Arvore));
    arvore->raiz = NULL;

    return arvore;
}

No *criarNo(int valor) {
    No* novo = (No*) malloc(sizeof(No));
    novo->valor = valor;
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}

void inserir(Arvore *arvore, int x){
    arvore->raiz = inserirRec(arvore->raiz,x);
}


No *inserirRec(No *raiz,int x){
    if(raiz == NULL){
        Arvore *nova = (Arvore*)malloc(sizeof(Arvore));
        return nova;
    }

    if(x > raiz->valor){
        return inserirRec(raiz->dir, x);
    }else if(x < raiz->valor){
        return inserirRec(raiz->esq, x);
    }

    return raiz;
}

bool pesquisar(Arvore *arvore, int x){
    return pesquisaRec(arvore->raiz, x);
}

bool pesquisaRec(No *raiz, int x){
    bool resp = false;

    if(x == raiz->valor){
        resp = true;
    }

    if(x < raiz->valor){
        return pesquisaRec(raiz->esq, x);
    }else if(x > raiz->valor){
        return pesquisaRec(raiz->dir, x);
    }

    return resp;
}

int maiorEsq(No *raiz){
    No *i = raiz;
    while(i->dir != NULL){
        i = i->dir;
    }

    return i;
}

void remover(Arvore *arvore, int x){
    return removerRec(arvore->raiz, x);
}

No *removerRec(No *raiz, int x){
    if(x > raiz->valor){
        return inserirRec(raiz->dir, x);
    }else if(x < raiz->valor){
        return inserirRec(raiz->esq, x);
    }else{

        if(raiz->dir == NULL && raiz->esq == NULL){
            free(raiz);
            return NULL;
        }

        if(raiz->dir == NULL){
            No *temp = raiz->esq;
            free(raiz);
            return temp;
        }else if(raiz->esq == NULL){
            No* temp = raiz->dir;
            free(raiz);
            return temp;
        }

        int maiorValor = maiorEsq(raiz->esq);
        raiz->valor = maiorValor;
        raiz->esq = removerRec(raiz->esq, maiorValor);
    }

    return raiz;


}

