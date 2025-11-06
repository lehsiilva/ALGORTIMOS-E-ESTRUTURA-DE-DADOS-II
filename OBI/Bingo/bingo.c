#include <stdio.h>

int main() {
    int N, K, U;
    
    scanf("%d %d %d", &N, &K, &U);
    
    int cartelas[N][K];
    int contCartela[N]; // quantos números já saíram em cada cartela
    int fimCartela[N];  // índice do sorteio em que cada cartela completou
    
    for(int i = 0; i < N; i++) {
        contCartela[i] = 0;
        fimCartela[i] = -1;
        for(int j = 0; j < K; j++) {
            scanf("%d", &cartelas[i][j]);
        }
    }
    
    int sorteio[U];
    for(int i = 0; i < U; i++) {
        scanf("%d", &sorteio[i]);
    }
    
    // Marca números sorteados nas cartelas
    for(int s = 0; s < U; s++) {
        for(int c = 0; c < N; c++) {
            if(fimCartela[c] == -1) { // só continua se cartela ainda não terminou
                for(int j = 0; j < K; j++) {
                    if(cartelas[c][j] == sorteio[s]) {
                        contCartela[c]++;
                    }
                }
                if(contCartela[c] == K) {
                    fimCartela[c] = s;
                }
            }
        }
    }
    
    // Descobre o menor índice de término
    int menor = U+1;
    for(int i = 0; i < N; i++) {
        if(fimCartela[i] != -1 && fimCartela[i] < menor) {
            menor = fimCartela[i];
        }
    }
    
    // Imprime as cartelas vencedoras
    for(int i = 0; i < N; i++) {
        if(fimCartela[i] == menor) {
            printf("%d ", i+1);
        }
    }
    printf("\n");
    
    return 0;
}
