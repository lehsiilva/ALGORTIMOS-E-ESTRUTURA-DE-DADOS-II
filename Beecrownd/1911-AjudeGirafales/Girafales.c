#include <stdio.h>
#include <string.h>

#define MAX 1000

int main() {
    int N;

    while (scanf("%d", &N) != EOF && N > 0) {
        
        char nomes[N][MAX];
        char originais[N][MAX];

        for (int i = 0; i < N; i++) {
            scanf("%s %s", nomes[i], originais[i]);
        }

        int M;
        scanf("%d", &M);

        int falsos = 0;

        for (int j = 0; j < M; j++) {
            char nome[MAX], assin[MAX];
            scanf("%s %s", nome, assin);

            int pos = -1;
            for (int i = 0; i < N; i++) {
                if (strcmp(nomes[i], nome) == 0) {
                    pos = i;
                    i = N;
                }
            }

            int dif = 0;
            for (int k = 0; k < strlen(originais[pos]); k++) {
                if (originais[pos][k] != assin[k]) {
                    dif++;
                }
            }

            if (dif > 1) {
                falsos++;
            }
        }

        printf("%d\n", falsos);
    }

    return 0;
}
