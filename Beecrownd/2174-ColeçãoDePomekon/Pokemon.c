#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct No {
    char palavra[60];
    struct No* esq;
    struct No* dir;
} No;

No* novoNo(char* palavra) {
    No* no = (No*) malloc(sizeof(No));
    strcpy(no->palavra, palavra);
    no->esq = NULL;
    no->dir = NULL;
    return no;
}

No* inserir(No* raiz, char* palavra) {
    if (raiz == NULL) {
        return novoNo(palavra);
    }

    int cmp = strcmp(palavra, raiz->palavra);

    if (cmp > 0) {
        raiz->dir = inserir(raiz->dir, palavra);
    } else if (cmp < 0) {
        raiz->esq = inserir(raiz->esq, palavra);
    }

    return raiz;
}

int contar(No* raiz) {
    if (raiz == NULL) {
        return 0;
    }
    return 1 + contar(raiz->esq) + contar(raiz->dir);
}


int main() {
    int n;
    
    while (scanf("%d", &n) != EOF) {
        No* raiz = NULL;
        char palavra[60];

        for (int i = 0; i < n; i++) {
            scanf("%s", palavra);
            raiz = inserir(raiz, palavra);
        }

        int cont = contar(raiz);
        int soma = 151 - cont;

        printf("Falta(m) %d pomekon(s).\n", soma);

    }

    return 0;
}
