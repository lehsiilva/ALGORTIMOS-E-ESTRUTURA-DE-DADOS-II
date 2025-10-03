import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Games{
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
        
        public Games(){ //Construtor
            this.id = 0;
            this.name = "Unknown";
            this.releaseDate = "01/01/1900" ;
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

        //SETS
        public void setId(int arqId){
            this.id = arqId; 
        }
        public void setName(String name) {
            if(!(name == null || name.isEmpty())){
                this.name = name.trim(); //TRIM: remove os espaços no inicio e fim.
            }else{
                this.name = "Unknown";
            }
        }
         public void setReleaseDate(String arqDate) {
            if (arqDate == null || arqDate.isEmpty()) {
                this.releaseDate = "01/01/1900";
                return;
            }

            arqDate = arqDate.replace("\"", "").trim(); // remove aspas

            // Tenta detectar formato textual "MMM dd, yyyy"
            String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

            try {
                String[] parts = arqDate.split(" "); // separa por espaço
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
            } catch (Exception e) { //
                // fallback para formato já numérico ou inválido
                
            }

            // fallback para formatos numéricos separados por "/"
            String[] parts = arqDate.split("/");
            if (parts.length == 1) { //
                this.releaseDate = "01/01/" + parts[0];
            } else if (parts.length == 2) { 
                String mes = String.format("%02d", Integer.parseInt(parts[0])); //
                this.releaseDate = "01/" + mes + "/" + parts[1];
            } else if (parts.length == 3) { 
                String dia = String.format("%02d", Integer.parseInt(parts[0])); //
                String mes = String.format("%02d", Integer.parseInt(parts[1])); //
                this.releaseDate = dia + "/" + mes + "/" + parts[2];
            } else {
                this.releaseDate = "01/01/1900";
            }
        }
         public void setEstimatedOwners(String arqOwners) {
            if (arqOwners == null || arqOwners.isEmpty()) {
                this.estimatedOwners = 0;
                return; //
            }else{
            // remove tudo que não for número
            arqOwners = arqOwners.replaceAll("[^0-9]", ""); //REPLACEALL: substitui todas as ocorrências de um padrão, tudo q n for digito substitui por nada.
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
                this.categories = new String[0];
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
    

    static List<String> csv = new ArrayList<>();

    // Método para preencher a lista csv com os dados do arquivo CSV
    public static void preencher(String arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = br.readLine()) != null) csv.add(line);
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    public static String[] splitCSV(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(sb.toString().trim());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        result.add(sb.toString().trim());
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //

        // Lê CSV
        preencher("games.csv");
        List<String> data = csv.subList(1, csv.size()); // ignora header
        Games[] games = new Games[data.size()];

        for (int i = 0; i < data.size(); i++) {
            games[i] = new Games();
            String[] f = splitCSV(data.get(i)); // separador CSV
            games[i].setId(Integer.parseInt(f[0]));
            games[i].setName(f[1]);
            games[i].setReleaseDate(f[2]);
            games[i].setEstimatedOwners(f[3]);
            games[i].setPrice(f[4]);
            games[i].setSupportedLanguages(f[5]);
            games[i].setMetacriticScore(f[6]);
            games[i].setUserScore(f[7]);
            games[i].setAchievements(f[8]);
            games[i].setPublishers(f[9]);
            games[i].setDevelopers(f[10]);
            games[i].setCategories(f[11]);
            games[i].setGenres(f[12]);
            games[i].setTags(f[13]);
        }

        String line = scanner.nextLine();

        while (!line.equals("FIM")) {

            int id = Integer.parseInt(line);
            for (Games g : games) {
                if (g.getId() == id) {
                    System.out.println("=> " + g.getId() + " ## " + g.getName() + " ## " + g.getReleaseDate() + " ## " +
                            g.getEstimatedOwners() + " ## " + g.getPrice() + " ## " +
                            java.util.Arrays.toString(g.getSupportedLanguages()) + " ## " +
                            g.getMetacriticScore() + " ## " + g.getUserScore() + " ## " +
                            g.getAchievements() + " ## " +
                            java.util.Arrays.toString(g.getPublishers()) + " ## " +
                            java.util.Arrays.toString(g.getDevelopers()) + " ## " +
                            java.util.Arrays.toString(g.getCategories()) + " ## " +
                            java.util.Arrays.toString(g.getGenres()) + " ## " +
                            java.util.Arrays.toString(g.getTags()) + " ## ");
                }
            }

            line = scanner.nextLine();
        }

        scanner.close();
    }
}



/*PERGUNTAS: ENTENDER O METODO DAS DATAS E DAS LINGUAGENS(PRINCIPALMENTE O PQ O [] NAO SAIU PQ NAO SAO DUPLOS, A MAIN OS METODOS IMPORTADS SPRIT, PREENCHER) */