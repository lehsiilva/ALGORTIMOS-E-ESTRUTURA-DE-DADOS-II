#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int valor;
    struct Celula *px;
}Celula;

typedef struct Pilha{
    Celula *topo;
}Pilha;

Pilha *criarPilha(){
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
    int valorRemovido =0;
    if(pilha->topo == NULL) return -1;
    
    if(pilha->topo != NULL){
        Celula *remover = pilha->topo;
        valorRemovido = remover->valor;
        pilha->topo = pilha->topo->px;
        free(remover);
    }
    return valorRemovido;
}

int min(Pilha *pilha){
    Celula *i = pilha->topo;
    if(i == NULL) return -1; 

    int menor = i->valor;
    while(i != NULL){
        if(i->valor < menor)
            menor = i->valor;
        i = i->px;
    }
    return menor;
}

int main() {
    int n;
    while(scanf("%d", &n) != EOF){
        Pilha *pilha = criarPilha();
        char opc[100];
        int valor;
        int resul;

        for(int i = 0; i < n; i++){
            scanf("%s", opc);

            if(opc[0]=='P' && opc[1]=='U' && opc[2]=='S' && opc[3]=='H' && opc[4]=='\0'){
                scanf("%d", &valor);
                push(pilha, valor);
            } else if(opc[0]=='P' && opc[1]=='O' && opc[2]=='P' && opc[3]=='\0'){
                resul = pop(pilha);
                if(resul == -1){
                    printf("EMPTY\n");
                }
            } else {
                resul = min(pilha);
                if(resul != -1){
                    printf("%d\n", resul);
                }else{
                    printf("EMPTY\n");
                }
            }
        }
    }

    return 0;
}
