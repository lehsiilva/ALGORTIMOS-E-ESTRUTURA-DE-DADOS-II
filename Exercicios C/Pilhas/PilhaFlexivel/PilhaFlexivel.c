#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int valor;
    struct Celula *px;
}Celula;

typedef struct Pilha{
    Celula *topo;
}Pilha;

Pilha *criarPilha(){ // "Construtor"
    Pilha *pilha = (Pilha*)malloc(sizeof(Pilha));
    pilha->topo = NULL;
    return pilha;
}

void push(Pilha *pilha, int x){
    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->valor = x;
    celula->px = pilha->topo;
    pilha->topo = celula;
}

int pop(Pilha *pilha){
    Celula *celulaRemovida = (Celula*)malloc(sizeof(Celula));

    if(pilha->topo == NULL){
        printf("Pilha Vazia!");
        return -1;
    }

    int valorRemovido = pilha->topo->valor;
    pilha->topo = pilha->topo->px;
    free(celulaRemovida);

    return valorRemovido;

}

void mostrar(Pilha *pilha){
    Celula *i = pilha->topo;

    printf("Pilha: ");
    while(i != NULL){
        printf("%d ", i->valor);
        i = i->px;
    }  

    printf("\n");
}


int main(){
    
    Pilha *pilha = criarPilha();
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
                    push(pilha,valor);
                    break;
            }
                case 2:{
                    int resul = pop(pilha);
                    printf("\nValor removido: %d", resul);
                    break;
            }
                case 3:
                    mostrar(pilha);
                    break;
        }
            
    } while (opc != 0);

    return 0;
    
}