import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Games {

    private int id;
    private String name;
    private String releaseDate;
    private int estimatedOwners;
    private float price;
    private String[] supportedLanguages;
    private int metacriticScore;
    private float userScore;
    private int achievements;
    private String[] publishers;
    private String[] developers;
    private String[] categories;
    private String[] genres;
    private String[] tags;


    public Games() { 
        this.id = 0;
        this.name = " ";
        this.releaseDate = " ";
        this.estimatedOwners = 0;
        this.price = 0.0f;
        this.supportedLanguages = new String[0];
        this.metacriticScore = -1;
        this.userScore = -1.0f;
        this.achievements = 0;
        this.publishers = new String[0];
        this.developers = new String[0];
        this.categories = new String[0];
        this.genres = new String[0];
        this.tags = new String[0];
    }

    public void setId(int arqId) { this.id = arqId; }
    public void setName(String name) { this.name = (name == null || name.isEmpty()) ? " " : name.trim(); }
    public void setReleaseDate(String arqDate) {
        if (arqDate == null || arqDate.isEmpty()) { this.releaseDate = " "; return; }
        arqDate = arqDate.replace("\"", "").trim(); //RELACE: remove aspas
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        try {
            String[] p = arqDate.split(" "); //SPLIT: separa por espaço
            if (p.length == 3) {
                int day = Integer.parseInt(p[1].replace(",", ""));
                int m = 0;
                for (int i = 0; i < months.length; i++) 
                    m = (p[0].startsWith(months[i])) ? i + 1 : m;
                this.releaseDate = String.format("%02d/%02d/%s", day, m, p[2]);
                return;
            }
        } catch (Exception e) {}
        String[] p = arqDate.split("/");
        if (p.length == 1) this.releaseDate = "01/01/" + p[0];
        else if (p.length == 2) this.releaseDate = "01/" + p[0] + "/" + p[1];
        else if (p.length == 3) this.releaseDate = p[0] + "/" + p[1] + "/" + p[2];
        else this.releaseDate = " ";
    }

    public void setEstimatedOwners(String s) {
        if (s == null || s.isEmpty()) this.estimatedOwners = 0;
        else { s = s.replaceAll("[^0-9]", ""); // remove tudo que não for número
        this.estimatedOwners = Integer.parseInt(s); }
    }

    public void setPrice(String s) {
        if (s == null || s.isEmpty()) this.price = 0.0f;
        else this.price = Float.parseFloat(s.replace(",", "."));
    }

    public void setSupportedLanguages(String s) {
        if (s == null || s.isEmpty()) { this.supportedLanguages = new String[0]; return; }
        s = s.replace("[", "").replace("]", "").replace("'", "").trim();
        String[] p = s.split(",");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        this.supportedLanguages = p;
    }

    public void setMetacriticScore(String s) {
        this.metacriticScore = (s == null || s.isEmpty()) ? -1 : Integer.parseInt(s);
    }

    public void setUserScore(String s) {
        this.userScore = (s == null || s.isEmpty()) ? -1.0f : Float.parseFloat(s.replace(",", "."));
    }

    public void setAchievements(String s) {
        this.achievements = (s == null || s.isEmpty()) ? 0 : Integer.parseInt(s);
    }

    public void setPublishers(String s) {
        if (s == null || s.isEmpty()) { this.publishers = new String[0]; return; }
        String[] p = s.split(",");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        this.publishers = p;
    }

    public void setDevelopers(String s) {
        if (s == null || s.isEmpty()) { this.developers = new String[0]; return; }
        String[] p = s.split(",");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        this.developers = p;
    }

    public void setCategories(String s) {
        if (s == null || s.isEmpty()) { this.categories = new String[0]; return; }
        String[] p = s.split(",");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        this.categories = p;
    }

    public void setGenres(String s) {
        if (s == null || s.isEmpty()) { this.genres = new String[0]; return; }
        String[] p = s.split(",");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        this.genres = p;
    }

    public void setTags(String s) {
        if (s == null || s.isEmpty()) { this.tags = new String[0]; return; }
        String[] p = s.split(",");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        this.tags = p;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public static String arquivo = "/tmp/games.csv"; // caminho do arquivo CSV
    private static List<String> csv = new ArrayList<>(); // lista para armazenar as linhas do CSV
    public static List<String> getcsv(){ return csv; }

    // Carregar o arquivo CSV
    public static void carregarCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) { //BUFFEREDREADER: ler arquivo
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) csv.add(line);
        }
        catch (IOException e) {}
    }

    public static Games preencher(String line) {
        String[] f = ler(line);
        Games g = new Games();
        g.setId(Integer.parseInt(f[0]));
        g.setName(f[1]);
        g.setReleaseDate(f[2]);
        g.setEstimatedOwners(f[3]);
        g.setPrice(f[4]);
        g.setSupportedLanguages(f[5]);
        g.setMetacriticScore(f[6]);
        g.setUserScore(f[7]);
        g.setAchievements(f[8]);
        g.setPublishers(f[9]);
        g.setDevelopers(f[10]);
        g.setCategories(f[11]);
        g.setGenres(f[12]);
        g.setTags(f[13]);
        return g;
    }

    // Método para ler uma linha do CSV considerando aspas
    public static String[] ler(String line) {
        List<String> r = new ArrayList<>();
        boolean aspas = false;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') aspas = !aspas;
            else if (c == ',' && !aspas) { r.add(s.toString().trim()); s = new StringBuilder(); }
            else s.append(c);
        }
        r.add(s.toString().trim());
        return r.toArray(new String[0]);
    }

    public void imprimir() {
        System.out.println("=> " + this.getId() + " ## " + this.getName());
    }


    // Classe de Hash com reserva
    static class HashReserva {
        private String[] tabela;
        private int tamPrincipal = 21;
        private int tamTotal = 30;
        private int reservaInicio = 21;
        private int proxReserva = 21;
        private int comparacoes = 0;

        HashReserva() {
            tabela = new String[tamTotal];
        }

        private int hash(String nome) {
            int soma = 0;
            for (int i = 0; i < nome.length(); i++)
                soma += nome.charAt(i);
            return soma % tamPrincipal;
        }

        public void inserir(String nome) {
            int pos = hash(nome);
            if (tabela[pos] == null) {
                tabela[pos] = nome;
            } else {
                if (proxReserva >= tamTotal) return;
                tabela[proxReserva++] = nome;
            }
        }

        public int[] buscar(String nome) {
            int pos = hash(nome);
            comparacoes++;
            
            if (tabela[pos] != null && nome.equals(tabela[pos])) {
                return new int[]{pos, 1}; //posição, encontrado
            }
            
            // Se não encontrou na posição principal, busca na reserva
            if (tabela[pos] != null) {
                for (int i = reservaInicio; i < proxReserva; i++) {
                    comparacoes++;
                    if (tabela[i] != null && nome.equals(tabela[i])) {
                        return new int[]{i, 1}; //posição, encontrado
                    }
                }
            }
            
            return new int[]{pos, 0}; // posição calculada, não encontrado
        }

        public int getComparacoes() {
            return comparacoes;
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        carregarCSV(arquivo);

        List<String> lines = Games.getcsv();
        Games[] games = new Games[lines.size()];

        for (int i = 0; i < lines.size(); i++)
            games[i] = preencher(lines.get(i));

        List<Games> selecionados = new ArrayList<>();

        String input = scanner.nextLine();
        while (!input.equals("FIM")) {
            int idBuscado = Integer.parseInt(input);
            for (Games g : games)
                if (g.getId() == idBuscado)
                    selecionados.add(g);
            input = scanner.nextLine();
        }

        // Criar a tabela hash com reserva e inserir os jogos selecionados
        HashReserva hash = new HashReserva();
        for (Games g : selecionados) {
            hash.inserir(g.getName());
        }

        List<String> nomesBusca = new ArrayList<>();
        String nome = scanner.nextLine();
        while (!nome.equals("FIM")) {
            nomesBusca.add(nome);
            nome = scanner.nextLine();
        }
        scanner.close();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("874205_hashReserva.txt"));

            for (String n : nomesBusca) {
                int[] resultado = hash.buscar(n);
                int pos = resultado[0];
                boolean encontrado = (resultado[1] == 1);
                String status = encontrado ? "SIM" : "NAO";
                System.out.println(n + ":  (Posicao: " + pos + ") " + status);
            }

            int totalComparacoes = hash.getComparacoes();
            bw.write("874205\t" + totalComparacoes);
            bw.close();

        } catch (Exception e) {}

    }

}