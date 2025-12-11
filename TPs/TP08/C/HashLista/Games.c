#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_LINE 2000
#define MAX_GAMES 5000
#define TAM_TAB 21

typedef struct {
    int id;
    char name[200];
    char releaseDate[20];
    int estimatedOwners;
    float price;
    int metacriticScore;
    float userScore;
    int achievements;
} Game;

// Nó da lista 
typedef struct No {
    Game game;
    struct No* prox;
} No;

// Tabela Hash com lista encadeada
typedef struct {
    No* tabela[TAM_TAB];
    int comparacoes;
} HashIndireta;

void lerLinha(char* linha, Game* game);
int hash1(char* nome);
void inserir(HashIndireta* h, Game g);
int* buscar(HashIndireta* h, char* nome);
void inicializarHash(HashIndireta* h);
void cleanName(char *dest, char *src);

// Função hash
int hash1(char* nome) {
    int soma = 0;
    for (int i = 0; nome[i] != '\0'; i++) {
        soma += (unsigned char)nome[i];
    }
    return soma % TAM_TAB;
}

// Inicializa a tabela hash
void inicializarHash(HashIndireta* h) {
    for (int i = 0; i < TAM_TAB; i++) {
        h->tabela[i] = NULL;
    }
    h->comparacoes = 0;
}

void cleanName(char *dest, char *src) {
    int j = 0;
    for (int i = 0; src[i]; i++) {
        if (src[i] != '"') dest[j++] = src[i];
    }
    dest[j] = '\0';
    
    char *start = dest;
    while(*start && isspace((unsigned char)*start)) start++;
    if(start != dest) memmove(dest, start, strlen(start) + 1);
    
    int len = strlen(dest);
    while(len > 0 && isspace((unsigned char)dest[len-1])) dest[--len] = '\0';
}

// Insere na tabela hash
void inserir(HashIndireta* h, Game g) {
    int pos = hash1(g.name);

    No* novo = (No*)malloc(sizeof(No));
    novo->game = g;
    novo->prox = h->tabela[pos];

    h->tabela[pos] = novo;
}

// Busca na tabela hash
int* buscar(HashIndireta* h, char* nome) {
    int* resultado = (int*)malloc(2 * sizeof(int));
    int pos = hash1(nome);

    resultado[0] = pos;
    resultado[1] = 0;

    No* atual = h->tabela[pos];

    while (atual != NULL) {
        h->comparacoes++;
        if (strcmp(atual->game.name, nome) == 0) {
            resultado[1] = 1;
            atual = NULL;
        } else {
            atual = atual->prox;
        }
    }

    return resultado;
}

// Lê e parseia uma linha do CSV
void lerLinha(char* linha, Game* game) {
    char *campos[14]; 
    int idx = 0;
    int aspas = 0;
    char *ptr = linha;
    char *start = linha;

    game->id = 0;
    strcpy(game->name, "");

    while (*ptr) {
        if (*ptr == '"') {
            aspas = !aspas;
        } else if (*ptr == ',' && !aspas) {
            *ptr = '\0';
            campos[idx++] = strdup(start);
            start = ptr + 1;
        }
        ptr++;
    }
    campos[idx++] = strdup(start); //strdup do último campo
    
    char *nl = strchr(campos[idx-1], '\n'); //strchr para encontrar \n
    if (nl) *nl = '\0';

    if (idx >= 2) {
        game->id = atoi(campos[0]); //atoi do id
        cleanName(game->name, campos[1]);
    }

    for(int i=0; i<idx; i++) free(campos[i]);
}

int main() {
    Game games[MAX_GAMES];
    int numGames = 0;

    FILE* file = fopen("/tmp/games.csv", "r");
    if (file == NULL) {
        file = fopen("games.csv", "r");
        if (file == NULL) {
            return 1;
        }
    }

    char linha[MAX_LINE];
    fgets(linha, MAX_LINE, file);

    while (fgets(linha, MAX_LINE, file) != NULL && numGames < MAX_GAMES) {
        lerLinha(linha, &games[numGames]);
        if (games[numGames].id > 0) {
            numGames++;
        }
    }
    fclose(file);

    Game selecionados[MAX_GAMES];
    int numSelecionados = 0;

    char input[200];
    
    int continua = 1;
    while (continua && scanf("%s", input) == 1) {
        if (strcmp(input, "FIM") == 0) {
            continua = 0;
        } else {
            int idBuscado = atoi(input);
            
            for (int i = 0; i < numGames; i++) {
                if (games[i].id == idBuscado) {
                    selecionados[numSelecionados++] = games[i];
                    i = numGames;
                }
            }
        }
    }

    // Cria a tabela hash
    HashIndireta hash;
    inicializarHash(&hash);

    for (int i = 0; i < numSelecionados; i++) {
        inserir(&hash, selecionados[i]);
    }

    char nomesBusca[MAX_GAMES][200];
    int numBuscas = 0;
    
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
    
    continua = 1;
    while (continua && fgets(input, 200, stdin) != NULL) {
        input[strcspn(input, "\n")] = '\0';
        input[strcspn(input, "\r")] = '\0';
        
        if (strcmp(input, "FIM") == 0) {
            continua = 0;
        } else {
            strcpy(nomesBusca[numBuscas++], input);
        }
    }

    // Realiza buscas e imprime resultados
    for (int i = 0; i < numBuscas; i++) {
        int* resultado = buscar(&hash, nomesBusca[i]);
        int pos = resultado[0];
        bool encontrado = resultado[1];

        printf("%s: (Posicao: %d) %s\n", nomesBusca[i], pos, encontrado ? "SIM" : "NAO");

        free(resultado);
    }

    FILE* log = fopen("874205_hashIndireta.txt", "w");
    if (log != NULL) {
        fprintf(log, "874205\t%d", hash.comparacoes);
        fclose(log);
    }

    for (int i = 0; i < TAM_TAB; i++) {
        No* atual = hash.tabela[i];
        while (atual != NULL) {
            No* tmp = atual;
            atual = atual->prox;
            free(tmp);
        }
    }

    return 0;
}