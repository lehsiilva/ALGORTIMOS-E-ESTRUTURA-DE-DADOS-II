/*Crie um método iterativo que recebe uma string e retorna true se a mesma é composta somente por vogais.

Crie outro método iterativo que recebe uma string e retorna true se a mesma é composta somente por consoantes.

Crie um terceiro método iterativo que recebe uma string e retorna true se a mesma corresponde a um número inteiro.

Crie um quarto método iterativo que recebe uma string e retorna true se a mesma corresponde a um número real.

Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada Xi é um booleano indicando se a é entrada é: composta somente por vogais (X1); composta somente somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se Xi for verdadeiro, seu valor será SIM, caso contrário, NÃO.*/
#include <stdio.h>
#include <stdbool.h>

int Contador(char *palavra){
    int cont = 0;

    while(palavra[cont] != '\0'){
        cont++;
    }

    return cont;
}

//Verifica Vogais
bool Vogais(char *palavra, int tam){
    bool verifica = false;
    int cont = 0;
    for(int i = 0; i < tam; i++){
        if(palavra[i] == 'A' || palavra[i] == 'E' ||palavra[i] == 'I' ||palavra[i] == 'O' ||palavra[i] == 'U' || palavra[i] == 'a' || palavra[i] == 'e' ||palavra[i] == 'i' ||palavra[i] == 'o' ||palavra[i] == 'u' ){
            cont++;
        }
    }

    if(cont == tam){
        verifica = true;
    }

    return verifica;
}

//Verifica Consoantes
bool Consoantes(char *palavra, int tam){
    bool verifica = false;
    int cont = 0;
     for(int i = 0; i < tam; i++){
        if(palavra[i] != 'A' &&  palavra[i] != 'E' && palavra[i] != 'I' && palavra[i] != 'O' && palavra[i] != 'U' &&  palavra[i] != 'a' &&  palavra[i] != 'e' && palavra[i] != 'i' && palavra[i] != 'o' && palavra[i] != 'u'  ){
            cont++;
        }
    }

    if(cont == tam){
        verifica = true;
    }

    return verifica;
}

//Verifica Inteiros
bool VerificaInt(char *palavra, int tam){
    bool verifica = false;
    int cont = 0;

    for(int i = 0; i < tam; i++){
        if(palavra[i] >= '0' && palavra[i] <= '9' ){
            cont ++;
        }
    }

    if(cont == tam){
        verifica = true;
    }

    return verifica;
}

//Verifica Floats
bool VerificaFloat(char *palavra, int tam){
    bool verifica = false;
    int cont = 0;

    for(int i = 0; i < tam; i++){
        if(palavra[i] == '.'){
            cont++;
            verifica = true;
            i = tam;
        }
    }

    return verifica;
}

int main(){

    char palavra[1000];
    scanf("%[^\n]",palavra);

    while(!(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0')){

        int tam = Contador(palavra);

        if(Vogais(palavra, tam) == true ){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        if(Consoantes(palavra, tam) == true ){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        if(VerificaInt(palavra, tam) == true ){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        if(VerificaFloat(palavra,tam) == true ){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }

        scanf(" ar%[^\n]",palavra);
    }

    return 0;
}