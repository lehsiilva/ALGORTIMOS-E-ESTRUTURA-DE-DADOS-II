import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Log {
   public static String logFileName = "874205_mergesort.txt"; 
    public static final String MATRICULA = "874205"; 
    public static long comparacoes = 0; 
    public static long movimentacoes = 0; 
    public static double tempoExecucao = 0; 

    public static void registrarLog(String log) {
        String tempoFormatado = String.format("%.2f", tempoExecucao);
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(log))) {
            writer.printf("%s\t%d\t%d\t%s\n", MATRICULA, comparacoes, movimentacoes, tempoFormatado);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
    public static String getMATRICULA() {
        return MATRICULA;
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

    public Games(Games other) {
        this.id = other.id;
        this.name = other.name;
        this.releaseDate = other.releaseDate;
        this.estimatedOwners = other.estimatedOwners;
        this.price = other.price;
        this.supportedLanguages = other.supportedLanguages.clone();
        this.metacriticScore = other.metacriticScore;
        this.userScore = other.userScore;
        this.achievements = other.achievements;
        this.publishers = other.publishers.clone();
        this.developers = other.developers.clone();
        this.categories = other.categories.clone();
        this.genres = other.genres.clone();
        this.tags = other.tags.clone();
    }

    public void setId(int arqId) {
        this.id = arqId;
    }

    public void setName(String name) {
        if (!(name == null || name.isEmpty())) {
            this.name = name.trim();
        } else {
            this.name = " ";
        }
    }

    public void setReleaseDate(String arqDate) {
        if (arqDate == null || arqDate.isEmpty()) {
            this.releaseDate = " ";
            return;
        }

        arqDate = arqDate.replace("\"", "").trim();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        try {
            String[] parts = arqDate.split(" ");
            if (parts.length == 3) {
                int day = Integer.parseInt(parts[1].replace(",", ""));
                int month = 0;

                for (int i = 0; i < months.length; i++) {
                    month = (parts[0].startsWith(months[i])) ? i + 1 : month;
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
            this.releaseDate = " ";
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

    public void imprimir() {
        System.out.println("=> " + this.getId() + " ## " + this.getName() + " ## " + this.getReleaseDate() + " ## " +
                this.getEstimatedOwners() + " ## " + String.format("%.2f", this.getPrice()) + " ## " +
                java.util.Arrays.toString(this.getSupportedLanguages()) + " ## " +
                this.getMetacriticScore() + " ## " + String.format("%.1f", this.getUserScore()) + " ## " +
                this.getAchievements() + " ## " +
                java.util.Arrays.toString(this.getPublishers()) + " ## " +
                java.util.Arrays.toString(this.getDevelopers()) + " ## " +
                java.util.Arrays.toString(this.getCategories()) + " ## " +
                java.util.Arrays.toString(this.getGenres()) + " ## " +
                java.util.Arrays.toString(this.getTags()) + " ##");
    }

    public static int compare(Games g1, Games g2) {
        Log.comparacoes++;
        if (g1.getPrice() != g2.getPrice()) {
            return Float.compare(g1.getPrice(), g2.getPrice());
        }
        return Integer.compare(g1.getId(), g2.getId());
    }

    public static void mergesort(Games[] array) {
        Games[] tmp = new Games[array.length];
        mergesort(array, tmp, 0, array.length - 1);
    }

    private static void mergesort(Games[] array, Games[] tmp, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(array, tmp, esq, meio);
            mergesort(array, tmp, meio + 1, dir);
            merge(array, tmp, esq, meio, dir);
        }
    }

    private static void merge(Games[] array, Games[] tmp, int esq, int meio, int dir) {
        int i = esq, j = meio + 1, k = esq;

        for (int p = esq; p <= dir; p++) {
            tmp[p] = array[p];
        }

        while (i <= meio && j <= dir) {
            if (compare(tmp[i], tmp[j]) <= 0) {
                array[k++] = tmp[i++];
            } else {
                array[k++] = tmp[j++];
            }
            Log.movimentacoes++;
        }

        while (i <= meio) {
            array[k++] = tmp[i++];
            Log.movimentacoes++;
        }

        while (j <= dir) {
            array[k++] = tmp[j++];
            Log.movimentacoes++;
        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        carregarCSV(arquivo);
        List<String> lines = Games.getcsv();

        Games[] allGames = new Games[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            allGames[i] = preencher(lines.get(i));
        }

        List<Games> selecionados = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("FIM")) {
            try {
                int idBuscado = Integer.parseInt(input);

                for(Games g : allGames) {
                    if (g.getId() == idBuscado) {
                        selecionados.add(new Games(g)); 
                    }
                }
            } catch (NumberFormatException e) {
            }

            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            } else {
                input = "FIM";
            }
        }

        scanner.close();

        Games[] gamesParaOrdenar = selecionados.toArray(new Games[0]);

        Log.comparacoes = 0;
        Log.movimentacoes = 0;

        long startTime = System.nanoTime();
        mergesort(gamesParaOrdenar);
        long endTime = System.nanoTime();

        Log.tempoExecucao = (endTime - startTime) / 1000000.0; 
        
        String logFileName = Log.getMATRICULA() + "_mergesort.txt";
        
        // Chamada de log
        Log.registrarLog(logFileName);

        int N = gamesParaOrdenar.length;

        if (N > 0) {
            System.out.println("| 5 pre\u00E7os mais caros |");
            for (int i = N - 1; i >= Math.max(0, N - 5); i--) {
                gamesParaOrdenar[i].imprimir();
            }

            System.out.println("");

            System.out.println("| 5 pre\u00E7os mais baratos |");
            for (int i = 0; i < Math.min(5, N); i++) {
                gamesParaOrdenar[i].imprimir();
            }

            
        }
    }
}