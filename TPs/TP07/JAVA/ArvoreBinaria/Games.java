import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Nó da árvore binária
class No {
    Games game;
    No esq, dir;

    public No(Games g) {
        this.game = g;
        this.esq = null;
        this.dir = null;
    }
}

class ArvoreBinaria {

    No raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserir(Games g) {
        raiz = inserirRec(g, raiz);
    }

    private No inserirRec(Games g, No i) {
        if (i == null) {
            Games.movimentacoes++;
            return new No(g);
        }

        Games.comparacoes++;
        int cmp = g.getName().compareTo(i.game.getName());

        if (cmp < 0) {
            i.esq = inserirRec(g, i.esq);
        } 
        else if (cmp > 0) {
            i.dir = inserirRec(g, i.dir);
        }

        return i;
    }

    public boolean pesquisar(String nome) {
        System.out.print(nome + ": =>raiz");
        return pesquisarRec(nome.trim(), raiz); //trim para remover espaços em branco
    }

    private boolean pesquisarRec(String nome, No i) {
        if (i == null) {
            Games.comparacoes++;
            System.out.println(" NAO");
            return false;
        }

        Games.comparacoes++;
        int cmp = nome.compareTo(i.game.getName());

        if (cmp == 0) {
            System.out.println(" SIM");
            return true;
        } 
        else if (cmp < 0) {
            System.out.print(" esq");
            return pesquisarRec(nome, i.esq);
        } 
        else {
            System.out.print(" dir");
            return pesquisarRec(nome, i.dir);
        }
    }
}

public class Games {

    private int id;
    private String name;

    public static String log = "874205_arvoreBinaria.txt";
    public static int matricula = 874205;
    public static long comparacoes = 0;
    public static long movimentacoes = 0;
    public static double tempoEmSegundos = 0.0;

    public Games() {
        this.id = 0;
        this.name = " ";
    }

    public void setId(int arqId) {
        this.id = arqId;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty())
            this.name = name.trim();
        else
            this.name = " ";
    }

    public int getId() { return id; }
    public String getName() { return name; }

    private static String arquivo = "/tmp/games.csv"; // Caminho do arquivo CSV
    private static List<String> csv = new ArrayList<>();

    public static List<String> getcsv() { return csv; } // Retorna a lista csv

    // Carregar o arquivo CSV
    public static void carregarCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                csv.add(line);
            }

        } catch (IOException e) {
            System.err.println("Erro ao carregar CSV: " + e.getMessage());
        }
    }

    // Preencher objeto Games a partir de uma linha do CSV
    public static Games preencher(String line) {
        String[] f = ler(line);
        Games g = new Games();

        g.setId(Integer.parseInt(f[0]));
        g.setName(f[1]);

        return g;
    }

    // Ler linha do CSV considerando aspas
    public static String[] ler(String line) {
        List<String> result = new ArrayList<>();
        boolean aspas = false;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                aspas = !aspas;
            } else if (c == ',' && !aspas) {
                result.add(str.toString().trim());
                str = new StringBuilder();
            } else {
                str.append(c);
            }
        }

        result.add(str.toString().trim());
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        carregarCSV(arquivo);
        List<String> linhas = Games.getcsv();

        //preencher todos os jogos
        Games[] todos = new Games[linhas.size()];
        for (int i = 0; i < linhas.size(); i++) {
            todos[i] = preencher(linhas.get(i));
        }

        ArvoreBinaria arvore = new ArvoreBinaria();

        String entrada = scanner.nextLine();

        while (!entrada.equals("FIM")) {

            int idBuscado = Integer.parseInt(entrada); //Int.parseInt para converter string para inteiro

            for (Games g : todos) {
                if (g.getId() == idBuscado) {
                    arvore.inserir(g);
                }
            }

            entrada = scanner.nextLine();
        }

        comparacoes = 0;
        movimentacoes = 0;

        if (scanner.hasNextLine()) entrada = scanner.nextLine();
        else entrada = "FIM";

        while (!entrada.equals("FIM")) {

            arvore.pesquisar(entrada);

            if (scanner.hasNextLine()) entrada = scanner.nextLine();
            else entrada = "FIM";
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Games.log))) {
            bw.write(
                String.format("%d\t%d\t%d\t%.2f\n",
                matricula, comparacoes, movimentacoes, tempoEmSegundos)
            );
        } catch (IOException e) {
            System.err.println("Erro ao escrever log: " + e.getMessage());
        }

        scanner.close();
    }
}
