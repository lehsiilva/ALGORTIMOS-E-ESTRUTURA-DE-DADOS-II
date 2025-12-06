#include <stdio.h>
#include <string.h>

#define MAX 100005

char pilha[MAX];

int main() {
    int T;
    scanf("%d", &T);
    getchar(); // consome o \n após o número

    for (int t = 0; t < T; t++) {
        char str[MAX];
        fgets(str, MAX, stdin);
        int len = strlen(str);

        // remove o '\n' do fgets
        if (str[len - 1] == '\n') {
            str[len - 1] = '\0';
            len--;
        }

        int topo = -1; // topo da pilha
        int bem_definida = 1;

        for (int i = 0; i < len && bem_definida; i++) {
            char c = str[i];
            if (c == '(' || c == '[' || c == '{') {
                pilha[++topo] = c; // empilha
            } else { // ')', ']', '}'
                if (topo < 0) {
                    bem_definida = 0; // pilha vazia, mas fecha parêntese
                } else {
                    char aberto = pilha[topo--]; // desempilha
                    if ((c == ')' && aberto != '(') ||
                        (c == ']' && aberto != '[') ||
                        (c == '}' && aberto != '{')) {
                        bem_definida = 0; // não corresponde
                    }
                }
            }
        }

        if (topo >= 0) bem_definida = 0; // ainda tem aberto sem fechar

        if (bem_definida)
            printf("S\n");
        else
            printf("N\n");
    }

    return 0;
}
