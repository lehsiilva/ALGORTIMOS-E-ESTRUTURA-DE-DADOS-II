#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int valor;
    struct Celula *px;
}Celula;


typedef struct ListaFlexivel{
    Celula *inicio;
    Celula *fim;
}ListaFlexivel;

ListaFlexivel *criarLista(){ //"Contrutor" celula cabeca
    ListaFlexivel *lista = (ListaFlexivel*)malloc(sizeof(ListaFlexivel));
    Celula *cabeca = (Celula*)malloc(sizeof(Celula));
    cabeca->valor = 0;
    cabeca->px = NULL;

    lista->inicio = cabeca;
    lista->fim = cabeca;

    return lista;
}

int tamanho(ListaFlexivel *lista){
    int tam = 0;

    Celula *i = lista->inicio->px;

    while(i != NULL){
        tam++;

        i = i->px;
    }

    return tam;  
}

void inserirInicio(ListaFlexivel *lista, int x){ //??
    Celula *tmp = (Celula*)malloc(sizeof(Celula));
    tmp->valor = x;
    tmp->px = lista->inicio->px;
    lista->inicio->px = tmp;

    if (lista->fim == lista->inicio) {
        lista->fim = tmp;
    }

}

void inserirFim(ListaFlexivel *lista, int x){ //ok
    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->valor = x;
    celula->px = NULL;

    lista->fim->px = celula;
    lista->fim = celula;
}

void inserirEscolhendo(ListaFlexivel *lista, int x, int posicao){//ok
    int tam = tamanho(lista);
    if(lista->inicio == lista->fim){
        printf("Lista Vazia");
    }else if(posicao < 0 || posicao > tam){
        printf("Posicao invalida!");
    }else if(posicao == 0){
        inserirInicio(lista,x);
    }else if(posicao == tam){
        inserirFim(lista,x);
    }else{
        Celula *i = lista->inicio;

        for(int j = 0; j < posicao; j++){
            i = i->px;
        }

        Celula *tmp = (Celula*)malloc(sizeof(Celula));
        tmp->valor = x;
        tmp->px = i->px;
        i->px = tmp;
    }

}

int removerInicio(ListaFlexivel *lista){ //ok
    Celula *tmp = lista->inicio->px;
    int valorRemovido = tmp->valor;
    lista->inicio->px = tmp->px;

    if (lista->inicio->px == NULL) { // lista ficou vazia
            lista->fim = lista->inicio;
        }

    free(tmp);

    return valorRemovido;
}

int removerFim(ListaFlexivel *lista){ //ok
    Celula *i = lista->inicio;

    while(i->px != lista->fim){
        i = i->px;
    }

    Celula *tmp = lista->fim;
    int remover = tmp->valor;

    i->px = NULL;
    lista->fim = i;  
    free(tmp);

    return remover;
}

int removerEscolhendo(ListaFlexivel *lista, int posicao){ //ok
    int tam = tamanho(lista);
    int elemento = 0;
    if(lista->inicio == lista->fim){
        printf("Lista Vazia");
    }else if(posicao < 0 || posicao > tam){
        printf("Posicao invalida!");
    }else if(posicao == 0){
        elemento = removerInicio(lista);
    }else if(posicao == tam){
        elemento = removerFim(lista);
    }else{
        Celula *i = lista->inicio;

        for(int j = 0; j < posicao; j++){
            i = i->px;
        }

        Celula *tmp = i->px;
        elemento = tmp->valor;
        i->px = tmp->px;

        free(tmp);

    }

    return elemento;
}

void mostrar(ListaFlexivel *lista){
    

    Celula *i = lista->inicio->px;
    printf("Lista: ");
    while(i != NULL){
        printf("%d ", i->valor);
        i = i->px;
    }
    printf("\n");
}

int main(){
    ListaFlexivel *lista = criarLista();
    int opc;

    do {
            printf("\n------ MENU ------\n");
            printf("0 - SAIR\n");
            printf("1 - INSERIR NO INICIO\n");
            printf("2 - INSERIR NO FIM\n");
            printf("3 - INSERIR ESCOLHENDO POSICAO\n");
            printf("4 - REMOVER INICIO\n");
            printf("5 - REMOVER FIM\n");
            printf("6 - REMOVER ESCOLHENDO POSICAO\n");
            printf("7 - MOSTRAR LISTA\n");
            printf("Escolha uma opcao: \n");

            scanf("%d", &opc);
            int valor; 
            int posicao;
            int resul;

            switch (opc) {
                case 1:
                    printf("Digite o valor: ");
                    scanf("%d", &valor);
                    inserirInicio(lista, valor);
                    break;
                case 2:
                    printf("Digite o valor: ");
                    scanf("%d", &valor);
                    inserirFim(lista, valor);
                    break;
                case 3:
                    printf("\nDigite o valor e a posição: ");
                    printf("\nDigite a posição: ");
                    scanf("%d", &valor);
                    scanf("%d", &posicao);
                    inserirEscolhendo(lista,valor,posicao);
                    break;
                case 4:
                    resul = removerInicio(lista);
                    if(resul != -1){
                        printf("Valor removido: %d ", resul);
                    }
                    break;
                case 5:
                    resul = removerFim(lista);
                    if(resul != -1){
                        printf("Valor removido: %d ", resul);
                    }
                    break;
                case 6:
                    printf("\nDigite a posição: ");
                    scanf("%d", &posicao);
                    resul = removerEscolhendo(lista,posicao);
                    if(resul != -1){
                        printf("Valor removido: %d ", resul);
                    }
                    break;
                case 7:
                    mostrar(lista);
                    break;
                case 0:
                    printf("\nSaindo...");
                    break;
                default:
                    printf("\nOpção inválida!");
            }

        } while (opc != 0);
}