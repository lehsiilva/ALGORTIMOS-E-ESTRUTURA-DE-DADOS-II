#include <stdio.h>
#include <stdlib.h>

int mdc(int a, int b){
    a = abs(a);
    b = abs(b);
    if(b == 0){
        return a;
    }else{
        return mdc(b, a%b);
    }
}
void soma(int N1, int D1, int N2, int D2){
    int num = (N1*D2) + (N2*D1);
    int den = D1*D2;
    int mdcCima = num/mdc(num,den);
    int mdcBaixo = den/mdc(num,den);
    
    if(mdcCima != 0 && mdcBaixo != 0){
        printf("%d/%d = %d/%d \n",num,den,mdcCima,mdcBaixo);
    }else{
        printf("%d/%d = %d/%d \n",num,den,num,den);
    }
    
}
void subtrai(int N1, int D1, int N2, int D2){
    int num = (N1*D2) - (N2*D1);
    int den = D1*D2;
    int mdcCima = num/mdc(num,den);
    int mdcBaixo = den/mdc(num,den);
    
    if(mdcCima != 0 && mdcBaixo != 0){
        printf("%d/%d = %d/%d \n",num,den,mdcCima,mdcBaixo);
    }else{
        printf("%d/%d = %d/%d \n",num,den,num,den);
    }
}
void multiplica(int N1, int D1, int N2, int D2){
    int num = (N1*N2);
    int den = D1*D2;
    int mdcCima = num/mdc(num,den);
    int mdcBaixo = den/mdc(num,den);
    
    if(mdcCima != 0 && mdcBaixo != 0){
        printf("%d/%d = %d/%d \n",num,den,mdcCima,mdcBaixo);
    }else{
        printf("%d/%d = %d/%d \n",num,den,num,den);
    }
}
void divide(int N1, int D1, int N2, int D2){
    int num = (N1*D2);
    int den = N2*D1;
    int mdcCima = num/mdc(num,den);
    int mdcBaixo = den/mdc(num,den);
    
    if(mdcCima != 0 && mdcBaixo != 0){
        printf("%d/%d = %d/%d \n",num,den,mdcCima,mdcBaixo);
    }else{
        printf("%d/%d = %d/%d \n",num,den,num,den);
    }
}
 
int main() {
    int n;
    scanf("%d", &n);
    while(n!=0){
        int N1;
        scanf("%d", &N1);

        int D1;
        scanf("%d", &D1);

        char op;
        scanf(" %c", &op);
        
        int N2;
        scanf("%d", &N2);

        int D2;
        scanf("%d", &D2);

        switch(op){
            case '+':
            soma(N1,D1,N2,D2);
            n--;
            break;
            
            case '-':
            subtrai(N1,D1,N2,D2);
            n--;
            break;
            
            case '*':
            multiplica(N1,D1,N2,D2);
            n--;
            break;
            
            case '/':
            divide(N1,D1,N2,D2);
            n--;
            break;
        }
    }
 
    return 0;
}