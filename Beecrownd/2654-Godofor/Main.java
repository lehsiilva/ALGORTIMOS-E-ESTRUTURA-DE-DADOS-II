import java.util.Scanner;

class Candidato {
    String nome;
    int poder;
    int matou;
    int morreu;

    public Candidato(String nome, int poder, int matou, int morreu) {
        this.nome = nome;
        this.poder = poder;
        this.matou = matou;
        this.morreu = morreu;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Candidato escolhido = new Candidato("", -1, -1, 101); // inicializa valores

        for (int i = 0; i < N; i++) {
            String nome = sc.next();
            int poder = sc.nextInt();
            int matou = sc.nextInt();
            int morreu = sc.nextInt();

            Candidato candidato = new Candidato(nome, poder, matou, morreu);

            // Critério de escolha
            if (candidato.poder > escolhido.poder ||
                (candidato.poder == escolhido.poder && candidato.matou > escolhido.matou) ||
                (candidato.poder == escolhido.poder && candidato.matou == escolhido.matou && candidato.morreu < escolhido.morreu) ||
                (candidato.poder == escolhido.poder && candidato.matou == escolhido.matou && candidato.morreu == escolhido.morreu && candidato.nome.compareTo(escolhido.nome) < 0)) {
                escolhido = candidato;
            }
        }

        System.out.println(escolhido.nome);
        sc.close();
    }
}
