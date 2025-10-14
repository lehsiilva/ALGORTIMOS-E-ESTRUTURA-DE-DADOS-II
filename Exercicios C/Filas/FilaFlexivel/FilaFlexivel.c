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

FilaFlexivel *criarFila(){ // "Construtor"

    FilaFlexivel *fila = (FilaFlexivel*)malloc(sizeof(FilaFlexivel));

    fila->inicio = NULL;
    fila->fim = NULL;

    return fila;
}

void enqueue(FilaFlexivel *fila, int valor){

    Celula *celula = (Celula*)malloc(sizeof(Celula));

    celula->valor = valor;
    celula->proximo = NULL;

    if(fila->inicio == NULL){
        fila->inicio = celula;
    }else{
        fila->fim->proximo = celula;
    }

    fila->fim = celula;
}

int dequeue(FilaFlexivel *fila){

    if(fila->inicio == NULL){
        printf("\nFila Vazia");
    }

    Celula *celulaRemovida = fila->inicio;
    int valorRemovido = celulaRemovida->valor;
    fila->inicio = fila->inicio->proximo;

    if (fila->inicio == NULL) {
        fila->fim = NULL;
    }

    free(celulaRemovida);

    return valorRemovido;
}

void mostrar(FilaFlexivel *fila){

    if(fila->inicio == NULL){
        printf("\nFila Vazia");
    }

    Celula *i = fila->inicio;

    printf("Fila: ");

    while(i != NULL){
        printf("%d ", i->valor);

        i = i->proximo;
    }
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