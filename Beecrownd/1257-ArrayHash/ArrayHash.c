#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int VerificaPos(char letra){
    char vet[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    int i = 0;
    int pos = 0;
        while(i < 26){
            if(letra == vet[i]){
                pos = i;
                i = 26;
            }
            i++;
        }
    
    return pos;
}

int main(){
    int n;
    scanf("%d", &n);

    while(n != 0){
        int l;
        scanf("%d", &l);

        int soma = 0;
        char vet[l][100];

        for(int i = 0; i < l; i++){
            scanf("%s", vet[i]);
        }

        for(int i = 0; i < l; i++){
            for(int j = 0; j < strlen(vet[i]); j++){
                char letra = vet[i][j];
                int elemento = VerificaPos(letra);
                int cont = elemento + i + j;
                soma += cont;
            }
        }

        printf("%d\n", soma);
        n--;
    }
}