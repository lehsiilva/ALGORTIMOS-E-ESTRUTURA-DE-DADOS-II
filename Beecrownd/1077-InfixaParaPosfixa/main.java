import java.util.Scanner;

public class main {

    static int prioridade(char op) {
        if (op == '^') return 3;
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0;
    }

    static boolean ehOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    static boolean ehLetraOuNumero(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < n; t++) {
            String expr = sc.nextLine();

            char[] pilha = new char[expr.length()];
            int topo = -1; // pilha vazia

            for (int i = 0; i < expr.length(); i++) {
                char c = expr.charAt(i);

                if (ehLetraOuNumero(c)) {
                    System.out.print(c);
                } else if (c == '(') {
                    topo++;
                    pilha[topo] = c;
                } else if (c == ')') {
                    while (topo >= 0 && pilha[topo] != '(') {
                        System.out.print(pilha[topo]);
                        topo--;
                    }
                    if (topo >= 0 && pilha[topo] == '(') {
                        topo--; // remove '('
                    }
                } else if (ehOperador(c)) {
                    boolean empilhar = true;
                    while (topo >= 0 && ehOperador(pilha[topo]) && empilhar) {
                        char topoOp = pilha[topo];
                        if ((c != '^' && prioridade(topoOp) >= prioridade(c)) ||
                            (c == '^' && prioridade(topoOp) > prioridade(c))) {
                            System.out.print(pilha[topo]);
                            topo--;
                        } else {
                            empilhar = false;
                        }
                    }
                    topo++;
                    pilha[topo] = c;
                }
            }

            while (topo >= 0) {
                System.out.print(pilha[topo]);
                topo--;
            }

            System.out.println(); // pula linha ao final da expressão
        }

        sc.close();
    }
}
