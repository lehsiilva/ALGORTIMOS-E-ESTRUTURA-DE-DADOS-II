#include <stdio.h>
#include <stdbool.h>

void ler(char palavra[]){
    
    scanf(" %[^\n]", palavra);
    
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

    }else if(palavra[i] != 'a'&& palavra[i] != 'e'&& palavra[i] != 'i'&& palavra[i] != 'o'&& palavra[i] != 'u'&& palavra[i] != 'A'&& palavra[i] != 'E'&& palavra[i] != 'I'&& palavra[i] != 'O'&& palavra[i] != 'U'){

        return false;
        
    }else{
        return Vogais(palavra,a,i+1);
    }
}

bool Consoantes(char palavra[], int a, int i){

    if(i == a){
        return true;

    }else if(palavra[i] == 'a' || palavra[i] == 'e' || palavra[i] == 'i' || palavra[i] == 'o' || palavra[i] == 'u' || palavra[i] == 'A' || palavra[i] == 'E' || palavra[i] == 'I' || palavra[i] == 'O' || palavra[i] == 'U' || (palavra[i] >= '0' && palavra[i] <= '9')){
        return false;
        
    }else{
        return Consoantes(palavra,a,i+1);
    }

}

bool Inteiros(char palavra[], int a, int i){

    if(i == a){
        return true;

    }else if(palavra[i] >= '0' && palavra[i] <= '9'){
        return Inteiros(palavra,a,i+1);
        
    }else{
        return false;
    }
}

bool Real(char palavra[], int a, int i,int ponto){

    if(i == a ){
        return ponto == 1;

    }else if(palavra[i] >= '0' && palavra[i] <= '9'){
        return Real(palavra,a,i+1,ponto);
        
    }else if(palavra[i] == '.' && ponto == 0){
        return Real(palavra,a,i+1,1);
    }else{
        return false;
    }

}

int main(){
    char palavra[1000];
    ler(palavra);

    while(!(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && palavra[3] == '\0')){

    int a = contador(palavra);
    int i = 0;
    int ponto = 0;

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
        if(Real(palavra,a,i,ponto) == true){
            printf("SIM ");
            printf("\n");
        }else{
            printf("NAO ");
            printf("\n");
        }

        ler(palavra);
    }
    return 0;
}