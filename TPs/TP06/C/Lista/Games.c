#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_GAMES 10000
#define MAX_STR 64
#define MAX_LINE 8192
#define MAX_ARRAY_ITEM 200
#define CSV_PATH "/tmp/games.csv"

typedef struct
{
    int id;
    char name[MAX_STR];
    char releaseDate[MAX_STR];
    int estimatedOwners;
    float price;
    char supportedLanguages[MAX_ARRAY_ITEM][MAX_STR];
    int supportedLanguagesCount;
    int metacriticScore;
    float userScore;
    int achievements;
    char publishers[MAX_ARRAY_ITEM][MAX_STR];
    int publishersCount;
    char developers[MAX_ARRAY_ITEM][MAX_STR];
    int developersCount;
    char categories[MAX_ARRAY_ITEM][MAX_STR];
    int categoriesCount;
    char genres[MAX_ARRAY_ITEM][MAX_STR];
    int genresCount;
    char tags[MAX_ARRAY_ITEM][MAX_STR];
    int tagsCount;
} Games;

// função para remover espaços em branco do início e do fim
void trim(char *s)
{
    if (!s || *s == '\0')
        return;
    char *start = s;
    while (*start && isspace((unsigned char)*start)) //isspace para verificar espaços em branco, using char para evitar problemas com sinais negativos
        start++;
    if (start != s)
        memmove(s, start, strlen(start) + 1); //mmemove para mover a memória
    int len = strlen(s);
    while (len > 0 && isspace((unsigned char)s[len - 1]))
        s[--len] = '\0';
}

//função para remover caracteres específicos de uma string [], '
void string_replace_chars(char *s)
{
    if (!s || *s == '\0')
        return;
    char *read_ptr = s;
    char *write_ptr = s;
    while (*read_ptr)
    {
        if (*read_ptr != '[' && *read_ptr != ']' && *read_ptr != '\'')
        {
            *write_ptr = *read_ptr;
            write_ptr++;
        }
        read_ptr++;
    }
    *write_ptr = '\0';
}


void parseDate(const char *arqDate, char *releaseDate)
{
    if (arqDate == NULL || strlen(arqDate) == 0)
    {
        strcpy(releaseDate, " ");
        return;
    }
    char dateCopy[256];
    strncpy(dateCopy, arqDate, sizeof(dateCopy) - 1); //strncpy para copiar a string
    dateCopy[sizeof(dateCopy) - 1] = '\0';

    char cleanedDate[256];
    int j = 0;
    for (int i = 0; dateCopy[i] != '\0' && j < (int)sizeof(cleanedDate) - 1; i++)
    {
        if (dateCopy[i] != '"')
            cleanedDate[j++] = dateCopy[i];
    }
    cleanedDate[j] = '\0';
    trim(cleanedDate);

    const char *months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    char monthStr[16];
    int day = 0, month = 0, year = 0;

    if (sscanf(cleanedDate, "%s %d, %d", monthStr, &day, &year) == 3)
    {
        for (int i = 0; i < 12; i++)
        {
            if (strncmp(monthStr, months[i], 3) == 0)
            {
                month = i + 1;
            }
        }
        if (month > 0 && year > 0)
        {
            sprintf(releaseDate, "%02d/%02d/%04d", day, month, year);
            return;
        }
    }

    if (sscanf(cleanedDate, "%s %d", monthStr, &year) == 2)
    {
        for (int i = 0; i < 12; i++)
        {
            if (strncmp(monthStr, months[i], 3) == 0)
            {
                month = i + 1;
            }
        }
        if (month > 0 && year > 0)
        {
            sprintf(releaseDate, "01/%02d/%04d", month, year);
            return;
        }
    }

    if (sscanf(cleanedDate, "%d", &year) == 1 && year >= 1900)
    {
        sprintf(releaseDate, "01/01/%04d", year);
        return;
    }

    strcpy(releaseDate, " ");
}


