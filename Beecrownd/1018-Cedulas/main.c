#include <stdio.h>
 
int main() {
 
   int num;
   scanf("%d", &num);
   
   int notas[]= {100,50,20,10,5,2,1};
   
   printf("%d\n", num);
   
   for(int i =0; i < 7; i++){
       int qntd = num / notas[i];
       printf("%d nota(s) de R$ %d,00\n", qntd,notas[i]);
       num%=notas[i];
   }
 
    return 0;
}