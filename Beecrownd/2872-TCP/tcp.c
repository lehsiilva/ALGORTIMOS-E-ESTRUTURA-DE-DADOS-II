#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 1000

void Ordenar(char *v[], int n){
    int i,j;
    for(int i = 0; i < n - 1; i++){
        for(int j = 0; j < n - i - 1; j++){
            if(strcmp(v[j],v[j+1]) > 0){
                char *temp = v[j];
                v[j] = v[j+1];
                v[j+1]  = temp;
            }
        }
    }
}

int main(){
    char *pacotes[MAX];
    char linha[50];
    int I = 1; 
    int cont = 0;
    int lendo = 1;
    while( scanf("%d", &I) != EOF){
        
        if(I == 1){ 
            fgets(linha,sizeof(linha),stdin); //remover o getchar
            linha[strcspn(linha,"\n")] = '\0';
            pacotes[cont] = strdup(linha);
            cont++;
        }else{
            Ordenar(pacotes,cont);
            for(int i = 0; i < cont; i++){
                printf("%s\n", pacotes[i]);//trocar o n de lugar
                free(pacotes[i]);
            }
            cont = 0;
        }
    }
}