#include <stdio.h>
#include <string.h>

int main() {
    int M, N;

    while (scanf("%d %d", &M, &N) != EOF) {

        char palavras[1000][20];
        int valores[1000];

        int i = 0;
        while (i < M) {
            scanf("%s %d", palavras[i], &valores[i]);
            i++;
        }

        getchar(); 

        int j = 0;
        while (j < N) {
            char linha[1000];
            int salario = 0;

            fgets(linha, 1000, stdin);

            while (strcmp(linha, ".\n") != 0 && strcmp(linha, ".") != 0) {
                char *palavra = strtok(linha, " \n");

                while (palavra != NULL) {
                    int x = 0;
                    while (x < M) {
                        if (strcmp(palavra, palavras[x]) == 0) {
                            salario += valores[x];
                        }
                        x++;
                    }
                    palavra = strtok(NULL, " \n");
                }

                fgets(linha, 1000, stdin);
            }

            printf("%d\n", salario);
            j++;
        }
    }

    return 0;
}
