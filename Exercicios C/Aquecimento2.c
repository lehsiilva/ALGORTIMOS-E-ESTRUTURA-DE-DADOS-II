/*Altere o seu programa para ler varias strings a leitura sera interrompida quando for digitado a palavra fim*/

#include <stdio.h>
#include <stdlib.h>

void minusculos(char c[], int qntd){
    
    int cont = 0;

    for(int i = 0; i < qntd; i++){

        if(c[i] >= 65 && c[i] <= 90){
            cont++;
        }

    }

    printf("Quantidade de minusculos: %d", cont);
    printf("\n");
}

int main(){

    printf("Digite a quantidade de letras: ");
    int qntd;
    scanf("%d", &qntd);

    getchar();

    printf("Digite a palavra: ");
    char str[qntd];
    scanf("%[^\n]", &str);
    getchar();
    
    
    

    while(!(str[0] == 'F' && str[1] == 'I' && str[2] == 'M' && str[3] == '\0')){

        minusculos(str, qntd);
        
        printf("Digite a palavra: ");
        scanf("%[^\n]", &str);

        printf("\n");

        getchar();
       
    }

    

    return 0;

}



