#include <stdio.h>
 
int main() {
 
    int n;
    scanf("%d", &n);
    while(n > 0){
        int m;
        scanf("%d", &m);
        
        int notas[m];
        int notasOrdenadas[m];
        int cont = 0;
        
        for(int i = 0; i < m; i++){
            scanf("%d", &notas[i]);
            notasOrdenadas[i] = notas[i];
            
        }

        for(int i = 0; i < m - 1; i++){
            for(int j = 0; j < m-i-1; j++){
                if(notasOrdenadas[j] < notasOrdenadas[j+1]){
                    int tmp = notasOrdenadas[j];
                    notasOrdenadas[j] = notasOrdenadas[j+1];
                    notasOrdenadas[j+1] = tmp;
                }
            }
        }
        
        for(int i = 0; i < m ; i++){
            if(notas[i] == notasOrdenadas[i] ){
                cont++;
            }
        }
        
        printf("%d\n", cont);
        n--;
    }
 
    return 0;
}