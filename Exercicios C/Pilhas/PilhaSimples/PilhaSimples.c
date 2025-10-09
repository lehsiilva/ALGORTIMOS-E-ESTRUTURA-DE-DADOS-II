#include <stdio.h>
#include <stdlib.h>

void push(int vet[], int *tam, int tamTotal,int valor){
    if(*tam < tamTotal){
            vet[*tam] = valor;
            (*tam)++;
        }else{
            printf("\nFila Cheia");
        }
}

void pop(int vet[], int *tam){
    if (*tam == 0) {
            printf("\nFila vazia!");
        } else {

            (*tam)--;
        }
}

void mostrar(int vet[], int tam){

    for(int i = tam - 1; i >= 0; i--){
                printf("%d ", vet[i]);
            }
          
            printf("\n");
}

int main(){
    int tam = 0;
    int tamTotal = 7;
    int vet[tamTotal];
    int opc;

    do{ 
        printf("\n----- MENU -----");
        printf("\nDigite 0 para SAIR");
        printf("\nDigite 1 para INSERIR");
        printf("\nDigite 2 para REMOVER");
        printf("\nDigite 3 para MOSTRAR");
        printf("\nDigite uma opcao: ");
        
        int valor;
        scanf("%d", &opc);

        switch (opc) {
            case 1:
                printf("\nDigite o valor a ser inserido: ");
                scanf("%d", &valor);
                push(vet, &tam, tamTotal, valor);
            break;

            case 2:
                pop(vet, &tam);
            break;
            case 3:
                mostrar(vet,tam);
            break;

            case 0:
                printf("SAINDO....");
            break;

            default:
                printf("\nOpcao invalida!");
            }

        } while (opc != 0);
}
