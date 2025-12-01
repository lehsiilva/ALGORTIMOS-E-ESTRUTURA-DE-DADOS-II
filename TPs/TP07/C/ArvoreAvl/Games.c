#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <locale.h>

#define MAX_NAME 500
#define MAX_LINE 2000
#define LOG_FILE "874205_arvoreBinaria.txt"
#define CSV_FILE "/tmp/games.csv"
#define MATRICULA 874205

long comparacoes = 0;
long movimentacoes = 0;
double tempoEmSegundos = 0.0;

typedef struct {
    int id;
    char name[MAX_NAME];
} Game;

typedef struct AVLNo {
    Game game;
    struct AVLNo *esquerda;
    struct AVLNo *direita;
    int inicial;
} AVLNo;

int max(int a, int b) {
    return (a > b) ? a : b;
}

int inicial(AVLNo *no) {
    return no ? no->inicial : 0;
}

AVLNo* criarNo(Game game) {
    AVLNo* no = (AVLNo*)malloc(sizeof(AVLNo));
    no->game = game;
    no->esquerda = no->direita = NULL;
    no->inicial = 1; 
    return no;
}

AVLNo* rotacaoDireita(AVLNo* y) {
    AVLNo* x = y->esquerda;
    AVLNo* T2 = x->direita;
    x->direita = y;
    y->esquerda = T2;
    y->inicial = max(inicial(y->esquerda), inicial(y->direita)) + 1;
    x->inicial = max(inicial(x->esquerda), inicial(x->direita)) + 1;
    movimentacoes += 2;
    return x;
}

AVLNo* rotacaoEsquerda(AVLNo* x) {
    AVLNo* y = x->direita;
    AVLNo* T2 = y->esquerda;
    y->esquerda = x;
    x->direita = T2;
    x->inicial = max(inicial(x->esquerda), inicial(x->direita)) + 1;
    y->inicial = max(inicial(y->esquerda), inicial(y->direita)) + 1;
    movimentacoes += 2;
    return y;
}

int getBalance(AVLNo* no) {
    return no ? inicial(no->esquerda) - inicial(no->direita) : 0;
}

AVLNo* inserir(AVLNo* no, Game game) {
    if (!no) return criarNo(game);

    comparacoes++;
    int cmp = strcmp(game.name, no->game.name);
    
    if (cmp < 0)
        no->esquerda = inserir(no->esquerda, game);
    else if (cmp > 0)
        no->direita = inserir(no->direita, game);
    else
        return no;

    no->inicial = 1 + max(inicial(no->esquerda), inicial(no->direita));
    int balance = getBalance(no);

    if (balance > 1 && strcmp(game.name, no->esquerda->game.name) < 0) //strcmp para comparar strings
        return rotacaoDireita(no);

    if (balance < -1 && strcmp(game.name, no->direita->game.name) > 0)
        return rotacaoEsquerda(no);

    if (balance > 1 && strcmp(game.name, no->esquerda->game.name) > 0) {
        no->esquerda = rotacaoEsquerda(no->esquerda);
        return rotacaoDireita(no);
    }

    if (balance < -1 && strcmp(game.name, no->direita->game.name) < 0) {
        no->direita = rotacaoDireita(no->direita);
        return rotacaoEsquerda(no);
    }

    return no;
}

void pesquisarAVL(AVLNo* no, const char* name, char* path) {
    if (!no) {
        printf("%s: %s NAO\n", name, path);
        return;
    }

    comparacoes++;
    int cmp = strcmp(name, no->game.name);

    if (cmp == 0) {
        printf("%s: %s SIM\n", name, path);
        return;
    }

    char newPath[MAX_LINE];
    
    if (cmp < 0) {
        snprintf(newPath, sizeof(newPath), "%s esq", path);
        pesquisarAVL(no->esquerda, name, newPath);
    } else {
        snprintf(newPath, sizeof(newPath), "%s dir", path);
        pesquisarAVL(no->direita, name, newPath);
    }
}

