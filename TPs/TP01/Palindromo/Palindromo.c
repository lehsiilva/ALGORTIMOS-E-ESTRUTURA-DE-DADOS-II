#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool verifica(char *palavra, int i, int j){
    if(i >= j){
        return true;
    }else if(palavra[i] != palavra[j]){
        return false;
    }else{
        return verifica(palavra, i+1, j-1);
    }

}

int main(){
    char *palavra = malloc(1000 * sizeof(char));;
    scanf("%[^\n]",palavra);
    getchar();

    int cont;

    while(!(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0')){

        cont = 0;
        

        while(palavra[cont] != '\0'){
            cont++;
            
        }

        if(verifica(palavra, 0, cont - 1) == true){
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