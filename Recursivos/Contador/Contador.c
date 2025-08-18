#include <stdio.h>

void Imprimir(int n){
    if(n == 1){
        printf("%d ", n);
    }else{
        Imprimir(n - 1);
        printf("%d ", n);
    }
}

int main(){
    int n;
    scanf("%d", &n);
    Imprimir(n);

    return 0;
}