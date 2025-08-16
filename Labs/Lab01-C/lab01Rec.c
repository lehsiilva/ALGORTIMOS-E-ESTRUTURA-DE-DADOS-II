#include <stdio.h>


    int maiusculo(char palavra[], int i){

        if(palavra[i] == '\0'){ // Caso Base
            return 0;

        }else if(palavra[i] >= 65 && palavra[i] <= 90){ 
            return 1 + maiusculo(palavra, i + 1);// Chamada Recursiva

        }else{
            return maiusculo(palavra, i + 1); // Chamada Recursiva
        }
    }
    

    int main(){

        printf("Digite a palavra: ");
        char palavra[100];
        scanf("%s", palavra);

        while (palavra[0] != 'F' && palavra[1] != 'I' && palavra[2] != 'M' && palavra[3] != '\0'){ 
        
            int resp = maiusculo(palavra, 0);

            printf("A palavra possui %d letras maiusculas", resp);

            printf("\n");

            printf("Digite a palavra: ");
            scanf("%s", palavra);

        }
             

        return 0;
        
    }
    

