/*Escreva uma funcao que receba uma string e retorne a quantidade de caracteres minusculos*/

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
}

int main(){

    printf("Digite a quantidade de letras: ");
    int qntd;
    scanf("%d", &qntd);

    getchar();


    printf("Digite a palavra: ");
    char str[qntd];
    scanf("%[^\n]", &str);
    
   

    minusculos(str, qntd);

    return 0;

}

