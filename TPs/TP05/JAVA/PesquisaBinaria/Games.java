package PesquisaBinaria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Games {
    private int id;
    private String name;

    public static String log = "874205_binaria.txt";
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
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            this.name = " ";
        }
    }

    public int getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    private static String arquivo = "/tmp/games.csv";
    private static List<String> csv = new ArrayList<>();


    public static List<String> getcsv(){
        return csv;
    }

    public static void carregarCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                csv.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    public static Games preencher(String line) {
        String[] f = ler(line);
        Games g = new Games();
        
        g.setId(Integer.parseInt(f[0])); 
        g.setName(f[1]);

        return g;
    }

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

    public static void quicksort(List<Games> lista, int esq, int dir) {
        int i = esq, j = dir;
        
        String pivo = lista.get((dir + esq) / 2).getName();
        
        while (i <= j) {
            
            while (lista.get(i).getName().compareTo(pivo) < 0) {
                comparacoes++;
                i++;
            }
            comparacoes++;
            
            while (lista.get(j).getName().compareTo(pivo) > 0) {
                comparacoes++;
                j--;
            }
            comparacoes++;
            
            if (i <= j) {
                Games temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
                i++;
                j--;
            }
        }
        
        if (esq < j) quicksort(lista, esq, j);
        if (i < dir) quicksort(lista, i, dir);
    }

    public static boolean pesquisaBinaria(List<Games> lista, String x) {
        boolean resp = false;
        int dir = (lista.size() - 1), esq = 0, meio;

        while (esq <= dir){
            meio = (esq + dir) / 2;
            
            comparacoes++;
            int cmp = x.trim().compareTo(lista.get(meio).getName());

            if(cmp == 0){
                resp = true;
                esq = dir + 1;
            } else if (cmp > 0) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return resp;
    }

    public static void criarLog() {
        try (PrintWriter fw = new PrintWriter(new FileWriter(log))) {
            fw.write(matricula + "\t" + tempoEmSegundos + "\t" + comparacoes);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        long inicioExecucao = System.currentTimeMillis();
        
        Scanner scanner = new Scanner(System.in);

        carregarCSV(arquivo);
        List<String> linhas = Games.getcsv();

        Games[] todosGames = new Games[linhas.size()];
        for (int i = 0; i < linhas.size(); i++) {
            todosGames[i] = preencher(linhas.get(i));
        }

        List<Games> selecionados = new ArrayList<>();
        String entrada = scanner.nextLine();

        while (!entrada.equals("FIM")) {
            int idBuscado = Integer.parseInt(entrada);

            for(int i = 0; i < todosGames.length; i++) {
                Games g = todosGames[i];
                if (g.getId() == idBuscado) {
                    selecionados.add(g);
                }
            }
            entrada = scanner.nextLine();
        }
        
        if (!selecionados.isEmpty()) {
            quicksort(selecionados, 0, selecionados.size() - 1);
        }
        
        entrada = scanner.nextLine();
        while (!entrada.equals("FIM")) {
            String nomeBuscado = entrada;

            boolean encontrado = pesquisaBinaria(selecionados, nomeBuscado);
                if(encontrado) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            
            entrada = scanner.nextLine();
        }

        long fimExecucao = System.currentTimeMillis();
        long tempoTotalMs = fimExecucao - inicioExecucao;
        
        tempoEmSegundos = tempoTotalMs / 1000.0;
        
        criarLog();

        scanner.close();
    }
}