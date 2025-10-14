#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{

    int valor;
    struct Celula *proximo;

}Celula;

typedef struct Pilha{

    Celula *topo;

}Pilha;

Pilha* criarPilha(){ // "Construtor"

    Pilha *PilhaFlexivel = (Pilha*)malloc(sizeof(Pilha));

    PilhaFlexivel->topo = NULL;

    return PilhaFlexivel;
}

void push(Pilha *pilha, int valor){

    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->valor = valor;
    celula->proximo = pilha->topo;
    pilha->topo = celula;
}

void inverter(Pilha *pilha){
    Celula *atual = NULL;
    Celula *proximo = pilha->topo;
    Celula *anterior = NULL;

    while(atual != NULL){
        proximo = atual->proximo;
        atual->proximo = anterior;
    
        anterior = atual;
        atual = proximo;

    }
}

void mostrar(Pilha *pilha){

    if(pilha->topo == NULL){
        printf("Pilha Vazia");
    }

    Celula *i = pilha->topo;

    while(i != NULL){
        printf("%d ", i->valor);

        i = i->proximo;
    }
}

int main(){
    
    Pilha *pilha = criarPilha();
    int opc;

    do { 
            printf("\n----- MENU -----");
            printf("\nDigite 0 para SAIR");
            printf("\nDigite 1 para INSERIR");
            printf("\nDigite 2 para INVERTER");
            printf("\nDigite 3 para MOSTRAR");
            printf("\nEscolha uma opcao: ");

            scanf("%d", &opc);

            switch(opc) {
                case 1:{
                    printf("\nDigite o valor: ");
                    int valor; 
                    scanf("%d", &valor);
                    push(pilha,valor);
                    break;
            }
                case 2:
                    inverter(pilha);
                    break;
            
                case 3:
                    mostrar(pilha);
                    break;
        }
            
    } while (opc != 0);

    return 0;
    
}