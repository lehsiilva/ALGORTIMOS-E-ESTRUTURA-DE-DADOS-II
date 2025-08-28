#include <stdio.h>
int Contador(char Str[]){

    int i = 0;

    while(Str[i] != '\0'){
        i++;
    }

    return i;
}

int main(){
    char str[50];
    char str2[50];

    scanf("%s %s", str,str2);

    int cont1 = Contador(str);
    int cont2 = Contador(str2);

    int tam = cont1 + cont2;

    char combinador[tam];

    int cont = 0;

    for(int i = 0; i < tam - 1 ; i+=2){
        if(i <= cont1){
        combinador[i] = str[cont];
        }if(i <= cont2){
        combinador[i+1] = str2[cont];
        }

        cont++;
    }

    printf("%s", combinador);

    return 0;
}