int parseArrayField(const char *s, char array[][MAX_STR], int max)
{
    char temp[MAX_LINE];
    int count = 0;
    if (!s || strlen(s) == 0)
        return 0;

    strncpy(temp, s, sizeof(temp) - 1);
    temp[sizeof(temp) - 1] = '\0';
    trim(temp);
    string_replace_chars(temp);
    trim(temp);

    char *tok = strtok(temp, ",");
    while (tok != NULL && count < max)
    {
        trim(tok);
        size_t len = strlen(tok); //size_t tipo de variável para tamanhos, strlen para obter o tamanho da string
        if (len > 0 && tok[0] == '"')
            memmove(tok, tok + 1, len);
        len = strlen(tok);
        if (len > 0 && tok[len - 1] == '"')
            tok[len - 1] = '\0';
        trim(tok);
        if (strlen(tok) > 0)
        {
            strncpy(array[count], tok, MAX_STR - 1);
            array[count][MAX_STR - 1] = '\0';
            count++;
        }
        tok = strtok(NULL, ","); //strtok para separar a string em tokens
    }
    return count;
}

// função para ler um registro CSV
int ler_registro_csv(FILE *fp, char *buffer, size_t bufsize)
{
    buffer[0] = '\0';
    char tmp[MAX_LINE];

    bool leu = fgets(tmp, sizeof(tmp), fp) != NULL;
    if (!leu)
        return 0;
    strncpy(buffer, tmp, bufsize - 1);
    buffer[bufsize - 1] = '\0';

    int contAspas = 0;  // conta aspas para verificar se o registro está completo (par = completo)
    for (char *p = buffer; *p; p++)
        if (*p == '"')
            contAspas++;

    bool aspasImpares = (contAspas % 2) != 0;
    bool continuar = aspasImpares && leu;

    while (continuar)
    {
        leu = fgets(tmp, sizeof(tmp), fp) != NULL;
        if (leu)
        {
            size_t remaining = bufsize - strlen(buffer) - 1;
            if (remaining > 0)
                strncat(buffer, tmp, remaining);
            for (char *p = tmp; *p; p++)
                if (*p == '"')
                    contAspas++;
        }
        // Atualiza condição para continuar: aspas ainda ímpares e leu algo
        aspasImpares = (contAspas % 2) != 0;
        continuar = aspasImpares && leu;
    }

    // Remove \n ou \r do final
    size_t len = strlen(buffer);
    while (len > 0 && (buffer[len - 1] == '\n' || buffer[len - 1] == '\r'))
        buffer[--len] = '\0';

    return (buffer[0] != '\0');
}


// função para ler uma linha CSV em campos
int readCSVLine(char *line, char fields[][MAX_LINE], int maxFields)
{
    int fieldIndex = 0;
    bool inQuotes = false;
    char *start = line;
    char *p = line;
    size_t len = strlen(line);

    if (len > 0 && line[len - 1] == '\n')
        line[--len] = '\0';
    if (len > 0 && line[len - 1] == '\r')
        line[--len] = '\0';

    while (*p && fieldIndex < maxFields)
    {
        if (*p == '"')
        {
            inQuotes = !inQuotes;
            p++;
        }
        else if (*p == ',' && !inQuotes)
        {
            int fieldLength = p - start;
            if (fieldLength > MAX_LINE - 1)
                fieldLength = MAX_LINE - 1;
            strncpy(fields[fieldIndex], start, fieldLength);
            fields[fieldIndex][fieldLength] = '\0';
            trim(fields[fieldIndex]);
            fieldIndex++;
            start = p + 1;
            p++;
        }
        else
        {
            p++;
        }
    }

    if (fieldIndex < maxFields)
    {
        int fieldLength = strlen(start);
        if (fieldLength > MAX_LINE - 1)
            fieldLength = MAX_LINE - 1;
        strncpy(fields[fieldIndex], start, fieldLength);
        fields[fieldIndex][fieldLength] = '\0';
        trim(fields[fieldIndex]);
        fieldIndex++;
    }

    return fieldIndex;
}

