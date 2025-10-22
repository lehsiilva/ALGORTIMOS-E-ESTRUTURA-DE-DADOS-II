import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Games {
    public static String log = "874205_heapsort.txt";
    public static int matricula = 874205;
    public static long comparacoes = 0;
    public static long movimentacoes = 0;
    public static double tempoEmSegundos = 0.0; 

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

    // SETS
    public void setId(int arqId) {
        this.id = arqId;
    }

    public void setName(String name) {
        if (!(name == null || name.isEmpty())) {
            this.name = name.trim(); // TRIM: remove espaços no inicio e fim.
        } else {
            this.name = " ";
        }
    }

    public void setReleaseDate(String arqDate) {
        if (arqDate == null || arqDate.isEmpty()) {
            this.releaseDate = " ";
            return;
        }

        arqDate = arqDate.replace("\"", "").trim(); //REPLACE: troca um pelo o outro

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        try {
            String[] parts = arqDate.split(" "); //SPLIT: Funciona como um separador
            if (parts.length == 3) {
                int day = Integer.parseInt(parts[1].replace(",", ""));
                int month = 0;

                for (int i = 0; i < months.length; i++) {
                    month = (parts[0].startsWith(months[i])) ? i + 1 : month; //StartsWith: Analisa o começo das palavras
                }

                String year = parts[2];
                this.releaseDate = String.format("%02d/%02d/%s", day, month, year);
                return;
            }
        } catch (Exception e) {}

        //Tratamento datas a
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
            arqOwners = arqOwners.replaceAll("[^0-9]", "");//REPLACEALL: remove tudo exceto números
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
    
    private static String arquivo = "/tmp/games.csv"; // Caminho do arquivo CSV
    private static List<String> csv = new ArrayList<>();//Lista para armazenar as linhas do CSV


    public static List<String> getcsv(){
        return csv;
    }

    // carrega o arquivo inteiro para a lista csv
    public static void carregarCSV(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
        
        br.readLine(); // Lê e ignora o cabeçalho
        String line;
        while ((line = br.readLine()) != null) {
            csv.add(line);
        }
        
    } catch (IOException e) {
        System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
    }
}


    // converte uma linha do CSV em um objeto Games
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

    //divide uma única linha do CSV em um array de strings
    public static String[] ler(String line) {
        List<String> result = new ArrayList<>();
        boolean aspas = false;
        StringBuilder str = new StringBuilder();

        // alterna o valor de aspas para lidar com campos entre aspas
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
            this.getEstimatedOwners() + " ## " + this.getPrice() + " ## " +
            java.util.Arrays.toString(this.getSupportedLanguages()) + " ## " +
            this.getMetacriticScore() + " ## " + this.getUserScore() + " ## " +
            this.getAchievements() + " ## " +
            java.util.Arrays.toString(this.getPublishers()) + " ## " +
            java.util.Arrays.toString(this.getDevelopers()) + " ## " +
            java.util.Arrays.toString(this.getCategories()) + " ## " +
            java.util.Arrays.toString(this.getGenres()) + " ## " +
            java.util.Arrays.toString(this.getTags()) + " ## ");
    }

    //Para comparar dois jogos e decidir qual tem maior prioridade ou qual deve vir primeiro no array ordenado.
    public static int compare(Games g1, Games g2) {
        comparacoes++; 

        if (g1.getEstimatedOwners() != g2.getEstimatedOwners()) {
            return g1.getEstimatedOwners() - g2.getEstimatedOwners();
        } else {
            return g1.getId() - g2.getId();
        }
    }

    //Para construir o heap a partir do array 
    public static void heapify(Games[] arr, int n, int i) {
        int raiz = i; 
        int esq = 2 * i + 1; 
        int dir = 2 * i + 2; 

        if (esq < n && compare(arr[esq], arr[raiz]) > 0) {
            raiz = esq;
        }

        if (dir < n && compare(arr[dir], arr[raiz]) > 0) {
            raiz = dir;
        }

        if (raiz != i) {
            swap(arr, i, raiz);

            heapify(arr, n, raiz);
        }
    }

    public static void heapsort(Games[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);

            heapify(arr, i, 0);
        }
    }

    public static void swap(Games[] arr, int i, int j) {
        Games temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        movimentacoes += 3; 
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        carregarCSV(arquivo);
        List<String> lines = Games.getcsv();

        Games[] games = new Games[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            games[i] = preencher(lines.get(i));
        }

        List<Games> selecionados = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("FIM")) {
            int idBuscado = Integer.parseInt(input);

            for(int i = 0; i < games.length; i++) {
                Games g = games[i];
                if (g.getId() == idBuscado) {
                    selecionados.add(g);
                }
            }

            input = scanner.nextLine();
        }

        // Converte a lista para um array para ordenar
        Games[] selecionadosArray = selecionados.toArray(new Games[0]);

        comparacoes = 0;
        movimentacoes = 0;
        
        long start = System.nanoTime();
        
        heapsort(selecionadosArray);
        
        long end = System.nanoTime();
        
        // Cálculo do tempo em milissegundos
        double tempo = (end - start) / 1e6;

        for(int i = 0; i < selecionadosArray.length; i++) { //Imprime os jogos ordenados
            selecionadosArray[i].imprimir();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))) {
            bw.write(String.format("%s\t%d\t%d\t%.2f\n", matricula, comparacoes, movimentacoes, tempo));
        }
        catch(Exception e) {}

        scanner.close();
    }
}
