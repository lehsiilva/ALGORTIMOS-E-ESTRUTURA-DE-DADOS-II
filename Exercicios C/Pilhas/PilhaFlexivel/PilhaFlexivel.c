#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int valor;
    struct Celula *proximo;

}Celula;

typedef struct{

    Celula *topo;

}PilhaFlexivel;

PilhaFlexivel* criarPilha() { // "Construtor"
   
    PilhaFlexivel *novaPilha = (PilhaFlexivel*)malloc(sizeof(PilhaFlexivel));  // Aloca espaço para a estrutura PilhaFlexivel
    
    novaPilha->topo = NULL; 

    return novaPilha;
}

void push(PilhaFlexivel *pilha, int x){

    Celula* celula = (Celula*)malloc(sizeof(Celula));
    celula->valor = x;
    celula->proximo = pilha->topo;
    pilha->topo = celula;
}

int pop(PilhaFlexivel *pilha){

    if(pilha->topo == NULL){
        printf("Pilha Vazia");
    }

    int valorRemovido = pilha->topo->valor;
    Celula* celulaRemovida = (Celula*)malloc(sizeof(Celula));
    celulaRemovida = pilha->topo;
    pilha->topo = pilha->topo->proximo;
    
    free(celulaRemovida);

    return valorRemovido;

}

void mostrar(PilhaFlexivel *pilha){

    Celula *i = pilha->topo;

    printf("Pilha: ");
    while(i != NULL){
        printf("%d ", i->valor);

        i = i->proximo;
    }
}


int main(){
    
    PilhaFlexivel *pilhaFlexivel = criarPilha();
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
                    push(pilhaFlexivel,valor);
                    break;
            }
                case 2:{
                    int resul = pop(pilhaFlexivel);
                    printf("\nValor removido: %d", resul);
                    break;
            }
                case 3:
                    mostrar(pilhaFlexivel);
                    break;
        }
            
    } while (opc != 0);

    return 0;
    
}