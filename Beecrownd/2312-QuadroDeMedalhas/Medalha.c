#include <stdio.h>
#include <string.h>

#define MAX 100

int main() {
    int n;
    char pais[MAX][31]; // nomes dos países
    int ouro[MAX], prata[MAX], bronze[MAX];

    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        scanf("%s %d %d %d", pais[i], &ouro[i], &prata[i], &bronze[i]);
    }

    // Ordenação manual
    for (int i = 0; i < n - 1; i++) {
        int pos = i;
        for (int j = i + 1; j < n; j++) {
            if (ouro[j] > ouro[pos] ||
                (ouro[j] == ouro[pos] && prata[j] > prata[pos]) ||
                (ouro[j] == ouro[pos] && prata[j] == prata[pos] && bronze[j] > bronze[pos]) ||
                (ouro[j] == ouro[pos] && prata[j] == prata[pos] && bronze[j] == bronze[pos] &&
                 strcmp(pais[j], pais[pos]) < 0)) {
                pos = j;
            }
        }

        // Troca se necessário
        if (pos != i) {
            char tempPais[31];
            int temp;
            
            strcpy(tempPais, pais[i]);
            strcpy(pais[i], pais[pos]);
            strcpy(pais[pos], tempPais);
            
            temp = ouro[i]; ouro[i] = ouro[pos]; ouro[pos] = temp;
            temp = prata[i]; prata[i] = prata[pos]; prata[pos] = temp;
            temp = bronze[i]; bronze[i] = bronze[pos]; bronze[pos] = temp;
        }
    }

    // Imprime o resultado
    for (int i = 0; i < n; i++) {
        printf("%s %d %d %d\n", pais[i], ouro[i], prata[i], bronze[i]);
    }

    return 0;
}
