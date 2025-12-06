#include <stdio.h>

#define MAXP 10001

int main() {
    int P, S, teste = 1;

    while (scanf("%d %d", &P, &S) == 2) {
        if (P == 0 && S == 0) return 0;

        int cobertura[MAXP];
        
        for (int i = 0; i <= P; i++) cobertura[i] = 0;

        for (int i = 0; i < S; i++) {
            int u, v;
            scanf("%d %d", &u, &v);
            for (int j = u; j < v; j++) {
                cobertura[j] = 1;
            }
        }

        printf("Teste %d\n", teste++);
        int i = 0;
        while (i <= P) {
            
            if (cobertura[i] == 1) {
                int inicio = i;
                int fim = i;
                while (fim <= P && cobertura[fim] == 1) {
                    fim++;
                }
                printf("%d %d\n", inicio, fim);
                i = fim; 
            } else {
                i++;
            }
        }

        printf("\n");
    }

    return 0;
}
