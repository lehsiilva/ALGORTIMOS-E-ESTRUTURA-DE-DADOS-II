#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

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
    strncpy(dateCopy, arqDate, sizeof(dateCopy) - 1);
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
            if (strncmp(monthStr, months[i], 3) == 0)
                month = i + 1;
        if (month > 0 && year > 0)
        {
            sprintf(releaseDate, "%02d/%02d/%04d", day, month, year);
            return;
        }
    }
    if (sscanf(cleanedDate, "%s %d", monthStr, &year) == 2)
    {
        for (int i = 0; i < 12; i++)
            if (strncmp(monthStr, months[i], 3) == 0)
                month = i + 1;
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
    strncpy(temp, s, sizeof(temp) - 1); //strncpy para copiar a string
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
    if (fgets(tmp, sizeof(tmp), fp) == NULL)
        return 0;
    strncpy(buffer, tmp, bufsize - 1);
    buffer[bufsize - 1] = '\0';
    int contAspas = 0;     // conta aspas para verificar se o registro está completo (par = completo)
    for (char *p = buffer; *p; p++)
        if (*p == '"')
            contAspas++;
    bool fim = (contAspas % 2 == 0);
    while (!fim && fgets(tmp, sizeof(tmp), fp) != NULL)
    {
        size_t remaining = bufsize - strlen(buffer) - 1;
        if (remaining > 0)
            strncat(buffer, tmp, remaining); // strncat para concatenar strings com limite
        for (char *p = tmp; *p; p++)
            if (*p == '"')
                contAspas++;
        fim = (contAspas % 2 == 0);
    }
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
            inQuotes = !inQuotes;
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
        }
        p++;
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
    if (numFields < 14)
        return g;
    char cleanId[MAX_STR];
    int k = 0;
    for (int i = 0; fields[0][i]; i++)
        if (isdigit(fields[0][i]))
            cleanId[k++] = fields[0][i];
    cleanId[k] = '\0';
    g.id = k > 0 ? atoi(cleanId) : 0;
    strncpy(g.name, fields[1], MAX_STR - 1);
    g.name[MAX_STR - 1] = '\0';
    parseDate(fields[2], g.releaseDate);
    char cleanOwners[MAX_STR];
    int j = 0;
    for (int i = 0; fields[3][i]; i++)
        if (isdigit(fields[3][i]))
            cleanOwners[j++] = fields[3][i];
    cleanOwners[j] = '\0';
    g.estimatedOwners = j > 0 ? atoi(cleanOwners) : 0;
    if (strcasecmp(fields[4], "Free to Play") == 0)
        g.price = 0.0f;
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
    g.metacriticScore = strlen(fields[6]) == 0 ? -1 : atoi(fields[6]);
    char scoreTemp[MAX_STR];
    strncpy(scoreTemp, fields[7], MAX_STR - 1);
    trim(scoreTemp);
    if (strlen(scoreTemp) == 0 || strcmp(scoreTemp, "tbd") == 0)
        g.userScore = -1.0f;
    else
    {
        for (char *c = scoreTemp; *c; c++)
            if (*c == ',')
                *c = '.';
        g.userScore = strtof(scoreTemp, NULL);
    }
    g.achievements = strlen(fields[8]) == 0 ? 0 : atoi(fields[8]);
    g.publishersCount = parseArrayField(fields[9], g.publishers, MAX_ARRAY_ITEM);
    g.developersCount = parseArrayField(fields[10], g.developers, MAX_ARRAY_ITEM);
    g.categoriesCount = parseArrayField(fields[11], g.categories, MAX_ARRAY_ITEM);
    g.genresCount = parseArrayField(fields[12], g.genres, MAX_ARRAY_ITEM);
    g.tagsCount = parseArrayField(fields[13], g.tags, MAX_ARRAY_ITEM);
    return g;
}

void Games_imprimir(const Games *g)
{
    printf("=> %d ## %s ## %s ## %d ## %.2f ## [", g->id, g->name, g->releaseDate, g->estimatedOwners, g->price);
    for (int j = 0; j < g->supportedLanguagesCount; j++)
        printf("%s%s", g->supportedLanguages[j], (j < g->supportedLanguagesCount - 1) ? ", " : "");
    printf("] ## %d ## %.1f ## %d ## [", g->metacriticScore, g->userScore, g->achievements);
    for (int j = 0; j < g->publishersCount; j++)
        printf("%s%s", g->publishers[j], (j < g->publishersCount - 1) ? ", " : "");
    printf("] ## [");
    for (int j = 0; j < g->developersCount; j++)
        printf("%s%s", g->developers[j], (j < g->developersCount - 1) ? ", " : "");
    printf("] ## [");
    for (int j = 0; j < g->categoriesCount; j++)
        printf("%s%s", g->categories[j], (j < g->categoriesCount - 1) ? ", " : "");
    printf("] ## [");
    for (int j = 0; j < g->genresCount; j++)
        printf("%s%s", g->genres[j], (j < g->genresCount - 1) ? ", " : "");
    printf("] ## [");
    for (int j = 0; j < g->tagsCount; j++)
        printf("%s%s", g->tags[j], (j < g->tagsCount - 1) ? ", " : "");
    printf("] ## \n");
}

