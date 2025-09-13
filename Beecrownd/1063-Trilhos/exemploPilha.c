#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

void lerVagoes(char vagoes[], int n) {
    for (int i = 0; i < n; i++) {
        scanf(" %c", &vagoes[i]);
    }
}

bool push(char vagoes[], char pilha[], int n, int *topo, int *i) { //INSERIR
    if (*topo < n) {
        pilha[(*topo)++] = vagoes[(*i)++];
        return true;
    }
    return false;
}

char pop(char pilha[], int *topo) { //REMOVER
    if (*topo > 0) {
        return pilha[--(*topo)];
    }
    return '\0';
}

void compara(char vetAtual[], char pilha[], int *topo, int *a) {
    if (*topo > 0) {
        vetAtual[(*a)++] = pop(pilha, topo);
    }
}

bool verifica(char vagoesSaida[], char vetAtual[], int n) {
    for (int k = 0; k < n; k++) {
        if (vagoesSaida[k] != vetAtual[k]) {
            return false;
        }
    }
    return true;
}

int main() {
    int n;
    scanf("%d", &n);

    while (n != 0) {
        int topo = 0, i = 0, a = 0;

        char *pilha = malloc(n * sizeof(char));
        char *vetAtual = malloc(n * sizeof(char));
        char *vagoes = malloc(n * sizeof(char));
        char *vagoesSaida = malloc(n * sizeof(char));

        lerVagoes(vagoes, n);
        lerVagoes(vagoesSaida, n);

        char opc;
        scanf(" %c", &opc);

        do {
            switch (opc) {
                case 'I': {
                    bool resul = push(vagoes, pilha, n, &topo, &i);
                    if (resul) {
                        printf("I");
                    }
                } break;

                case 'R': {
                    compara(vetAtual, pilha, &topo, &a);
                    printf("R");
                } break;
            }
            scanf(" %c", &opc);
        } while (opc != 'F');

        if (!verifica(vagoesSaida, vetAtual, n)) {
            printf("Impossible");
        }

        printf("\n");

        free(pilha);
        free(vetAtual);
        free(vagoes);
        free(vagoesSaida);

        scanf("%d", &n);
    }

    return 0;
}
