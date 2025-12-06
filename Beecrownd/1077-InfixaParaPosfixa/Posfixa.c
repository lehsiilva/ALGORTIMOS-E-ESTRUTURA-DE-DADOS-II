#include <stdio.h>
#include <string.h>

int prioridade(char op) {
    if (op == '^') return 3;
    if (op == '*' || op == '/') return 2;
    if (op == '+' || op == '-') return 1;
    return 0;
}

int ehOperador(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
}

int ehLetraOuNumero(char c) {
    return ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
}

int main() {
    int n;
    scanf("%d\n", &n);

    for (int t = 0; t < n; t++) {
        char expr[301];
        fgets(expr, sizeof(expr), stdin);

        int len = strlen(expr);
        if (expr[len-1] == '\n') expr[len-1] = '\0';

        char pilha[301];
        int topo = -1;

        char resultado[301];
        int resPos = 0;

        for (int i = 0; expr[i] != '\0'; i++) {
            char c = expr[i];

            if (ehLetraOuNumero(c)) {
                resultado[resPos++] = c;
            } else if (c == '(') {
                topo++;
                pilha[topo] = c;
            } else if (c == ')') {
                while (topo >= 0 && pilha[topo] != '(') {
                    resultado[resPos++] = pilha[topo];
                    topo--;
                }
                if (topo >= 0 && pilha[topo] == '(') topo--;
            } else if (ehOperador(c)) {
                int empilhar = 1;
                while (topo >= 0 && ehOperador(pilha[topo]) && empilhar) {
                    char topoOp = pilha[topo];
                    if ((c != '^' && prioridade(topoOp) >= prioridade(c)) ||
                        (c == '^' && prioridade(topoOp) > prioridade(c))) {
                        resultado[resPos++] = pilha[topo];
                        topo--;
                    } else {
                        empilhar = 0;
                    }
                }
                topo++;
                pilha[topo] = c;
            }
        }

        while (topo >= 0) {
            resultado[resPos++] = pilha[topo];
            topo--;
        }

        resultado[resPos] = '\0';
        printf("%s\n", resultado);
    }

    return 0;
}
