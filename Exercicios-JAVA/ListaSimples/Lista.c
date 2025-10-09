#include <stdio.h>
#include <stdlib.h>

 // INSERIR NO FIM (normal)
    void inserir(int vet[], int *tam, int tamTotal,int valor) {
        if(*tam < tamTotal){
            vet[*tam] = valor;
            (*tam)++;
        }else{
            printf("Lista Cheia!");
        }
    }

    // INSERIR NO INICIO
    void inserirInicio(int vet[], int *tam, int tamTotal,int valor) {
        if(*tam < tamTotal){
            for(int i = *tam; i > 0; i--){
                vet[i] = vet[i-1];
            }
            vet[0] = valor;
            (*tam)++;

        }else{
            printf("Lista Cheia!");
        }
    }

    // INSERIR EM POSIÇÃO ESCOLHIDA
    void inserirEscolhendo(int vet[], int *tam, int tamTotal, int valor, int posicao) {
        if(*tam == tamTotal){
            printf("Lista cheia!");
        }else if(posicao < 0 || posicao >= tamTotal){
            printf("Posicao invalida");
        }else{
            for(int i = *tam; i > posicao; i--){
                vet[i] = vet[i-1];
            }

            vet[posicao] = valor;
            (*tam)++;
        }
    }

    // REMOVER DO INICIO
    void removerInicio(int vet[], int *tam) {
        if(*tam == 0){
            printf("Lista Vazia");
        }else{
            for(int i = 0; i < *tam -1; i++){
                vet[i] = vet[i +1]; 
            }

            (*tam)--;
        }
    }

    // REMOVER DO FIM
    void removerFim(int *tam) {
        if (*tam == 0) {
            printf("Lista vazia!");
            
        }else{
            (*tam)--;
        }

    }

    // REMOVER EM POSIÇÃO ESCOLHIDA
    void removerEscolhendo(int vet[], int *tam, int tamTotal, int posicao) {

        if(*tam == tamTotal){
            printf("Lista cheia!");
        }else if(posicao < 0 || posicao >= tamTotal){
            printf("Posicao invalida");
        }else{
            for(int i = posicao; i < *tam-1; i++){
                vet[i] = vet[i+1];
            }
            (*tam)--;
        }
    }

    // MOSTRAR LISTA
    void mostrar(int vet[],int tam) {
        if (tam == 0) {
            printf("Lista vazia!");
            
        }else{
            printf("Lista: ");
            for(int i = 0; i < tam; i++){
                printf("%d ", vet[i]);
            }
        }
    }

int main(){
    int tam = 0;
    int tamTotal = 7;
    int vet[tamTotal];

    int opc;

        do {
            printf("\n------ MENU ------\n");
            printf("0 - SAIR\n");
            printf("1 - INSERIR NORMAL (FIM)\n");
            printf("2 - INSERIR NO INICIO\n");
            printf("3 - INSERIR ESCOLHENDO POSICAO\n");
            printf("4 - REMOVER INICIO\n");
            printf("5 - REMOVER FIM\n");
            printf("6 - REMOVER ESCOLHENDO POSICAO\n");
            printf("7 - MOSTRAR LISTA\n");
            printf("Escolha uma opcao: \n");

            scanf("%d", &opc);
            int valor; 
            int posicao;

            switch (opc) {
                case 1:
                    printf("Digite o valor: ");
                    scanf("%d", &valor);
                    inserir(vet, &tam, tamTotal, valor);
                    break;
                case 2:
                    printf("Digite o valor: ");
                    scanf("%d", &valor);
                    inserirInicio(vet, &tam, tamTotal, valor);
                    break;
                case 3:
                    printf("\nDigite o valor e a posição: ");
                    printf("\nDigite a posição: ");
                    scanf("%d", &valor);
                    scanf("%d", &posicao);
                    inserirEscolhendo(vet, &tam, tamTotal, valor,posicao);
                    break;
                case 4:
                    removerInicio(vet,&tam);
                    break;
                case 5:
                    removerFim(&tam);
                    break;
                case 6:
                    printf("\nDigite a posição: ");
                    scanf("%d", &posicao);
                    removerEscolhendo(vet, &tam, tamTotal,posicao);
                    break;
                case 7:
                    mostrar(vet,tam);
                    break;
                case 0:
                    printf("\nSaindo...");
                    break;
                default:
                    printf("\nOpção inválida!");
            }

        } while (opc != 0);
}