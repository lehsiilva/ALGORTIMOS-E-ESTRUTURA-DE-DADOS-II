
import java.io.*;
import java.text.*;
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

    // Construtor
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

    // GETS
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

    // SETS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name != null ? name.trim() : "";
    }

    // Converte data do formato "MMM dd, yyyy" para "dd/MM/yyyy"
    public void setReleaseDate(String data) {
        if (data == null || data.isEmpty()) {
            this.releaseDate = "";
            return;
        }
        data = data.replace("\"", "").trim(); // TRIM: remove espaços no inicio e fim. //REPLACE: troca um pelo o outro
        try {
            DateFormat formatoEntrada = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
            DateFormat formatoSaida = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formatoEntrada.parse(data);
            this.releaseDate = formatoSaida.format(dataFormatada);
        } catch (ParseException e) {
            this.releaseDate = data;
        }
    }

    public void setEstimatedOwners(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.estimatedOwners = 0; 
        }else {
            this.estimatedOwners = Integer.parseInt(valor.replaceAll("[^0-9]", ""));
        }
    }

    public void setPrice(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.price = 0.0f; 
        }else {
            this.price = Float.parseFloat(valor.replace(",", "."));
        }
    }

    public void setSupportedLanguages(String str) {
        if (str == null || str.isEmpty()) {
            this.supportedLanguages = new String[0];
            return;
        }
        str = str.replace("[", "").replace("]", "").replace("'", "").trim();
        this.supportedLanguages = str.split(",");
        for (int i = 0; i < supportedLanguages.length; i++) {
            supportedLanguages[i] = supportedLanguages[i].trim();
        }
    }

    public void setMetacriticScore(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.metacriticScore = -1; 
        }else {
            this.metacriticScore = Integer.parseInt(valor);
        }
    }

    public void setUserScore(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.userScore = -1.0f; 
        }else {
            this.userScore = Float.parseFloat(valor.replace(",", "."));
        }
    }

    public void setAchievements(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.achievements = 0; 
        }else {
            this.achievements = Integer.parseInt(valor);
        }
    }

    public void setPublishers(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.publishers = new String[0];
            return;
        }
        this.publishers = valor.split(","); //SPLIT: Funciona como um separador
        for (int i = 0; i < publishers.length; i++) {
            publishers[i] = publishers[i].trim();
        }
    }

    public void setDevelopers(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.developers = new String[0];
            return;
        }
        this.developers = valor.split(",");
        for (int i = 0; i < developers.length; i++) {
            developers[i] = developers[i].trim();
        }
    }

    public void setCategories(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.categories = new String[0];
            return;
        }
        this.categories = valor.split(",");
        for (int i = 0; i < categories.length; i++) {
            categories[i] = categories[i].trim();
        }
    }

    public void setGenres(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.genres = new String[0];
            return;
        }
        this.genres = valor.split(",");
        for (int i = 0; i < genres.length; i++) {
            genres[i] = genres[i].trim();
        }
    }

    public void setTags(String valor) {
        if (valor == null || valor.isEmpty()) {
            this.tags = new String[0];
            return;
        }
        this.tags = valor.split(",");
        for (int i = 0; i < tags.length; i++) {
            tags[i] = tags[i].trim();
        }
    }

    public void imprimir(int index) {
        System.out.println("[" + index + "] => " + id + " ## " + name + " ## " + releaseDate + " ## "
                + estimatedOwners + " ## " + price + " ## " + Arrays.toString(supportedLanguages) + " ## "
                + metacriticScore + " ## " + userScore + " ## " + achievements + " ## "
                + Arrays.toString(publishers) + " ## " + Arrays.toString(developers) + " ## "
                + Arrays.toString(categories) + " ## " + Arrays.toString(genres) + " ## "
                + Arrays.toString(tags) + " ##");
    }

    private static String arquivo = "/tmp/games.csv";
    private static List<String> csv = new ArrayList<>();

    // Carrega todas as linhas do CSV, ignorando o cabeçalho
    public static void carregarCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            br.readLine(); // pular cabeçalho
            String line;
            while ((line = br.readLine()) != null) {
                csv.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    public static List<String> getcsv() {
        return csv;
    }

    // Cria objeto Games a partir de uma linha do CSV
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

    public static String[] ler(String line) {
        List<String> result = new ArrayList<>();
        boolean aspas = false;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                aspas = !aspas; 
            }else if (c == ',' && !aspas) {
                result.add(str.toString().trim());
                str = new StringBuilder();
            } else {
                str.append(c);
            }
        }
        result.add(str.toString().trim());
        return result.toArray(new String[0]);
    }

    //Lista
    static class ListaGames {

        private Games[] array;
        private int n;

        public ListaGames(int tamanho) {
            array = new Games[tamanho];
            n = 0;
        }

        public void inserirInicio(Games game) throws Exception {
            if (n >= array.length) {
                throw new Exception("Erro: lista cheia");
            }
            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = game;
            n++;
        }

        public void inserir(Games game, int posicao) throws Exception {
            if (n >= array.length) {
                throw new Exception("Erro: lista cheia");
            }
            if (posicao < 0 || posicao > n) {
                throw new Exception("Erro: posição inválida");
            }
            for (int i = n; i > posicao; i--) {
                array[i] = array[i - 1];
            }
            array[posicao] = game;
            n++;
        }

        public void inserirFim(Games game) throws Exception {
            if (n >= array.length) {
                throw new Exception("Erro: lista cheia");
            }
            array[n++] = game;
        }

        public Games removerInicio() throws Exception {
            if (n == 0) {
                throw new Exception("Erro: lista vazia");
            }
            Games removido = array[0];
            for (int i = 0; i < n - 1; i++) {
                array[i] = array[i + 1];
            }
            n--;
            return removido;
        }

        public Games remover(int posicao) throws Exception {
            if (n == 0) {
                throw new Exception("Erro: lista vazia");
            }
            if (posicao < 0 || posicao >= n) {
                throw new Exception("Erro: posição inválida");
            }
            Games removido = array[posicao];
            for (int i = posicao; i < n - 1; i++) {
                array[i] = array[i + 1];
            }
            n--;
            return removido;
        }

        public Games removerFim() throws Exception {
            if (n == 0) {
                throw new Exception("Erro: lista vazia");
            }
            return array[--n];
        }

        public void mostrar() {
            for (int i = 0; i < n; i++) {
                array[i].imprimir(i);
            }
        }
    }

    public static Games buscarPorId(Games[] games, int id) {
        Games resultado = null;
        for (Games g : games) {
            if (g.getId() == id) {
                resultado = g;
            }
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        carregarCSV(arquivo); // Carrega todas as linhas do CSV na lista estática
        List<String> linhas = getcsv();

        Games[] games = new Games[linhas.size()];
        for (int i = 0; i < linhas.size(); i++) {
            games[i] = preencher(linhas.get(i));
        }

        ListaGames lista = new ListaGames(1000);

        String entrada = sc.nextLine();
        while (!entrada.equals("FIM")) {
            int id = Integer.parseInt(entrada);
            boolean encontrado = false;
            int idx = 0;
            while (idx < games.length && !encontrado) {
                if (games[idx].getId() == id) {
                    try {
                        lista.inserirFim(games[idx]);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    encontrado = true;
                }
                idx++;
            }
            entrada = sc.nextLine();
        }

        int nComandos = Integer.parseInt(sc.nextLine());
        int i = 0;
        while (i < nComandos) {
            String linha = sc.nextLine();
            if (!linha.trim().isEmpty()) {
                String[] partes = linha.split(" ");
                try {
                    if (partes[0].equals("II")) {
                        Games g1 = buscarPorId(games, Integer.parseInt(partes[1]));
                        if (g1 != null) {
                            lista.inserirInicio(g1);
                        }
                    } else if (partes[0].equals("IF")) {
                        Games g2 = buscarPorId(games, Integer.parseInt(partes[1]));
                        if (g2 != null) {
                            lista.inserirFim(g2);
                        }
                    } else if (partes[0].equals("I*")) {
                        int pos = Integer.parseInt(partes[1]);
                        Games g3 = buscarPorId(games, Integer.parseInt(partes[2]));
                        if (g3 != null) {
                            lista.inserir(g3, pos);
                        }
                    } else if (partes[0].equals("RI")) {
                        System.out.println("(R) " + lista.removerInicio().getName());
                    } else if (partes[0].equals("RF")) {
                        System.out.println("(R) " + lista.removerFim().getName());
                    } else if (partes[0].equals("R*")) {
                        System.out.println("(R) " + lista.remover(Integer.parseInt(partes[1])).getName());
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
            i++;
        }

        lista.mostrar();
        sc.close();
    }
}