// função para criar um objeto Games a partir dos campos lidos
Games Games_create(char fields[][MAX_LINE], int numFields)
{
    Games g;
    memset(&g, 0, sizeof(Games));
    g.metacriticScore = -1;
    g.userScore = -1.0f;
    strcpy(g.releaseDate, " ");
    //ID
    if (numFields < 14)
        return g;

    char cleanId[MAX_STR];
    int k = 0;
    for (int i = 0; fields[0][i] != '\0' && k < MAX_STR - 1; i++)
    {
        if (isdigit((unsigned char)fields[0][i])) //isdigit para verificar se é dígito
            cleanId[k++] = fields[0][i];
    }
    cleanId[k] = '\0';
    g.id = k > 0 ? atoi(cleanId) : 0; //atoi para converter string para int

    strncpy(g.name, fields[1], MAX_STR - 1);
    g.name[MAX_STR - 1] = '\0';

    parseDate(fields[2], g.releaseDate);

    char cleanOwners[MAX_STR];
    int j = 0;
    for (int i = 0; fields[3][i] != '\0'; i++)
    {
        if (isdigit((unsigned char)fields[3][i]))
            cleanOwners[j++] = fields[3][i];
    }
    cleanOwners[j] = '\0';
    g.estimatedOwners = j > 0 ? atoi(cleanOwners) : 0;

    if (strcasecmp(fields[4], "Free to Play") == 0)
    {
        g.price = 0.0f;
    }
    else
    {
        char priceTemp[MAX_STR];
        strncpy(priceTemp, fields[4], MAX_STR - 1);
        for (char *c = priceTemp; *c; c++)
            if (*c == ',')
                *c = '.';
        g.price = strtof(priceTemp, NULL);
    }

    g.supportedLanguagesCount = parseArrayField(fields[5], g.supportedLanguages, MAX_ARRAY_ITEM);

    if (strlen(fields[6]) == 0)
        g.metacriticScore = -1;
    else
        g.metacriticScore = atoi(fields[6]);

    char scoreTemp[MAX_STR];
    strncpy(scoreTemp, fields[7], MAX_STR - 1);
    trim(scoreTemp);
    if (strlen(scoreTemp) == 0 || strcmp(scoreTemp, "tbd") == 0)
    {
        g.userScore = -1.0f;
    }
    else
    {
        for (char *c = scoreTemp; *c; c++)
            if (*c == ',')
                *c = '.';
        g.userScore = strtof(scoreTemp, NULL);
    }

    if (strlen(fields[8]) == 0)
        g.achievements = 0;
    else
        g.achievements = atoi(fields[8]);

    g.publishersCount = parseArrayField(fields[9], g.publishers, MAX_ARRAY_ITEM);
    g.developersCount = parseArrayField(fields[10], g.developers, MAX_ARRAY_ITEM);
    g.categoriesCount = parseArrayField(fields[11], g.categories, MAX_ARRAY_ITEM);
    g.genresCount = parseArrayField(fields[12], g.genres, MAX_ARRAY_ITEM);
    g.tagsCount = parseArrayField(fields[13], g.tags, MAX_ARRAY_ITEM);

    return g;
}


void Games_imprimir(const Games *g)
{
    printf("=> %d ## %s ## %s ## %d ## %.2f ## [",
           g->id, g->name, g->releaseDate, g->estimatedOwners, g->price);

    for (int j = 0; j < g->supportedLanguagesCount; j++)
    {
        printf("%s%s", g->supportedLanguages[j], (j < g->supportedLanguagesCount - 1) ? ", " : "");
    }
    printf("] ## %d ## %.1f ## %d ## [", g->metacriticScore, g->userScore, g->achievements);

    for (int j = 0; j < g->publishersCount; j++)
    {
        printf("%s%s", g->publishers[j], (j < g->publishersCount - 1) ? ", " : "");
    }
    printf("] ## [");
    for (int j = 0; j < g->developersCount; j++)
    {
        printf("%s%s", g->developers[j], (j < g->developersCount - 1) ? ", " : "");
    }
    printf("] ## [");
    for (int j = 0; j < g->categoriesCount; j++)
    {
        printf("%s%s", g->categories[j], (j < g->categoriesCount - 1) ? ", " : "");
    }
    printf("] ## [");
    for (int j = 0; j < g->genresCount; j++)
    {
        printf("%s%s", g->genres[j], (j < g->genresCount - 1) ? ", " : "");
    }
    printf("] ## [");
    for (int j = 0; j < g->tagsCount; j++)
    {
        printf("%s%s", g->tags[j], (j < g->tagsCount - 1) ? ", " : "");
    }
    printf("] ##\n");
}

