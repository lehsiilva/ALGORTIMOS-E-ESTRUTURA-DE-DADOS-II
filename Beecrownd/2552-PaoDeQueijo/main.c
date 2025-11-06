#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int valor; 
    struct Celula *dir;
    struct Celula *esq;
    struct Celula *sup;
    struct Celula *inf;

}Celula;

typedef struct Matriz{
    Celula ***dados;
    Celula *inicio;
    int linha;
    int coluna;

}Matriz;

Matriz *criarMatriz(int linha, int coluna){
    Matriz *matriz = (Matriz*)malloc(sizeof(Matriz));
    matriz-> linha = linha;
    matriz->coluna = coluna;

    matriz->dados = (Celula***)malloc(linha * sizeof(Celula**));

    for(int i = 0; i < linha; i++){
        matriz->dados[i] = (Celula**)malloc(coluna * sizeof(Celula*));
        for(int j = 0; j < coluna; j++){
            matriz->dados[i][j] = (Celula*)malloc(sizeof(Celula));
            matriz->dados[i][j]->valor = 0;
            matriz->dados[i][j]->dir = NULL;
            matriz->dados[i][j]->esq = NULL;
            matriz->dados[i][j]->sup = NULL;
            matriz->dados[i][j]->inf = NULL;
        }
    }
    
    for(int i = 0; i < linha; i++){
        for(int j = 0; j < coluna; j++){
            if(i > 0){
                matriz->dados[i][j]->sup =  matriz->dados[i - 1][j];
            }
            if(i < linha -1){
                matriz->dados[i][j]->inf =  matriz->dados[i + 1][j];
            }
            if(j > 0){
                matriz->dados[i][j]->esq =  matriz->dados[i][j - 1];
            }
            if(j < coluna - 1){
                matriz->dados[i][j]->dir =  matriz->dados[i][j + 1];
            }
        }
    }

    matriz->inicio = matriz->dados[0][0];

    return matriz;

}

void preencher(Matriz *matriz){
    for(int i = 0; i < matriz->linha; i++){
        for(int j = 0; j < matriz->coluna; j++){
            scanf("%d", &matriz->dados[i][j]->valor);
        }
    }
}


void paoqueijo(Matriz *matriz){
    
    for(int i = 0; i < matriz->linha; i++){
        for(int j = 0; j < matriz->coluna; j++){
            if(matriz->dados[i][j]->valor == 1){
                printf("9");
            }else{
                int cont = 0;
                if(matriz->dados[i][j]-> dir != NULL && matriz->dados[i][j]->dir->valor == 1){cont++;}
                if(matriz->dados[i][j]-> esq != NULL && matriz->dados[i][j]->esq->valor == 1){cont++;}
                if(matriz->dados[i][j]-> sup != NULL && matriz->dados[i][j]->sup->valor == 1){cont++;}
                if(matriz->dados[i][j]-> inf != NULL && matriz->dados[i][j]->inf->valor == 1){cont++;}
                printf("%d", cont);
            }

        }

        printf("\n");
    }
}

/*void imprimir(Matriz *matriz){
    for(int i = 0; i < matriz->linha; i++){
        for(int j = 0; j < matriz->coluna; j++){
            printf("%d ", matriz->dados[i][j]->valor);
        }

        printf("\n");
    }

}*/

int main(){
    int linha,coluna;

    while(scanf("%d %d", &linha, &coluna) != EOF ){
        Matriz *matriz = criarMatriz(linha,coluna);

        preencher(matriz);
        paoqueijo(matriz);
    }
}
