#include <stdio.h>
#include <stdbool.h>

bool brasileiro(char placa[]){
    bool resp = false;
    if((placa[0] >= 65 && placa[0] <= 90) && (placa[1] >= 65 && placa[1] <= 90) && (placa[2] >= 65 && placa[2] <= 90) && (placa[3] == '-') && (placa[4] >= '0' && placa[4] <= '9') && (placa[5] >= '0' && placa[5] <= '9') && (placa[6] >= '0' && placa[6] <= '9') && (placa[7] >= '0' && placa[7] <= '9' )){
        resp = true;
    }

    return resp;
}

bool mercorsul(char placa[]){
    bool resp = false;
    if(placa[0] >= 65 && placa[0] <= 90 && placa[1] >= 65 && placa[1] <= 90 && placa[2] >= 65 && placa[2] <= 90 && placa[3] >= '0' && placa[3] <= '9' && placa[4] >= 65 && placa[4] <= 90 && placa[5] >= '0' && placa[5] <= '9' && placa[6] >= '0' && placa[6] <= '9'){
        resp = true;
    }

    return resp;
}


int main(){
    char placa[10];
    
    while(scanf("%s", placa) == 1){

        if(brasileiro(placa)){
            printf("1");
        }else if(mercorsul(placa)){
            printf("2");
        }else{
            printf("0");
        }
    }
}