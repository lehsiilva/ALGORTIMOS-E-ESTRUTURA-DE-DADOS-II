/*Crie um método recursivo que recebe uma string e retorna true se a mesma é composta somente por vogais.

Crie outro método recursivo que recebe uma string e retorna true se a mesma é composta somente por consoantes.

Crie um terceiro método recursivo que recebe uma string e retorna true se a mesma corresponde a um número inteiro.

Crie um quarto método recursivo que recebe uma string e retorna true se a mesma corresponde a um número real.

Na saída padrão, para cada linha de entrada, escreva outra de saída da seguinte forma X1 X2 X3 X4 onde cada Xi é um booleano indicando se a é entrada é: composta somente por vogais (X1); composta somente somente por consoantes (X2); um número inteiro (X3); um número real (X4). Se Xi for verdadeiro, seu valor será SIM, caso contrário, NÃO.*/

#include <stdio.h>
#include <stdbool.h>

void ler(char palavra[]){
    
    scanf(" %[^\n]",&palavra);
    
}

int contador(char palavra[]){
    int a = 0;
    while(palavra[a]!='\0'){
        a++;
    }

    return a;
}

bool Vogais(char palavra[], int a, int i){

    if(i == a){
        return true;

    }else if(palavra[i] == 'a' || palavra[i] == 'e' || palavra[i] == 'i' || palavra[i] == 'o' || palavra[i] == 'u' || palavra[i] == 'A' || palavra[i] == 'E' || palavra[i] == 'I' || palavra[i] == 'O' || palavra[i] == 'U'){

        return false;
        
    }else{
        return Vogais(palavra,a,i+1);
    }
}

bool Consoantes(char palavra[], int a, int i){

    if(i == a){
        return true;

    }else if(palavra[i] != 'a' || palavra[i] != 'e' || palavra[i] != 'i' || palavra[i] != 'o' || palavra[i] != 'u' || palavra[i] != 'A' || palavra[i] != 'E' || palavra[i] != 'I' || palavra[i] != 'O' || palavra[i] != 'U'){
        return false;
        
    }else{
        return Consoantes(palavra,a,i+1);
    }

}

bool Inteiros(char palavra[], int a, int i){

    if(i == a){
        return true;

    }else if(palavra[i] >= '0' && palavra[i] <= '9'){
        return false;
        
    }else{
        return Inteiros(palavra,a,i+1);
    }
}

bool Real(char palavra[], int a, int i){

    int resul;
    int cont = 0;

    if(i == a){
        return true;

    }else if(palavra[i] == '.'){
        cont++;
        
    }else{
        return Real(palavra,a,i+1);
    }

    if(cont == 1){
        resul = true;
    }else{
        resul = false;
    }


    return resul;
}

int main(){
    char palavra[100];
    ler(palavra);

    while(!(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0')){

    int a = contador(palavra);
    int i = 0;


        if(Vogais(palavra,a,i) == true){
            printf("SIM ");
        }else{
            printf("NAO ");
        }
        if(Consoantes(palavra,a,i) == true){
            printf("SIM ");
        }else{
            printf("NAO ");
        }
        if(Inteiros(palavra,a,i) == true){
            printf("SIM ");
        }else{
            printf("NAO ");
        }
        if(Real(palavra,a,i) == true){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        ler(palavra);
    }
    return 0;
}