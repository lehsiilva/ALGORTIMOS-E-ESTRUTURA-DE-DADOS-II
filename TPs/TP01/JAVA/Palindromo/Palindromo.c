#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool verifica(char *palavra, int i, int j){ //Faz a verificacao e retorna se o dado é true ou false
    if(i >= j){
        return true;
    }else if(palavra[i] != palavra[j]){
        return false;
    }else{
        return verifica(palavra, i+1, j-1);
    }

}

int main(){
    char *palavra = malloc(1000 * sizeof(char)); //alocca memoria para os caracteres
    scanf("%[^\n]",palavra);
    getchar(); // eliminar o buffer

    int cont;

    while(!(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0')){ //Verifica se a palavra é diferente de fim

        cont = 0;
        

        while(palavra[cont] != '\0'){
            cont++;
            
        }

        if(verifica(palavra, 0, cont - 1) == true){ //Verifica se é true ou false para retornar a resposta
            printf("SIM");
            printf("\n");
        }else{
            printf("NAO");
            printf("\n");
        }

        
        scanf("%[^\n]",palavra);
        getchar();
    }

    free(palavra);


    return 0;
}