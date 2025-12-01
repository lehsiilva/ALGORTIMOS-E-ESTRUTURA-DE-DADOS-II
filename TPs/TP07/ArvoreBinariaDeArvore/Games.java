import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Nó da segunda árvore 
class No2 {
    String chave;
    Games game;
    No2 esq, dir;

    public No2(Games g) {
        this.chave = g.getName();
        this.game = g;
        this.esq = this.dir = null;
    }
}

// Árvore binária secundária (organizada por Name)
class ArvoreBinaria2 {
    No2 raiz;

    public ArvoreBinaria2() {
        raiz = null;
    }

    public void inserir(Games g) {
        raiz = inserirRec(g, raiz);
    }

    private No2 inserirRec(Games g, No2 i) {
        if (i == null) {
            return new No2(g);
        }

        int cmp = g.getName().compareTo(i.chave);

        if (cmp < 0) {
            i.esq = inserirRec(g, i.esq);
        } 
        else if (cmp > 0) {
            i.dir = inserirRec(g, i.dir);
        }

        return i;
    }

    public boolean pesquisar(String nome) {
        return pesquisarRec(nome, raiz);
    }

    private boolean pesquisarRec(String nome, No2 i) {
        if (i == null) {
            return false;
        }

        int cmp = nome.compareTo(i.chave);

        if (cmp == 0) {
            return true;
        } 
        else if (cmp < 0) {
            System.out.print("esq ");
            return pesquisarRec(nome, i.esq);
        } 
        else {
            System.out.print("dir ");
            return pesquisarRec(nome, i.dir);
        }
    }
}

// Nó da primeira árvore 
class No1 {
    int chave;
    No1 esq, dir;
    ArvoreBinaria2 arvore2;

    public No1(int chave) {
        this.chave = chave;
        this.esq = this.dir = null;
        this.arvore2 = new ArvoreBinaria2();
    }
}

// Árvore binária principal 
class ArvoreBinaria1 {
    No1 raiz;

    public ArvoreBinaria1() {
        raiz = null;
    }

    // Criar estrutura inicial com a ordem especificada
    public void criarEstrutura() {
        int[] ordem = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
        for (int chave : ordem) {
            raiz = inserirChave(chave, raiz);
        }
    }

    private No1 inserirChave(int chave, No1 i) {
        if (i == null) {
            return new No1(chave);
        }

        if (chave < i.chave) {
            i.esq = inserirChave(chave, i.esq);
        } 
        else if (chave > i.chave) {
            i.dir = inserirChave(chave, i.dir);
        }

        return i;
    }

    public void inserirGame(Games g, int mod) {
        No1 no = buscarNo(mod, raiz);
        if (no != null) {
            no.arvore2.inserir(g);
        }
    }

    private No1 buscarNo(int chave, No1 i) {
        if (i == null) {
            return null;
        }

        if (chave == i.chave) {
            return i;
        } 
        else if (chave < i.chave) {
            return buscarNo(chave, i.esq);
        } 
        else {
            return buscarNo(chave, i.dir);
        }
    }

    public boolean pesquisar(String nome) {
        System.out.print("=> " + nome + " => raiz ");
        boolean encontrado = pesquisarRec(nome, raiz);
        System.out.println(encontrado ? " SIM" : " NAO");
        return encontrado;
    }

