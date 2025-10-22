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

    lista->inicio = cabeca;
    lista->fim = cabeca;

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

    if(lista->inicio->px != NULL){
        lista->inicio->px->ant = celula;
    }else{
        lista->fim = celula;
    }

    lista->inicio->px = celula;
}

void inserirFim(ListaDuplaFlexivel *lista, int x){
    CelulaDupla *celula = (CelulaDupla*)malloc(sizeof(CelulaDupla));
    celula->valor = x;
    celula->ant = lista->fim;
    celula->px = NULL;

    lista->fim->px = celula;
    lista->fim = celula;
}

void inserirEscolhendo(ListaDuplaFlexivel *lista, int x, int pos){
    int tam = tamanho(lista);

    if(pos < 0 || pos > tam){
        printf("Posicao invalida!");
    }else if(pos == 0){
        inserirInicio(lista,x);
    }else if(pos == tam){
        inserirFim(lista, x);
    }else{
        CelulaDupla *i = lista->inicio->px;
        for(int j = 0; j < pos; j++){
            i = i->px;
        }

        CelulaDupla *tmp = (CelulaDupla*)malloc(sizeof(CelulaDupla));
        tmp->valor = x;
        tmp->ant = i->ant;
        tmp->px = i;

        i->ant->px = tmp;
        i->ant = tmp;
    }

}

int removerInicio(ListaDuplaFlexivel *lista){
    if(lista->inicio == lista->fim){
        printf("Lista Vazia");
    }

    CelulaDupla *tmp = lista->inicio->px;
    int remover = tmp->valor;
    lista->inicio->px = tmp->px;

    if(lista->inicio->px != NULL){
        tmp->ant = lista->inicio;
    }else{
        lista->fim = tmp;
    }

    free(tmp);
    return remover;
}

int removerFim(ListaDuplaFlexivel *lista){
    if(lista->inicio == lista->fim){
        printf("Lista Vazia");
    }

    CelulaDupla *tmp = lista->fim;
    int remover = tmp->valor;
    lista->fim = tmp->ant;
    lista->fim->px = NULL;

    free(tmp);
    return remover;

}

int removerEscolhendo(ListaDuplaFlexivel *lista, int pos){
    int tam = tamanho(lista);
    int elemento = 0;

    if(pos < 0 || pos > tam){
        printf("Posicao invalida!");
    }else if(pos == 0){
        elemento = removerInicio(lista);
    }else if(pos == tam - 1){
        elemento = removerFim(lista);
    }else{
        CelulaDupla *i = lista->inicio->px;
        for(int j = 0; j < pos; j++){
            i = i->px;
        }
        CelulaDupla *tmp = i;
        elemento = tmp->valor;
        i->ant->px = i->px;
        i->px->ant = i->ant;

        free(tmp);
    }

    return elemento;
    
}

void mostrar(ListaDuplaFlexivel *lista){
    CelulaDupla *i = lista->inicio->px;

    printf("Lista: ");
    while(i != NULL){
        printf("%d ", i->valor);
        i = i->px;
    }
    printf("\n");
}

void mostrarInvertido(ListaDuplaFlexivel *lista){
    CelulaDupla *i = lista->fim;

    printf("Lista Invertida: ");
    while(i != lista->inicio){
        printf("%d ", i->valor);
        i = i->ant;
    }
    printf("\n");
}

int main(){
    ListaDuplaFlexivel *lista = criarLista();
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
            printf("8 - MOSTRAR LISTA INVERTIDA\n");
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
                case 8:
                    mostrarInvertido(lista);
                    break;
                case 0:
                    printf("\nSaindo...");
                    break;
                default:
                    printf("\nOpção inválida!");
            }

        } while (opc != 0);
}