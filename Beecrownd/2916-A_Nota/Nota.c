#include <stdio.h>

#define MAX_N 1000005
#define MOD 1000000007

int notas[MAX_N];

void heapify(int n, int i) {
    int maior = i;
    int left = 2 * i + 1;
    int right = 2 * i + 2;

    if (left < n && notas[left] > notas[maior]) {
        maior = left;
    }
    if (right < n && notas[right] > notas[maior]) {
        maior = right;
    }

    if (maior != i) {
        int temp = notas[i];
        notas[i] = notas[maior];
        notas[maior] = temp;

        heapify(n, maior);
    }
}

void heapSort(int n) {
    // construir heap
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(n, i);
    }

    // extrair elementos
    for (int i = n - 1; i >= 0; i--) {
        int temp = notas[0];
        notas[0] = notas[i];
        notas[i] = temp;

        heapify(i, 0);
    }
}

int main() {
    int n, k;

    while (scanf("%d %d", &n, &k) != EOF) {

        for (int i = 0; i < n; i++) {
            scanf("%d", &notas[i]);
        }

        heapSort(n);

        // como heapsort ficou em ordem crescente → somar de trás para frente
        int soma = 0;
        int start = n - k;

        for (int i = start; i < n; i++) {
            soma = (soma + notas[i]) % MOD;
        }

        printf("%d\n", soma);
    }

    return 0;
}