// Função para carregar o CSV
int carregarCSV(const char* arquivo, Game* games) {
    FILE* fp = fopen(arquivo, "r");
    if (!fp) return 0;

    char line[MAX_LINE];
    int count = 0;

    fgets(line, MAX_LINE, fp); // header

    while (fgets(line, MAX_LINE, fp) && count < 10000) {
        line[strcspn(line, "\r\n")] = '\0';
        
        if (strlen(line) >= 3) {
            // Parsing: ID,Name
            char *p = line;
            char idStr[20];
            int i = 0;
            
            // Lê ID
            while (*p && *p != ',' && i < 19) {
                idStr[i++] = *p++;
            }
            idStr[i] = '\0';
            int id = atoi(idStr);
            
            if (*p == ',') p++;
            
            // Lê Name
            i = 0;
            int aspas = 0;
            
            while (*p == ' ') p++; // trim
            
            if (*p == '"') {
                aspas = 1;
                p++;
            }
            
            char name[MAX_NAME];
            while (*p && i < MAX_NAME - 1) {
                if (aspas && *p == '"') {
                    if (*(p+1) == '"') {
                        name[i++] = '"';
                        p += 2;
                    } else {
                        *p = '\0'; // Força saída do loop
                    }
                } else if (!aspas && (*p == ',' || *p == '\r' || *p == '\n')) {
                    *p = '\0'; // Força saída do loop
                } else {
                    name[i++] = *p++;
                }
            }
            
            while (i > 0 && name[i-1] == ' ') i--;
            name[i] = '\0';
            
            if (id > 0 && strlen(name) > 0) {
                games[count].id = id;
                strcpy(games[count].name, name);
                count++;
            }
        }
    }

    fclose(fp);
    return count;
}

void liberarArvore(AVLNo* no) {
    if (!no) return;
    liberarArvore(no->esquerda);
    liberarArvore(no->direita);
    free(no);
}

int isFIM(const char* str) {
    return (str[0] == 'F' && str[1] == 'I' && str[2] == 'M' && str[3] == '\0');
}

int main() {
    setlocale(LC_ALL, "");
    setlocale(LC_CTYPE, "UTF-8");
    
    clock_t start = clock();

    Game* games = (Game*)malloc(10000 * sizeof(Game));
    int totalGames = carregarCSV(CSV_FILE, games);

    AVLNo* arvore = NULL;

    char input[MAX_LINE];
    int continuarLendo = 1;
    
    // Lê IDs e insere na árvore
    while (continuarLendo && fgets(input, sizeof(input), stdin)) {
        input[strcspn(input, "\r\n")] = '\0';
        
        if (isFIM(input)) {
            continuarLendo = 0;
        } else {
            int id = atoi(input); // atoi para converter string para inteiro
            if (id > 0) {
                int encontrado = 0;
                for (int i = 0; i < totalGames && !encontrado; i++) {
                    if (games[i].id == id) {
                        arvore = inserir(arvore, games[i]);
                        encontrado = 1;
                    }
                }
            }
        }
    }

    //Lê nomes e pesquisa
    continuarLendo = 1;
    while (continuarLendo && fgets(input, sizeof(input), stdin)) {
        input[strcspn(input, "\r\n")] = '\0';//strcspn para remover newline
        
        if (isFIM(input)) {
            continuarLendo = 0;
        } else {
            char path[MAX_LINE] = "raiz";
            pesquisarAVL(arvore, input, path);
        }
    }

    tempoEmSegundos = (double)(clock() - start) / CLOCKS_PER_SEC;

    FILE* log = fopen(LOG_FILE, "w");
    if (log) {
        fprintf(log, "%d\t%%ld\t%ld\t%.2f\n", MATRICULA, comparacoes, movimentacoes, tempoEmSegundos);
        fclose(log);
    }

    liberarArvore(arvore);
    free(games);
    return 0;
}