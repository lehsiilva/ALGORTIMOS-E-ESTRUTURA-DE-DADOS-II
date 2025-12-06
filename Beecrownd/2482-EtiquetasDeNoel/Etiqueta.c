#include <stdio.h>
#include <string.h>

int main() {
    int N, M;
    scanf("%d", &N);
    getchar(); 

    char idioma[100][30];
    char traducao[100][100];

    for(int i = 0; i < N; i++) {
        fgets(idioma[i], 30, stdin);
        idioma[i][strcspn(idioma[i], "\n")] = 0; 

        fgets(traducao[i], 100, stdin);
        traducao[i][strcspn(traducao[i], "\n")] = 0;
    }

    scanf("%d", &M);
    getchar();

    char nome[100];
    char idiomaCrianca[30];

    for(int i = 0; i < M; i++) {

        fgets(nome, 100, stdin);
        nome[strcspn(nome, "\n")] = 0;

        fgets(idiomaCrianca, 30, stdin);
        idiomaCrianca[strcspn(idiomaCrianca, "\n")] = 0;

        printf("%s\n", nome);

        for(int j = 0; j < N; j++) {
            if(strcmp(idiomaCrianca, idioma[j]) == 0) {
                printf("%s\n", traducao[j]);
                j = N;
            }
        }

        printf("\n"); 
    }

    return 0;
}