// função para buscar um jogo pelo ID
Games *buscar_jogo_por_id(Games *all_games, int total_games, int id)
{
    for (int i = 0; i < total_games; i++)
    {
        if (all_games[i].id == id)
            return &all_games[i];
    }
    return NULL;
}

//Lista
typedef struct Celula
{
    Games *game;
    struct Celula *prox;
} Celula;

typedef struct
{
    Celula *primeiro;
    Celula *ultimo;
    int tamanho;
} ListaGames;

void inicializarLista(ListaGames *lista)
{
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
}

Celula *novaCelula(Games *g)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    if (!nova)
    {
        fprintf(stderr, "Erro ao alocar célula.\n");
        exit(1);
    }
    nova->game = g;
    nova->prox = NULL;
    return nova;
}

void inserirInicio(ListaGames *lista, Games *g)
{
    Celula *nova = novaCelula(g);
    nova->prox = lista->primeiro;
    lista->primeiro = nova;
    if (lista->ultimo == NULL)
        lista->ultimo = nova;
    lista->tamanho++;
}

void inserirFim(ListaGames *lista, Games *g)
{
    Celula *nova = novaCelula(g);
    if (lista->ultimo == NULL)
    {
        lista->primeiro = lista->ultimo = nova;
    }
    else
    {
        lista->ultimo->prox = nova;
        lista->ultimo = nova;
    }
    lista->tamanho++;
}

void inserirPos(ListaGames *lista, Games *g, int pos)
{
    if (pos < 0 || pos > lista->tamanho)
    {
        fprintf(stderr, "Erro: posição inválida.\n");
        return;
    }
    if (pos == 0)
    {
        inserirInicio(lista, g);
        return;
    }
    if (pos == lista->tamanho)
    {
        inserirFim(lista, g);
        return;
    }
    Celula *atual = lista->primeiro;
    for (int i = 0; i < pos - 1; i++)
        atual = atual->prox;
    Celula *nova = novaCelula(g);
    nova->prox = atual->prox;
    atual->prox = nova;
    lista->tamanho++;
}

Games *removerInicio(ListaGames *lista)
{
    if (lista->primeiro == NULL)
    {
        fprintf(stderr, "Erro: lista vazia.\n");
        return NULL;
    }
    Celula *rem = lista->primeiro;
    Games *g = rem->game;
    lista->primeiro = rem->prox;
    if (lista->primeiro == NULL)
        lista->ultimo = NULL;
    free(rem);
    lista->tamanho--;
    return g;
}

Games *removerFim(ListaGames *lista)
{
    if (lista->primeiro == NULL)
    {
        fprintf(stderr, "Erro: lista vazia.\n");
        return NULL;
    }
    if (lista->primeiro == lista->ultimo)
    {
        Games *g = lista->primeiro->game;
        free(lista->primeiro);
        lista->primeiro = lista->ultimo = NULL;
        lista->tamanho--;
        return g;
    }
    Celula *atual = lista->primeiro;
    while (atual->prox != lista->ultimo)
        atual = atual->prox;
    Games *g = lista->ultimo->game;
    free(lista->ultimo);
    lista->ultimo = atual;
    atual->prox = NULL;
    lista->tamanho--;
    return g;
}

Games *removerPos(ListaGames *lista, int pos)
{
    if (pos < 0 || pos >= lista->tamanho)
    {
        fprintf(stderr, "Erro: posição inválida.\n");
        return NULL;
    }
    if (pos == 0)
        return removerInicio(lista);
    if (pos == lista->tamanho - 1)
        return removerFim(lista);
    Celula *atual = lista->primeiro;
    for (int i = 0; i < pos - 1; i++)
        atual = atual->prox;
    Celula *rem = atual->prox;
    Games *g = rem->game;
    atual->prox = rem->prox;
    free(rem);
    lista->tamanho--;
    return g;
}

void mostrarLista(ListaGames *lista)
{
    Celula *atual = lista->primeiro;
    int index = 0;
    while (atual != NULL)
    {
        printf("[%d] ", index++);
        Games_imprimir(atual->game);
        atual = atual->prox;
    }
}


