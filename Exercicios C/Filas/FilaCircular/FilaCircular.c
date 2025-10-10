#include <stdio.h>
#include <stdlib.h>

#define MAX 5

void Inserir(int vet[], int *inicio, int *fim, int tamTotal, int valor){
    if((*fim + 1) % tamTotal == *inicio){
        printf("Fila Cheia");
    }else{
        vet[*fim] = valor;
        *fim = (*fim + 1) % tamTotal;
    }

}

int Remover(int vet[], int *inicio, int *fim, int tamTotal){
    if(*inicio == *fim){
        printf("Fila Vazia");
        return -1;
    }else{
        int resp = vet[*inicio];
        *inicio = (*inicio + 1) % tamTotal;
        return resp;
    }
}

void mostrar(int vet[], int *inicio, int *fim, int tamTotal){
    int i = *inicio;

    while( i != *fim){
        printf("%d ", vet[i]);
        i = (i + 1) % tamTotal;
    }
}

int main(){
    
    int inicio = 0;
    int fim = 0;
    int tamTotal = MAX + 1;
    int vet[tamTotal];
    int opc;


    do {
        printf("\n===== MENU FILA CIRCULAR =====");
        printf("\n1 - Inserir elemento");
        printf("\n2 - Remover elemento");
        printf("\n3 - Mostrar fila");
        printf("\n0 - Sair");
        printf("\nEscolha uma opcao: ");

        int valor;
        scanf("%d", &opc);

        switch (opc) {
            case 1:
                printf("\nDigite o valor a inserir: ");
                scanf("%d", &valor);
                Inserir(vet, &inicio, &fim, tamTotal, valor);
            break;

            case 2:{
                int resul = Remover(vet, &inicio, &fim, tamTotal);
                if (resul != -1){
                    printf("\nValor removido: %d\n", resul);
                }
            break;
            }
            case 3:
                printf("\nFila atual: ");
                mostrar(vet, &inicio, &fim, tamTotal);
            break;

            case 0:
                printf("\nEncerrando programa...");
            break;

            default:
                printf("\nOpção inválida! Tente novamente.");
            }

        } while (opc != 0);

        
}

