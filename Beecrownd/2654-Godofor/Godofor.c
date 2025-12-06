#include <stdio.h>
#include <string.h>

typedef struct {
    char nome[101];
    int poder;
    int matou;
    int morreu;
} Candidato;

int main() {
    int N;
    scanf("%d", &N);

    Candidato candidato, escolhido;
    escolhido.poder = -1; 
    escolhido.matou = -1;
    escolhido.morreu = 101; 
    escolhido.nome[0] = '\0';

    for (int i = 0; i < N; i++) {
        scanf("%s %d %d %d", candidato.nome, &candidato.poder, &candidato.matou, &candidato.morreu);

        // Critério de escolha
        if (candidato.poder > escolhido.poder ||
            (candidato.poder == escolhido.poder && candidato.matou > escolhido.matou) ||
            (candidato.poder == escolhido.poder && candidato.matou == escolhido.matou && candidato.morreu < escolhido.morreu) ||
            (candidato.poder == escolhido.poder && candidato.matou == escolhido.matou && candidato.morreu == escolhido.morreu && strcmp(candidato.nome, escolhido.nome) < 0)) {
            escolhido = candidato;
        }
    }

    printf("%s\n", escolhido.nome);

    return 0;
}
