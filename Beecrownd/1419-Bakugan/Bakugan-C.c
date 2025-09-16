#include <stdio.h>
#include <stdbool.h>

void ler(int leitura[], int num){
    for(int i = 0; i < num; i++){
        scanf("%d", &leitura[i]);
    }
}

void contador(int contar[], int *pts, int num){
    for(int i = 0; i < num; i++){
        *pts += contar[i];
    }
}


void contadorTriplo(int vetLeti[], int vetMark[], int *ptsLeti, int *ptsMark, int num, int *extra){
    
    for(int i = 2; i < num ; i++){
        bool leti = vetLeti[i] == vetLeti[i-1] && (vetLeti[i] == vetLeti[i-2]);
        bool mark = vetMark[i] == vetMark[i-1] && (vetMark[i] == vetMark[i-2]);

        if(*extra == 0){
            if(leti && !mark ){
                *ptsLeti += 30;
                *extra = 1;
            }else if(mark && !leti){
                *ptsMark += 30;
                *extra = 1;
            }else if(mark && leti){
                i = num;
            }
        }
                 
    }
}

int main(){
    int num;
    scanf("%d", &num);
    
    while(num != 0){
        
        int vetMark[num];
        ler(vetMark, num);

        int vetLeti[num];
        ler(vetLeti, num);

        int ptsLeti = 0;
        int ptsMark = 0;
        int extra = 0;

        contador(vetMark,&ptsMark,num);
        contador(vetLeti,&ptsLeti,num);

        contadorTriplo(vetLeti,vetMark,&ptsLeti,&ptsMark,num,&extra);

        if(ptsLeti > ptsMark){
            printf("L\n");
        }else if(ptsMark > ptsLeti){
            printf("M\n");
        }else{
            printf("T\n");
        }

        scanf("%d", &num);
    }
}