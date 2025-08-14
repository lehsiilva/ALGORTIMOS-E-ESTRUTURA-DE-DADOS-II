#include <stdio.h>


    void maiusculo(char palavra[], int cont){
    
        int contar = 0;

        for(int i = 0; i < cont; i++){

            if(palavra[i] >= 65 && palavra[i] <= 90){

                contar++;

            }
            
        }

            printf("Quantidade de Letras MAIUSCULAS: %d",contar);

            printf("\nDigite a palavra: ");
            scanf("%s", palavra);

    }


    int main(){

        int cont = 0;

        printf("Digite a palavra: ");
        char palavra[100];
        scanf("%s", palavra);
        
        while(palavra[cont] != '\0'){
            cont++;
        }

        
        while (palavra[0] != 'F' && palavra[1] != 'I' && palavra[2] != 'M' && palavra[3] != '\0'){ 
            
           
            maiusculo(palavra,cont);
             
            
        }

         return 0;
        
    }
    

