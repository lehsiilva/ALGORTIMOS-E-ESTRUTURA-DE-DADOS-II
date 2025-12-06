#include <stdio.h>

int main() {
    int N, quantia;
    char tipo;
    int total = 0, coelhos = 0, ratos = 0, sapos = 0;

    scanf("%d", &N);

    for(int i = 0; i < N; i++) {
        scanf("%d %c", &quantia, &tipo);

        total += quantia;

        if(tipo == 'C') {
            coelhos += quantia;
        } else if(tipo == 'R') {
            ratos += quantia;
        } else if(tipo == 'S') {
            sapos += quantia;
        }
    }

    printf("Total: %d cobaias\n", total);
    printf("Total de coelhos: %d\n", coelhos);
    printf("Total de ratos: %d\n", ratos);
    printf("Total de sapos: %d\n", sapos);

    printf("Percentual de coelhos: %.2f %%\n", (coelhos * 100.0) / total);
    printf("Percentual de ratos: %.2f %%\n", (ratos * 100.0) / total);
    printf("Percentual de sapos: %.2f %%\n", (sapos * 100.0) / total);

    return 0;
}
