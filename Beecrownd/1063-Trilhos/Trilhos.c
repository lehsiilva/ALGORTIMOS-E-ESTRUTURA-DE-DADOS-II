#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

void ler(char vagoes[], int n){
    for (int i = 0; i < n ; i ++){
        scanf(" %c", &vagoes[i]);
    }
}

void push(char vagoesA[], char pilha[], int *topo, int n, int *i){
    if(*topo < n){
        pilha[(*topo)++] = vagoesA[(*i)++];
        
    }
}

void pop(char pilha[], char *confere, int *a, int *topo){
    if(*topo > 0){
        confere[(*a)++] = pilha[--(*topo)];

    }
}

bool conferir(char confere[], char vagoesB[], int n){
    int cont = 0;
    
    for(int i = 0; i < n; i++){
        if(confere[i] == vagoesB[i]){
            cont++;
        }
    }
    
    if(cont == n){
        return true;
    }else{
        return false;
    }
}
int main() {
 
    int n;
    scanf("%d", &n);
    
    while( n != 0){
        int topo = 0;
        int i = 0;
        int a = 0;
        int j = 0;
        
        char *vagoesA = malloc(n * sizeof(char));
        ler(vagoesA, n);
        
        char *vagoesB =  malloc(n * sizeof(char));
        ler(vagoesB, n);
        
        char *pilha = malloc(n * sizeof(char));
        
        char *confere = malloc(n * sizeof(char));
        
        if (!vagoesA || !vagoesB || !pilha || !confere) {
            printf("Erro de memoria\n");
            return 1;
        }
        
        while (i < n) {
            // empilha sempre
            push(vagoesA, pilha, &topo, n, &i);
            printf("I");

            // desempilha enquanto o topo combina com a saída
            while (topo > 0 && pilha[topo-1] == vagoesB[j]) {
                pop(pilha, confere, &a, &topo);
                printf("R");
                j++;
            }
        }

        if(j != n){
            printf(" Impossible");
        }
        
        free(vagoesA);
        free(vagoesB);
        free(pilha);
        free(confere);
    
    }
 
    return 0;
}