    private boolean pesquisarRec(String nome, No1 i) {
        if (i == null) {
            return false;
        }

        // Pesquisa na segunda árvore deste nó
        if (i.arvore2.raiz != null) {
            if (i.arvore2.pesquisar(nome)) {
                return true;
            }
        }

        // Não encontrou continua na primeira árvore
        System.out.print(" ESQ ");
        if (pesquisarRec(nome, i.esq)) {
            return true;
        }

        System.out.print(" DIR ");
        return pesquisarRec(nome, i.dir);
    }
}

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

    // SETS
    public void setId(int arqId) {
        this.id = arqId;
    }

    public void setName(String name) {
        if (!(name == null || name.isEmpty())) {
            this.name = name.trim(); //TRIM: remove espaços extras
        } else {
            this.name = " ";
        }
    }

    public void setReleaseDate(String arqDate) {
        if (arqDate == null || arqDate.isEmpty()) {
            this.releaseDate = " ";
            return;
        }

        arqDate = arqDate.replace("\"", "").trim(); //REPLACE: remove aspas

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        try {
            String[] parts = arqDate.split(" ");
            if (parts.length == 3) {
                int day = Integer.parseInt(parts[1].replace(",", ""));
                int month = 0;

                for (int i = 0; i < months.length; i++) {
                    month = (parts[0].startsWith(months[i])) ? i + 1 : month; //STARTSWITH: verifica os primeiros caracteres
                }

                String year = parts[2];
                this.releaseDate = String.format("%02d/%02d/%s", day, month, year);
                return;
            }
        } catch (Exception e) {}

        String[] parts = arqDate.split("/");
        if(parts.length == 1){
            this.releaseDate = "01/01/" + parts[0];
        } else if (parts.length == 2) {
            int mes = Integer.parseInt(parts[0]);
            this.releaseDate = "01/" + mes + "/" + parts[1];
        } else if (parts.length == 3) {
            int dia = Integer.parseInt(parts[0]);
            int mes = Integer.parseInt(parts[1]);
            this.releaseDate = dia + "/" + mes + "/" + parts[2];
        } else {
            this.releaseDate =  " ";
        }
    }

    public void setEstimatedOwners(String arqOwners) {
        if (arqOwners == null || arqOwners.isEmpty()) {
            this.estimatedOwners = 0;
        } else {
            arqOwners = arqOwners.replaceAll("[^0-9]", "");
            this.estimatedOwners = Integer.parseInt(arqOwners);
        }
    }

    public void setPrice(String arqPrice) {
        if (arqPrice == null || arqPrice.isEmpty()) {
            this.price = 0.0f;
        } else {
            this.price = Float.parseFloat(arqPrice.replace(",", "."));
        }
    }

    public void setSupportedLanguages(String arqLanguages) {
        if (arqLanguages == null || arqLanguages.isEmpty()) {
            this.supportedLanguages = new String[0];
            return;
        }

        arqLanguages = arqLanguages.replace("[", "").replace("]", "").replace("'", "").trim();
        String[] parts = arqLanguages.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.supportedLanguages = parts;
    }

    public void setMetacriticScore(String arqScore) {
        if (arqScore == null || arqScore.isEmpty()) {
            this.metacriticScore = -1;
        } else {
            this.metacriticScore = Integer.parseInt(arqScore);
        }
    }

    public void setUserScore(String arqScore) {
        if (arqScore == null || arqScore.isEmpty()) {
            this.userScore = -1.0f;
        } else {
            this.userScore = Float.parseFloat(arqScore.replace(",", "."));
        }
    }

    public void setAchievements(String arqAchievements) {
        if (arqAchievements == null || arqAchievements.isEmpty()) {
            this.achievements = 0;
        } else {
            this.achievements = Integer.parseInt(arqAchievements);
        }
    }

    public void setPublishers(String arqPublishers) {
        if (arqPublishers == null || arqPublishers.isEmpty()) {
            this.publishers = new String[0];
            return;
        }
        String[] parts = arqPublishers.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.publishers = parts;
    }

    public void setDevelopers(String arqDevelopers) {
        if (arqDevelopers == null || arqDevelopers.isEmpty()) {
            this.developers = new String[0];
            return;
        }
        String[] parts = arqDevelopers.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.developers = parts;
    }

    public void setCategories(String arqCategories) {
        if (arqCategories == null || arqCategories.isEmpty()) {
            this.categories = new String[0];
            return;
        }
        String[] parts = arqCategories.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.categories = parts;
    }

    public void setGenres(String arqGenres) {
        if (arqGenres == null || arqGenres.isEmpty()) {
            this.genres = new String[0]; 
            return;
        }
        String[] parts = arqGenres.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.genres = parts;
    }

    public void setTags(String arqTags) {
        if (arqTags == null || arqTags.isEmpty()) {
            this.tags = new String[0];
            return;
        }
        String[] parts = arqTags.split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        this.tags = parts;
    }
    
    //GETS
    public int getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getReleaseDate() { 
        return releaseDate; 
    }
    public int getEstimatedOwners() { 
        return estimatedOwners; 
    }
    public float getPrice() { 
        return price; 
    }
    public String[] getSupportedLanguages() { 
        return supportedLanguages; 
    }
    public int getMetacriticScore() { 
        return metacriticScore; 
    }
    public float getUserScore() { 
        return userScore; 
    }
    public int getAchievements() { 
        return achievements; 
    }
    public String[] getPublishers() { 
        return publishers; 
    }
    public String[] getDevelopers() { 
        return developers; 
    }
    public String[] getCategories() { 
        return categories; 
    }
    public String[] getGenres() { 
        return genres; 
    }
    public String[] getTags() { 
        return tags; 
    }

    private static String arquivo = "/tmp/games.csv"; //caminho do arquivo CSV
    private static List<String> csv = new ArrayList<>(); //lista para armazenar as linhas do CSV

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

    // Função para ler uma linha do CSV considerando aspas
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
        List<String> lines = Games.getcsv();

        Games[] todos = new Games[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            todos[i] = preencher(lines.get(i));
        }

        ArvoreBinaria1 arvore = new ArvoreBinaria1();
        
        // Criar estrutura inicial da primeira árvore
        arvore.criarEstrutura();

        String entrada = scanner.nextLine();

        while (!entrada.equals("FIM")) {
            int idBuscado = Integer.parseInt(entrada);

            for (Games g : todos) {
                if (g.getId() == idBuscado) {
                    int mod = g.getEstimatedOwners() % 15;
                    arvore.inserirGame(g, mod);
                }
            }

            entrada = scanner.nextLine();
        }

        if (scanner.hasNextLine()) entrada = scanner.nextLine();
        else entrada = "FIM";

        while (!entrada.equals("FIM")) {
            arvore.pesquisar(entrada.trim());

            if (scanner.hasNextLine()) entrada = scanner.nextLine();
            else entrada = "FIM";
        }

        scanner.close();
    }
}