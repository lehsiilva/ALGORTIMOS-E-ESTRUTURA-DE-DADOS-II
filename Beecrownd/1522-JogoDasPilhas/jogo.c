#include <stdio.h>
#include <stdbool.h>
typedef struct Celula{
    int valor;
    struct Celula *px;
}Celula;

typedef struct Pilha{
    struct Celula *topo;
}Pilha;

Pilha *criarPilha(){
    Pilha *pilha = (Pilha*)malloc(sizeof(Pilha));
    pilha->topo = NULL;
}

void push(Pilha *pilha, int x){
    Celula *celula = (Celula*)malloc(sizeof(Celula));
    celula->px = pilha->topo;
    pilha->topo = celula;
    
}
 
void pop(Pilha *pilha){
    pilha->topo = pilha->topo->px;
}

int mostrarTopo(Pilha *pilha){
    Celula *i = pilha->topo;
    return i->valor;
}

bool verificar(Pilha *pilha){
    int a = mostrarTopo(pilha);
    int b = mostrarTopo(pilha);
    int c = mostrarTopo(pilha);


}

int main() {
 
    int n;
    while( scanf("%d", &n) != EOF){
        int a[n];
        int b[n];
        int c[n];
        bool verifica = false;
        int cont = 0;
    
        
        for(int i = 0; i < n; i++){
            scanf("%d %d %d", &a[i], &b[i],&c[i]);
        }
        
        for(int i = 0; i < n; i++){
            verifica = false;
            int soma = a[i] + b[i] + c[i];
            if(a[i]%3 == 0 || b[i]%3 == 0 || c[i]%3 == 0){
                verifica = true;
            }else if(soma%3 == 0){
                verifica = true;
            }else if(((a[i] + b[i])%3 == 0) || ((a[i] + c[i])%3 == 0) || ((c[i] + b[i])%3 == 0)){
                verifica = true;
            }
            
        }
        
        if(verifica){
            printf("1\n");
        }else{
            printf("0\n");
        }
        

    }
 
    return 0;
}