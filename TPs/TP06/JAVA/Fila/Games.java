import java.io.*;
import java.nio.charset.StandardCharsets; //para ler arquivos com acentuação
import java.util.*;

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
    private String linhaOriginal; // Armazena a linha original do CSV

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
        this.linhaOriginal = "";
    }

    //SETS
    public void setId(int arqId) {
        this.id = arqId;
    }

    public void setName(String name) {
        this.name = (name == null || name.isEmpty()) ? " " : name.trim();
    }

    public void setReleaseDate(String arqDate) {
        if (arqDate == null || arqDate.isEmpty()) {
            this.releaseDate = " ";
            return;
        }
        arqDate = arqDate.replace("\"", "").trim();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        try {
            String[] parts = arqDate.split(" "); //SPLIT: para datas no formato "Month Day, Year"
            if (parts.length == 3) {
                int day = Integer.parseInt(parts[1].replace(",", "")); //Replace para remover a vírgula
                int month = 0;
                for (int i = 0; i < months.length; i++) {
                    if (parts[0].startsWith(months[i])) month = i + 1; //startsWith para evitar problemas com abreviações
                }
                String year = parts[2];
                this.releaseDate = String.format("%02d/%02d/%s", day, month, year);
                return;
            }
        } catch (Exception e) {
        }
        String[] parts = arqDate.split("/");
        if (parts.length == 1) {
            this.releaseDate = "01/01/" + parts[0];
        } else if (parts.length == 2) {
            this.releaseDate = "01/" + Integer.parseInt(parts[0]) + "/" + parts[1];
        } else if (parts.length == 3) {
            this.releaseDate = Integer.parseInt(parts[0]) + "/" + Integer.parseInt(parts[1]) + "/" + parts[2];
        } else {
            this.releaseDate = " ";
        }
    }

    public void setEstimatedOwners(String arqOwners) {
        this.estimatedOwners = (arqOwners == null || arqOwners.isEmpty()) ? 0 : Integer.parseInt(arqOwners.replaceAll("[^0-9]", ""));
    }

    public void setPrice(String arqPrice) {
        this.price = (arqPrice == null || arqPrice.isEmpty()) ? 0.0f : Float.parseFloat(arqPrice.replace(",", "."));
    }

    public void setSupportedLanguages(String arqLanguages) {
        this.supportedLanguages = splitAndTrim(arqLanguages);
    }

    public void setMetacriticScore(String arqScore) {
        this.metacriticScore = (arqScore == null || arqScore.isEmpty()) ? -1 : Integer.parseInt(arqScore);
    }

    public void setUserScore(String arqScore) {
        this.userScore = (arqScore == null || arqScore.isEmpty()) ? -1.0f : Float.parseFloat(arqScore.replace(",", "."));
    }

    public void setAchievements(String arqAchievements) {
        this.achievements = (arqAchievements == null || arqAchievements.isEmpty()) ? 0 : Integer.parseInt(arqAchievements);
    }

    public void setPublishers(String arqPublishers) {
        this.publishers = splitAndTrim(arqPublishers);
    }

    public void setDevelopers(String arqDevelopers) {
        this.developers = splitAndTrim(arqDevelopers);
    }

    public void setCategories(String arqCategories) {
        this.categories = splitAndTrim(arqCategories);
    }

    public void setGenres(String arqGenres) {
        this.genres = splitAndTrim(arqGenres);
    }

    public void setTags(String arqTags) {
        this.tags = splitAndTrim(arqTags);
    }

    public void setLinhaOriginal(String linha) {
        this.linhaOriginal = linha;
    }

    private String[] splitAndTrim(String s) {
        if (s == null || s.isEmpty()) {
            return new String[0];
        }
        String[] parts = s.replace("[", "").replace("]", "").replace("'", "").split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }

    //GETS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLinhaOriginal() {
        return linhaOriginal;
    }

    //Para imprimir resumos dos jogos removidos
    public void imprimirResumo(int posicao) {
        System.out.println("(R) " + this.getName());
    }

    public void imprimirDetalhado(int index) {
        System.out.println("[" + index + "] => "
                + this.getId() + " ## "
                + this.getName() + " ## "
                + this.releaseDate + " ## "
                + this.estimatedOwners + " ## "
                + this.price + " ## "
                + Arrays.toString(this.supportedLanguages) + " ## "
                + this.metacriticScore + " ## "
                + this.userScore + " ## "
                + this.achievements + " ## "
                + Arrays.toString(this.publishers) + " ## "
                + Arrays.toString(this.developers) + " ## "
                + Arrays.toString(this.categories) + " ## "
                + Arrays.toString(this.genres) + " ## "
                + Arrays.toString(this.tags) + " ##");
    }

    private static String arquivo = "/tmp/games.csv";
    public static List<String> csv = new ArrayList<>();

    //Para carregar CSV e preencher objetos
    public static void carregarCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            br.readLine(); // ignora cabeçalho
            String line;
            while ((line = br.readLine()) != null) {
                csv.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }
    }

    public static Games preencher(String line) {
        String[] f = ler(line);
        Games g = new Games();
        g.setLinhaOriginal(line);
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

    //Ler linha do CSV considerando aspas
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

    //Fila Circular
    static class Fila {

        private Games[] array;
        private int primeiro;
        private int ultimo;
        private int tamanho;

        public Fila(int capacidade) {
            array = new Games[capacidade + 1];
            primeiro = 0;
            ultimo = 0;
            tamanho = 0;
        }

        public void enqueue(Games g) throws Exception {
            if ((ultimo + 1) % array.length == primeiro) {
                throw new Exception("Fila cheia");
            }
            array[ultimo] = g;
            ultimo = (ultimo + 1) % array.length;
            tamanho++;
        }

        public Games dequeue() throws Exception {
            if (primeiro == ultimo) {
                throw new Exception("Fila vazia");
            }
            Games resp = array[primeiro];
            primeiro = (primeiro + 1) % array.length;
            tamanho--;
            return resp;
        }

        public void mostrarTodosDetalhado() {
            int indice = 0;
            for (int i = primeiro; i != ultimo; i = (i + 1) % array.length) {
                array[i].imprimirDetalhado(indice);
                indice++;
            }
        }

        public int tamanho() {
            return tamanho;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        carregarCSV(arquivo); // Carrega o CSV uma vez

        List<String> lines = csv;
        Games[] games = new Games[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            games[i] = preencher(lines.get(i));
        }

        Fila fila = new Fila(100);

        // Leitura de IDs iniciais
        boolean lendoInicial = true;
        while (lendoInicial && scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("FIM")) {
                lendoInicial = false;
            } else if (!line.isEmpty()) {
                int idBuscado = Integer.parseInt(line);
                int idx = 0;
                boolean encontrado = false;
                while (idx < games.length && !encontrado) {
                    if (games[idx].getId() == idBuscado) {
                        fila.enqueue(games[idx]);
                        encontrado = true;
                    }
                    idx++;
                }
            }
        }

        // Processa comandos I <id> ou R
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            boolean processar = !line.isEmpty();
            if (processar) {
                if (line.startsWith("I")) {
                    int idBuscado = Integer.parseInt(line.split(" ")[1]);
                    int idx = 0;
                    boolean encontrado = false;
                    while (idx < games.length && !encontrado) {
                        if (games[idx].getId() == idBuscado) {
                            fila.enqueue(games[idx]);
                            encontrado = true;
                        }
                        idx++;
                    }
                } else if (line.startsWith("R")) {
                    Games removido = fila.dequeue();
                    removido.imprimirResumo(0); // imprime (R) NomeDoJogo
                }
            }
        }

        // Imprime detalhado todos os restantes
        fila.mostrarTodosDetalhado();
        scanner.close();
    }
}
