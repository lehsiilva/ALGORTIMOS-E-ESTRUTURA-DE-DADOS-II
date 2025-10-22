#include <stdio.h>
#include <stdlib.h>

int Soma(int num){
    
    if(num/10 == 0){//compara se o resultado é 0

        return num;

    }else{

        int somar = num%10; //guarda os restos das divisões

        return somar + Soma(num/10); //recursão para soma
    }
}

int main(){

    int num;
    char digito[20]; //armazena um numero ou uma string

    scanf("%s", digito);

    while(!(digito[0] == 'F' && digito[1] == 'I' && digito[2] == 'M' && digito[3] == '\0')){

        num = atoi(digito); //atoi converte string para int

        int resul = Soma(num);

        printf("%d\n", resul);

        scanf("%s", digito);
    }


    return 0;
}