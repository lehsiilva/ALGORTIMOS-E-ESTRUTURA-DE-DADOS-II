#include <stdio.h>


    char maiusculo(char palavra[]){
    
        int cont = 0;

        while(palavra[cont] != '\0'){
            cont++;
        }

        int contar = 0;

        if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0'){
            return 0;
        }else{

            for(int i = 0; i < cont; i++){

                if(palavra[i] >= 65 && palavra[i] <= 90){

                    contar++;

                }
                
            }

                printf("Quantidade de Letras MAIUSCULAS: %d",contar);

                printf("\nDigite a palavra: ");
                scanf("%s", palavra);
        
            return maiusculo(palavra); //Chamada Recursiva
        }
    }


    int main(){

        printf("Digite a palavra: ");
        char palavra[100];
        scanf("%s", palavra);

        maiusculo(palavra);
             

        return 0;
        
    }
    

