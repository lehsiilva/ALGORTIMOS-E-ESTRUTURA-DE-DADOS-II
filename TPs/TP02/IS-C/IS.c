#include <stdio.h>
#include <stdbool.h>

//Conta tamanho da string
int Contador(char *palavra){
    int cont = 0;
    while(palavra[cont] != '\0'){
        cont++;
    }
    return cont;
}

//Verifica Vogais
bool Vogais(char *palavra, int tam){
    int cont = 0;
    for(int i = 0; i < tam; i++){
        if(palavra[i] == 'A' || palavra[i] == 'E' ||palavra[i] == 'I' ||palavra[i] == 'O' ||palavra[i] == 'U' ||
           palavra[i] == 'a' || palavra[i] == 'e' ||palavra[i] == 'i' ||palavra[i] == 'o' ||palavra[i] == 'u'){
            cont++;
        }
    }
    return (cont == tam);
}

//Verifica Consoantes
bool Consoantes(char *palavra, int tam){
    int cont = 0;
    for(int i = 0; i < tam; i++){
        if(!(palavra[i] == 'A' || palavra[i] == 'E' || palavra[i] == 'I' || palavra[i] == 'O' || palavra[i] == 'U' ||
             palavra[i] == 'a' || palavra[i] == 'e' || palavra[i] == 'i' || palavra[i] == 'o' || palavra[i] == 'u')){
            cont++;
        }
    }
    return (cont == tam);
}

//Verifica Inteiros
bool VerificaInt(char *palavra, int tam){
    int cont = 0;
    for(int i = 0; i < tam; i++){
        if(palavra[i] >= '0' && palavra[i] <= '9'){
            cont++;
        }
    }
    return (cont == tam);
}

//Verifica Floats
bool VerificaFloat(char *palavra, int tam){
    int contPonto = 0;
    int contDig = 0;
    for(int i = 0; i < tam; i++){
        if(palavra[i] == '.'){ //verifica se há pontos
            contPonto++;
        }else if(palavra[i] >= '0' && palavra[i] <= '9'){
            contDig++;
        }else{
            return false; // se tiver caractere inválido
        }
    }
    return (contPonto == 1 && contDig > 0);
}

int main(){
    char palavra[1000];

    while (true){
        scanf(" %[^\n]", palavra); 

        if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0'){
            break;
        }

        int tam = Contador(palavra);

        if(Vogais(palavra, tam)){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        if(Consoantes(palavra, tam)){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        if(VerificaInt(palavra, tam)){
            printf("SIM ");
        }else{
            printf("NAO ");
        }

        if(VerificaFloat(palavra, tam)){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }
    }

    return 0;
}