// função para buscar um jogo pelo ID
Games *buscar_jogo_por_id(Games *all_games, int total_games, int id)
{
    for (int i = 0; i < total_games; i++)
        if (all_games[i].id == id)
            return &all_games[i];
    return NULL;
}

//Pilha
typedef struct CelulaPilha
{
    Games *game;
    struct CelulaPilha *prox;
} CelulaPilha;

typedef struct
{
    CelulaPilha *topo;
    int tamanho;
} PilhaGames;

void inicializarPilha(PilhaGames *p)
{
    p->topo = NULL;
    p->tamanho = 0;
}

void empilhar(PilhaGames *p, Games *g)
{
    CelulaPilha *nova = (CelulaPilha *)malloc(sizeof(CelulaPilha));
    nova->game = g;
    nova->prox = p->topo;
    p->topo = nova;
    p->tamanho++;
}

Games *desempilhar(PilhaGames *p)
{
    if (!p->topo)
        return NULL;
    CelulaPilha *rem = p->topo;
    Games *g = rem->game;
    p->topo = rem->prox;
    free(rem);
    p->tamanho--;
    return g;
}

void mostrarPilha(PilhaGames *p)
{
    if (p->tamanho == 0)
        return;
    Games **aux = (Games **)malloc(p->tamanho * sizeof(Games *));
    int idx = 0;
    for (CelulaPilha *c = p->topo; c; c = c->prox)
        aux[idx++] = c->game;
    for (int i = p->tamanho - 1; i >= 0; i--)
    {
        printf("[%d] ", p->tamanho - 1 - i);
        Games_imprimir(aux[i]);
    }
    free(aux);
}

int main()
{
    FILE *fp = fopen(CSV_PATH, "r");
    if (!fp)
    {
        fprintf(stderr, "Erro ao abrir arquivo %s\n", CSV_PATH);
        return 1;
    }

    // Alocação inicial para todos os jogos
    int all_capacity = 1024;
    Games *all_games = (Games *)malloc(all_capacity * sizeof(Games));
    if (!all_games)
    {
        fprintf(stderr, "Erro alocar jogos\n");
        fclose(fp);
        return 1;
    }

    int total_games = 0;
    char line[MAX_LINE];
    char fields[14][MAX_LINE];

    bool primeiraLinha = ler_registro_csv(fp, line, sizeof(line));
    if (!primeiraLinha)
    {
        fclose(fp);
        free(all_games);
        return 1;
    }

    bool leituraCSV = true;
    while (leituraCSV)
    {
        bool registroValido = ler_registro_csv(fp, line, sizeof(line));
        if (registroValido)
        {
            int numFields = readCSVLine(line, fields, 14);
            if (numFields == 14)
            {
                if (total_games >= all_capacity)
                {
                    int new_cap = all_capacity * 2;
                    Games *tmp = (Games *)realloc(all_games, new_cap * sizeof(Games));
                    if (tmp)
                    {
                        all_games = tmp;
                        all_capacity = new_cap;
                    }
                    else
                    {
                        leituraCSV = false;
                    }
                }
                if (leituraCSV)
                    all_games[total_games++] = Games_create(fields, numFields);
            }
        }
        else
            leituraCSV = false;
    }
    fclose(fp);

    PilhaGames pilha;
    inicializarPilha(&pilha);

    // Leitura dos IDs para empilhar inicialmente
    bool fimEntrada = false;
    while (!fimEntrada)
    {
        bool leu = fgets(line, sizeof(line), stdin);
        if (leu)
        {
            line[strcspn(line, "\n")] = 0;
            trim(line);
            if (strcmp(line, "FIM") == 0)
                fimEntrada = true;
            else if (strlen(line) > 0)
            {
                int id;
                if (sscanf(line, "%d", &id) == 1)
                {
                    Games *g = buscar_jogo_por_id(all_games, total_games, id);
                    if (g)
                        empilhar(&pilha, g);
                }
            }
        }
        else
            fimEntrada = true;
    }

    // Lê o número de comandos a serem executados
    bool comandosExistem = fgets(line, sizeof(line), stdin) != NULL;
    int nComandos = 0;
    if (comandosExistem)
    {
        trim(line);
        if (sscanf(line, "%d", &nComandos) != 1)
            nComandos = 0;
    }

    int i = 0;
    while (i < nComandos)
    {
        bool leu = fgets(line, sizeof(line), stdin) != NULL;
        if (leu)
        {
            line[strcspn(line, "\n")] = 0;
            trim(line);
            if (strlen(line) > 0)
            {
                if (line[0] == 'I')
                {
                    int id;
                    sscanf(line + 2, "%d", &id);
                    Games *g = buscar_jogo_por_id(all_games, total_games, id);
                    if (g)
                        empilhar(&pilha, g);
                }
                else if (line[0] == 'R')
                {
                    Games *g = desempilhar(&pilha);
                    if (g)
                        printf("(R) %s\n", g->name);
                }
                i++;
            }
        }
        else
            i++;
    }

    mostrarPilha(&pilha);
    free(all_games);
    return 0;
}
