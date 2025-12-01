import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

class Game {
    int id;
    String name;
    
    public Game(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class No {
    Game game;
    No esq, dir;
    boolean cor; 
    
    public No(Game game) {
        this.game = game;
        this.esq = this.dir = null;
        this.cor = true; 
    }
}

public class ArvoreAlvinegra {
    private No raiz;
    private static long comparacoes = 0;
    private static long movimentacoes = 0;
    private static final String LOG_FILE = "874205_arvoreAlvinegra.txt";
    private static final String CSV_FILE = "/tmp/games.csv";
    private static final int MATRICULA = 874205;
    
    public ArvoreAlvinegra() {
        raiz = null;
    }
    
    // Comparação de strings
    private int compararStrings(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int minLen = (len1 < len2) ? len1 : len2;
        
        for (int i = 0; i < minLen; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return c1 - c2;
            }
        }
        return len1 - len2;
    }
    
    // Comparação entre Games
    private int compare(Game a, Game b) {
        return compararStrings(a.name, b.name);
    }
    
    private int compare(String nome, Game a) {
        return compararStrings(nome, a.name);
    }
    
    // Inserção na árvore
    public void inserir(Game elemento) {
        if (raiz == null) {
            raiz = new No(elemento);
        } else if (raiz.dir == null && raiz.esq == null) {
            if (compare(elemento, raiz.game) < 0) { 
                raiz.esq = new No(elemento);
            } else {
                raiz.dir = new No(elemento);
            }
        } else if (raiz.esq == null) {
            if (compare(elemento, raiz.game) < 0) {
                raiz.esq = new No(elemento);
            } else if (compare(elemento, raiz.dir.game) < 0) {
                raiz.esq = new No(raiz.game);
                raiz.game = elemento;
            } else {
                raiz.esq = new No(raiz.game);
                raiz.game = raiz.dir.game;
                raiz.dir.game = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        } else if (raiz.dir == null) {
            if (compare(elemento, raiz.game) > 0) {
                raiz.dir = new No(elemento);
            } else if (compare(elemento, raiz.esq.game) > 0) {
                raiz.dir = new No(raiz.game);
                raiz.game = elemento;
            } else {
                raiz.dir = new No(raiz.game);
                raiz.game = raiz.esq.game;
                raiz.esq.game = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }
    
    private void inserir(Game elemento, No bisAvo, No avo, No pai, No i) {
        if (i == null) {
            if (compare(elemento, pai.game) < 0) {
                i = pai.esq = new No(elemento);
            } else {
                i = pai.dir = new No(elemento);
            }
            
            if (pai.cor == true) {
                balancear(bisAvo, avo, pai, i);
            }
        } else {
            quatroNo(bisAvo, avo, pai, i);
            
            if (compare(elemento, i.game) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (compare(elemento, i.game) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            }
        }
    }
    
    // Verifica se é um 4-nó
    private void quatroNo(No bisAvo, No avo, No pai, No i) {
        if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            
            if (i == raiz) {
                i.cor = false;
            } else if (pai.cor == true) {
                balancear(bisAvo, avo, pai, i);
            }
        }
    }
    
    // Balanceamento
    private void balancear(No bisAvo, No avo, No pai, No i) {
        if (pai.cor == true) {
            No novoAvo;
            
            if (compare(pai.game, avo.game) > 0) {
                if (compare(i.game, pai.game) > 0) {
                    novoAvo = rotacaoEsq(avo);
                } else {
                    avo.dir = rotacaoDir(pai);
                    novoAvo = rotacaoEsq(avo);
                }
            } else {
                if (compare(i.game, pai.game) < 0) {
                    novoAvo = rotacaoDir(avo);
                } else {
                    avo.esq = rotacaoEsq(pai);
                    novoAvo = rotacaoDir(avo);
                }
            }
            
            if (bisAvo == null) {
                raiz = novoAvo;
            } else if (compare(novoAvo.game, bisAvo.game) < 0) {
                bisAvo.esq = novoAvo;
            } else {
                bisAvo.dir = novoAvo;
            }
            
            novoAvo.cor = false;
            novoAvo.esq.cor = novoAvo.dir.cor = true;
        }
    }
    
    // Rotações
    private No rotacaoDir(No i) {
        No tmp = i.esq;
        i.esq = tmp.dir;
        tmp.dir = i;
        return tmp;
    }
    
    private No rotacaoEsq(No i) {
        No tmp = i.dir;
        i.dir = tmp.esq;
        tmp.esq = i;
        return tmp;
    }
    
    // Pesquisa
    public void pesquisar(String name) {
        System.out.print(name + ": =>raiz");
        boolean encontrou = pesquisar(raiz, name);
        if (encontrou) {
            System.out.println(" SIM");
        } else {
            System.out.println(" NAO");
        }
    }
    
    private boolean pesquisar(No i, String nome) {
        if (i == null) {
            return false;
        } else if (compare(nome, i.game) > 0) {
            System.out.print(" dir");
            return pesquisar(i.dir, nome);
        } else if (compare(nome, i.game) < 0) {
            System.out.print(" esq");
            return pesquisar(i.esq, nome);
        } else {
            return true;
        }
    }
    
    // Carregar CSV
    private static Game[] carregarCSV(String arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        Game[] games = new Game[50000];
        int count = 0;
        
        br.readLine();
        
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] campos = parsearLinha(linha);
            
            if (campos.length >= 2) {
                try {
                    int id = Integer.parseInt(campos[0].trim());
                    String name = campos[1].trim();
                    
                    games[count] = new Game(id, name);
                    count++;
                } catch (NumberFormatException e) {
                }
            }
        }
        
        br.close();
        
        Game[] resultado = new Game[count];
        for (int i = 0; i < count; i++) {
            resultado[i] = games[i];
        }
        
        return resultado;
    }
    
    // Parser de linha CSV
    private static String[] parsearLinha(String linha) {
        String[] resultado = new String[50];
        int indice = 0;
        boolean dentroAspas = false;
        StringBuilder campo = new StringBuilder();
        
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            
            if (c == '"') {
                dentroAspas = !dentroAspas;
            } else if (c == ',' && !dentroAspas) {
                resultado[indice++] = campo.toString();
                campo = new StringBuilder();
            } else {
                campo.append(c);
            }
        }
        
        resultado[indice++] = campo.toString();
        
        String[] retorno = new String[indice];
        for (int i = 0; i < indice; i++) {
            retorno[i] = resultado[i];
        }
        
        return retorno;
    }
    
    private static void gerarLog(long tempo) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE));
        DecimalFormat df = new DecimalFormat("0.00");
        double tempoSeg = tempo / 1000000000.0;
        pw.println(MATRICULA + "\t" + comparacoes + "\t" + movimentacoes + "\t" + df.format(tempoSeg));
        pw.close();
    }
    
    private static boolean isFIM(String s) {
        if (s.length() != 3) {
            return false;
        }
        return s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M';
    }
    
    public static void main(String[] args) {
        long inicio = System.nanoTime();
        
        try {
            Game[] games = carregarCSV(CSV_FILE);
            
            // Construção da árvore
            ArvoreAlvinegra arvore = new ArvoreAlvinegra();
            Scanner sc = new Scanner(System.in);
            
            String linha = sc.nextLine().trim();
            while (!isFIM(linha)) {
                try {
                    int id = Integer.parseInt(linha);
                    
                    int i = 0;
                    boolean achou = false;
                    while (i < games.length && !achou) {
                        if (games[i].id == id) {
                            arvore.inserir(games[i]);
                            achou = true;
                        }
                        i++;
                    }
                } catch (NumberFormatException e) {
                }
                linha = sc.nextLine().trim();
            }
            
            linha = sc.nextLine().trim();
            while (!isFIM(linha)) {
                arvore.pesquisar(linha);
                linha = sc.nextLine().trim();
            }
            
            sc.close();
            
            long fim = System.nanoTime();
            gerarLog(fim - inicio);
            
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}