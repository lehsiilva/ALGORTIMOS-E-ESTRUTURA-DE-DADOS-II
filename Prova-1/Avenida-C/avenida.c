#include <stdio.h>
#include <stdlib.h>

void distancia(int D, int distancias[]){
    
    int menor = abs(distancias[0] - D);
    

    for(int i = 1; i < 6; i++){
        if(menor > distancias[i]-D){
            menor = abs(distancias[i]-D);
        }
    }

    printf("%d", abs(menor));
}


int main(){
    int D;

    while(scanf("%d", &D) == 1){

        int distancias[6] = {0,400,800,1200,1600,2000};
        distancia(D, distancias);
     

    }

    return 0;
}