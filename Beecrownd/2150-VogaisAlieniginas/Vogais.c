#include <stdio.h>
#define MAX 100

int ler(char cont[]){
    int i = 0;
    int c;
    while (i < MAX-1 && (c = getchar()) != EOF && c != '\n') {
        cont[i++] = (char)c;
    }
    cont[i] = '\0'; // termina a string
    return i;
}

int contador(char cont[]){
    int i = 0;
    while(cont[i] != '\0'){
        i++;
    }

    return i;
}
 
int main() {
    char chave[MAX];
    char frase[MAX];

    int cont;
 
    while(ler(chave) != 0 && ler(frase) != 0){

        cont = 0;

        
        int a = contador(chave);
        int b = contador(frase);
        
    
        for(int i = 0; i < b; i++){
            for(int j = 0; j < a; j++){
                if(frase[i] == chave[j]){
                    cont++;
                }
            }
        }
        
        printf("%d\n", cont);
    }
 
    return 0;
}