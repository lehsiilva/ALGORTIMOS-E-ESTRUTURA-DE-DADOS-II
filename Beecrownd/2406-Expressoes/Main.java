import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine(); // consome a quebra de linha após o número

        for (int t = 0; t < T; t++) {
            String str = sc.nextLine();
            char[] pilha = new char[str.length()];
            int topo = -1;
            boolean bemDefinida = true;

            for (int i = 0; i < str.length() && bemDefinida; i++) {
                char c = str.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    pilha[++topo] = c; // empilha
                } else { // ')', ']', '}'
                    if (topo < 0) {
                        bemDefinida = false; // pilha vazia, mas fecha parêntese
                    } else {
                        char aberto = pilha[topo--]; // desempilha
                        if ((c == ')' && aberto != '(') ||
                            (c == ']' && aberto != '[') ||
                            (c == '}' && aberto != '{')) {
                            bemDefinida = false; // não corresponde
                        }
                    }
                }
            }

            if (topo >= 0) bemDefinida = false; // ainda tem aberto sem fechar

            System.out.println(bemDefinida ? "S" : "N");
        }

        sc.close();
    }
}
