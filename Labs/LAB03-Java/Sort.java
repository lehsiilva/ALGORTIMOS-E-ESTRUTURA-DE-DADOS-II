import java.util.*;

class Numero {
    int valor;
    int modulo;
    boolean impar;

    Numero(int valor, int M) {
        this.valor = valor;
        // cálculo do módulo seguindo a regra do C
        this.modulo = valor % M;
        this.impar = (valor % 2 != 0);
    }
}

public class Sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            if (N == 0 && M == 0) {
                System.out.println("0 0");
                break;
            }

            Numero[] numeros = new Numero[N];
            for (int i = 0; i < N; i++) {
                int valor = sc.nextInt();
                numeros[i] = new Numero(valor, M);
            }

            // Selection Sort como nas suas fotos
            for (int i = 0; i < N - 1; i++) {
                int posMin = i;
                for (int j = i + 1; j < N; j++) {
                    if (trocarNumero(numeros[posMin], numeros[j])) {
                        posMin = j;
                    }
                }
                Numero temp = numeros[i];
                numeros[i] = numeros[posMin];
                numeros[posMin] = temp;
            }

            // saída do cabeçalho
            System.out.println(N + " " + M);

            // saída dos números ordenados
            for (int i = 0; i < numeros.length; i++) {
                System.out.println(numeros[i].valor);
            }
        }

        sc.close();
    }

    static boolean trocarNumero(Numero a, Numero b) {
        // primeiro critério: módulo
        if (a.modulo != b.modulo) return a.modulo > b.modulo;

        // ímpares antes dos pares
        if (a.impar && !b.impar) return false;
        if (!a.impar && b.impar) return true;

        // empate entre ímpares: maior vem primeiro
        if (a.impar && b.impar) return a.valor < b.valor;

        // empate entre pares: menor vem primeiro
        return a.valor > b.valor;
    }
}

