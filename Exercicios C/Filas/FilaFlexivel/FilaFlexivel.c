#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int valor;
    struct Celula *proximo;
}Celula;

typedef struct FilaFlexivel{

    Celula *inicio;
    Celula *fim;

}FilaFlexivel;

FilaFlexivel *criarFila(){ // "Construtor" celula cabeca
    FilaFlexivel *fila = (FilaFlexivel*)malloc(sizeof(FilaFlexivel));
    Celula *cabeca = (Celula*)malloc(sizeof(Celula));
    cabeca->valor = 0;
    cabeca->proximo = NULL;

    fila->inicio= cabeca;
    fila->fim = fila->inicio;

    return fila;
    
}

void enqueue(FilaFlexivel *fila, int x){
    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->valor = x;
    celula->proximo = NULL;

    fila->fim->proximo = celula;
    fila->fim = celula;
    
}

int dequeue(FilaFlexivel *fila){
    if(fila->inicio == fila->fim){
        printf("Fila vazia!");
    }
    Celula *tmp = fila->inicio->proximo;
    int valorRemover = tmp->valor;
    fila->inicio->proximo = tmp->proximo;

    free(tmp);
    
    return valorRemover;
}

void mostrar(FilaFlexivel *fila){
    if(fila->inicio == fila->fim){
        printf("Fila vazia!");
    }

    Celula *i = fila->inicio->proximo;
    printf("Fila: ");
    while(i != NULL){
        printf("%d ", i->valor);
        i = i->proximo;
    }
    printf("\n");
    
}

int main(){

    FilaFlexivel *fila = criarFila();
    int opc;

    do { 
            printf("\n----- MENU -----");
            printf("\nDigite 0 para SAIR");
            printf("\nDigite 1 para INSERIR");
            printf("\nDigite 2 para DELETAR");
            printf("\nDigite 3 para MOSTRAR");
            printf("\nEscolha uma opcao: ");

            scanf("%d", &opc);

            switch(opc) {
                case 1:{
                    printf("\nDigite o valor: ");
                    int valor; 
                    scanf("%d", &valor);
                    enqueue(fila,valor);
                    break;
            }
                case 2:{
                    int resul = dequeue(fila);
                    printf("\nValor removido: %d", resul);
                    break;
            }
                case 3:
                    mostrar(fila);
                    break;
        }
            
    } while (opc != 0);

    return 0;
}