int main()
{
    FILE *fp = fopen(CSV_PATH, "r");
    if (fp == NULL)
    {
        fprintf(stderr, "Erro ao abrir arquivo %s\n", CSV_PATH);
        return 1;
    }

    // Alocação inicial para todos os jogos
    int all_capacity = 1024;
    Games *all_games = (Games *)malloc(all_capacity * sizeof(Games));
    if (all_games == NULL)
    {
        fprintf(stderr, "Erro: Falha ao alocar memoria para os jogos.\n");
        fclose(fp);
        return 1;
    }

    int total_games = 0;
    char line[MAX_LINE];
    char fields[14][MAX_LINE];

    // pula cabeçalho
    if (!ler_registro_csv(fp, line, sizeof(line)))
    {
        fclose(fp);
        free(all_games);
        return 1;
    }

    // leitura de todos os registros do CSV
    while (ler_registro_csv(fp, line, sizeof(line)))
    {
        int numFields = readCSVLine(line, fields, 14);
        if (numFields == 14)
        {
            if (total_games >= all_capacity)
            {
                int new_cap = all_capacity * 2;
                Games *tmp = (Games *)realloc(all_games, new_cap * sizeof(Games));
                if (tmp != NULL)
                {
                    all_games = tmp;
                    all_capacity = new_cap;
                }
            }
            all_games[total_games++] = Games_create(fields, numFields);
        }
    }
    fclose(fp);

    ListaGames lista;
    inicializarLista(&lista);

    char input[MAX_LINE];
    bool fimEntrada = false;
    while (!fimEntrada && fgets(input, sizeof(input), stdin) != NULL)
    {
        input[strcspn(input, "\n")] = '\0';
        trim(input);
        if (strcmp(input, "FIM") == 0)
            fimEntrada = true;
        else if (strlen(input) > 0)
        {
            int id = 0;
            if (sscanf(input, "%d", &id) == 1)
            {
                Games *g = buscar_jogo_por_id(all_games, total_games, id);
                if (g != NULL)
                    inserirFim(&lista, g);
            }
        }
    }

    bool comandosLidos = fgets(input, sizeof(input), stdin) != NULL;
    int nComandos = 0;
    if (comandosLidos)
    {
        trim(input);
        if (sscanf(input, "%d", &nComandos) != 1)
            nComandos = 0;
    }

    int i = 0;
    while (i < nComandos)
    {
        bool comandoValido = fgets(input, sizeof(input), stdin) != NULL;
        if (comandoValido)
        {
            input[strcspn(input, "\n")] = '\0';
            trim(input);
            if (strlen(input) > 0)
            {
                char *tokens[4];
                int tk = 0;
                char *p = strtok(input, " ");
                while (p != NULL && tk < 4)
                {
                    tokens[tk++] = p;
                    p = strtok(NULL, " ");
                }

                if (tk > 0)
                {
                    if (strcmp(tokens[0], "II") == 0 && tk >= 2)
                    {
                        int id = atoi(tokens[1]);
                        Games *g = buscar_jogo_por_id(all_games, total_games, id);
                        if (g)
                            inserirInicio(&lista, g);
                    }
                    else if (strcmp(tokens[0], "IF") == 0 && tk >= 2)
                    {
                        int id = atoi(tokens[1]);
                        Games *g = buscar_jogo_por_id(all_games, total_games, id);
                        if (g)
                            inserirFim(&lista, g);
                    }
                    else if (strcmp(tokens[0], "I*") == 0 && tk >= 3)
                    {
                        int pos = atoi(tokens[1]);
                        int id = atoi(tokens[2]);
                        Games *g = buscar_jogo_por_id(all_games, total_games, id);
                        if (g)
                            inserirPos(&lista, g, pos);
                    }
                    else if (strcmp(tokens[0], "RI") == 0)
                    {
                        Games *g = removerInicio(&lista);
                        if (g)
                            printf("(R) %s\n", g->name);
                    }
                    else if (strcmp(tokens[0], "RF") == 0)
                    {
                        Games *g = removerFim(&lista);
                        if (g)
                            printf("(R) %s\n", g->name);
                    }
                    else if (strcmp(tokens[0], "R*") == 0 && tk >= 2)
                    {
                        int pos = atoi(tokens[1]);
                        Games *g = removerPos(&lista, pos);
                        if (g)
                            printf("(R) %s\n", g->name);
                    }
                }
            }
        }
        i++;
    }

    mostrarLista(&lista);
    free(all_games);
    return 0;
}
