#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <stdbool.h> 

#define MAX_GAMES 1000
#define MAX_STR 64
#define MAX_LINE 4096
#define MAX_ARRAY_ITEM 50 
#define CSV_PATH "/tmp/games.csv" 

typedef struct {
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
void trim(char *s) {
    if (!s || *s == '\0'){
        return;
    }

    char *start = s;
    while (*start && isspace((unsigned char)*start)){ //isspace para verificar espaços em branco, using char para evitar problemas com sinais negativos
        start++;
    }
    if (start != s) {
        memmove(s, start, strlen(start) + 1); //mmemove para mover a memória
    }
    int len = strlen(s);
    while (len > 0 && isspace((unsigned char)s[len - 1])) {
        s[--len] = '\0';
    }
}

//função para remover caracteres específicos de uma string [], '
void string_replace_chars(char *s) {
    if (!s || *s == '\0'){
        return;
    }

    char *read_ptr = s;
    char *write_ptr = s;

    while (*read_ptr) {
        if (*read_ptr != '[' && *read_ptr != ']' && *read_ptr != '\'') {
            *write_ptr = *read_ptr;
            write_ptr++;
        }
        read_ptr++;
    }
    *write_ptr = '\0';
}

void parseDate(const char *arqDate, char *releaseDate) {
    if (arqDate == NULL || strlen(arqDate) == 0) {
        strcpy(releaseDate, " "); 
        return;
    }
    char dateCopy[100];
    strncpy(dateCopy, arqDate, 99);
    dateCopy[99] = '\0';
    char *p = dateCopy;
    char cleanedDate[100];
    int j = 0;
    while (*p) {
        if (*p != '\"') {
            cleanedDate[j++] = *p;
        }
        p++;
    }
    cleanedDate[j] = '\0';
    trim(cleanedDate);
    const char *months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    char monthStr[10];
    int day = 0, month = 0, year = 0;
    
    if (sscanf(cleanedDate, "%s %d, %d", monthStr, &day, &year) == 3) {
        for (int i = 0; i < 12; i++) {
            if (strncmp(monthStr, months[i], 3) == 0) {
                month = i + 1;
                int i = 12; // Sai do loop
            }
        }
        if (month > 0 && year > 0) {
            sprintf(releaseDate, "%02d/%02d/%04d", day, month, year);
            return;
        }
    }
    
    if (sscanf(cleanedDate, "%s %d", monthStr, &year) == 2) {
        for (int i = 0; i < 12; i++) {
            if (strncmp(monthStr, months[i], 3) == 0) {
                month = i + 1;
                int i = 12; // Sai do loop
            }
        }
        if (month > 0 && year > 0) {
            sprintf(releaseDate, "01/%02d/%04d", month, year);
            return;
        }
    }
    
    if (sscanf(cleanedDate, "%d", &year) == 1 && year >= 1900) {
        sprintf(releaseDate, "01/01/%04d", year);
        return;
    }

    strcpy(releaseDate, " "); 
}

int parseArrayField(const char *s, char array[][MAX_STR], int max) {
    char temp[MAX_LINE];
    int count = 0;
    if (!s || strlen(s) == 0){
        return 0;
    }

    strncpy(temp, s, sizeof(temp)-1); //strncpy para copiar a string
    temp[sizeof(temp)-1] = '\0';

    trim(temp);
    string_replace_chars(temp); 
    trim(temp);

    char *tok = strtok(temp, ",");
    while (tok != NULL && count < max) {
        trim(tok);
        
        size_t len = strlen(tok); //size_t tipo de variável para tamanhos, strlen para obter o tamanho da string
        if (len > 0 && tok[0] == '\"') memmove(tok, tok + 1, len);
        len = strlen(tok);
        if (len > 0 && tok[len - 1] == '\"') tok[len - 1] = '\0';
        
        trim(tok);
        
        if (strlen(tok) > 0) {
            strncpy(array[count], tok, MAX_STR-1); 
            array[count][MAX_STR-1] = '\0';
            count++;
        }
        tok = strtok(NULL, ","); //strtok para separar a string em tokens
    }
    return count;
}

// função para ler uma linha CSV em campos
int readCSVLine(char *line, char fields[][MAX_LINE], int maxFields) {
    int fieldIndex = 0;
    int aspas = 0; 
    char *start = line;
    char *p = line;

    size_t len = strlen(line);
    if (len > 0 && line[len - 1] == '\n') line[--len] = '\0';
    if (len > 0 && line[len - 1] == '\r') line[--len] = '\0';

    while (*p && fieldIndex < maxFields) {
        bool avanca_p = true;

        if (*p == '\"') {
            
            if (*(p + 1) == '\"' && aspas) { 
                p += 2; 
                avanca_p = false;
            } else {
                
                aspas = !aspas; 
            }
        } else if (*p == ',' && !aspas) {
            
            int fieldLength = p - start;
            if (fieldLength > MAX_LINE - 1) fieldLength = MAX_LINE - 1;
            
            strncpy(fields[fieldIndex], start, fieldLength);
            fields[fieldIndex][fieldLength] = '\0';
            trim(fields[fieldIndex]);
            
            fieldIndex++;
            start = p + 1;
        } 
        
        if (avanca_p) {
            p++;
        }
    }

    
    if (fieldIndex < maxFields) {
        int fieldLength = strlen(start);
        if (fieldLength > MAX_LINE - 1) fieldLength = MAX_LINE - 1;
        
        strncpy(fields[fieldIndex], start, fieldLength);
        fields[fieldIndex][fieldLength] = '\0';
        trim(fields[fieldIndex]);
        
        fieldIndex++;
    }
    return fieldIndex;
}

// função para criar um objeto Games a partir dos campos lidos
Games Games_create(char fields[][MAX_LINE], int numFields) {
    Games g; 
    memset(&g, 0, sizeof(Games));
    g.metacriticScore = -1;
    g.userScore = -1.0f;
    strcpy(g.releaseDate, " ");
    if (numFields < 14) return g;
    
    // ID 
    char cleanId[MAX_STR];
    int k = 0;
    for (int i = 0; fields[0][i] != '\0' && k < MAX_STR - 1; i++) {
        if (isdigit((unsigned char)fields[0][i])) { //isdigit para verificar se é dígito
            cleanId[k++] = fields[0][i];
        }
    }
    cleanId[k] = '\0';
    g.id = k > 0 ? atoi(cleanId) : 0; //atoi para converter string para int
    
    // Nome
    strncpy(g.name, fields[1], MAX_STR-1); g.name[MAX_STR-1] = '\0';
    
    // Data de Lançamento
    parseDate(fields[2], g.releaseDate);
    
    // Donos Estimados (estimatedOwners)
    char cleanOwners[MAX_STR];
    int j = 0;
    for (int i = 0; fields[3][i] != '\0'; i++) {
        if (isdigit((unsigned char)fields[3][i])) {
            cleanOwners[j++] = fields[3][i];
        }
    }
    cleanOwners[j] = '\0';
    g.estimatedOwners = j > 0 ? atoi(cleanOwners) : 0;
    
    // Preço
    if (strcasecmp(fields[4], "Free to Play") == 0) { // strcasecmp para comparação ignorando maiúsculas/minúsculas
        g.price = 0.0f;
    } else {
        char priceTemp[MAX_STR];
        strncpy(priceTemp, fields[4], MAX_STR-1);
        for (char *c = priceTemp; *c; c++) {
            if (*c == ',') *c = '.';
        }
        g.price = strtof(priceTemp, NULL); //strtof para converter string para float
    }
    
    // Idiomas Suportados (supportedLanguages)
    g.supportedLanguagesCount = parseArrayField(fields[5], g.supportedLanguages, MAX_ARRAY_ITEM);
    
    // Pontuação Metacritic (metacriticScore)
    if (strlen(fields[6]) == 0) {
        g.metacriticScore = -1;
    } else {
        g.metacriticScore = atoi(fields[6]);
    }
    
    // Pontuação do Usuário (userScore)
    char scoreTemp[MAX_STR];
    strncpy(scoreTemp, fields[7], MAX_STR-1);
    trim(scoreTemp); 
    
    if (strlen(scoreTemp) == 0 || strcmp(scoreTemp, "tbd") == 0) { 
        g.userScore = -1.0f;
    } else {
        for (char *c = scoreTemp; *c; c++) {
            if (*c == ',') *c = '.';
        }
        g.userScore = strtof(scoreTemp, NULL);
    }

    // Conquistas (achievements)
    if (strlen(fields[8]) == 0) {
        g.achievements = 0;
    } else {
        g.achievements = atoi(fields[8]); 
    }

    // Publicadoras (publishers)
    g.publishersCount = parseArrayField(fields[9], g.publishers, MAX_ARRAY_ITEM);
    
    // Desenvolvedoras (developers)
    g.developersCount = parseArrayField(fields[10], g.developers, MAX_ARRAY_ITEM);
    
    // Categorias (categories)
    g.categoriesCount = parseArrayField(fields[11], g.categories, MAX_ARRAY_ITEM);
    
    // Gêneros (genres)
    g.genresCount = parseArrayField(fields[12], g.genres, MAX_ARRAY_ITEM);
    
    // Etiquetas (tags)
    g.tagsCount = parseArrayField(fields[13], g.tags, MAX_ARRAY_ITEM);

    return g;
}

void Games_imprimir(const Games *g) {

    printf("=> %d ## %s ## %s ## %d ## %.2f ## [", g->id, g->name, g->releaseDate, g->estimatedOwners, g->price);

    for(int j = 0; j < g->supportedLanguagesCount; j++) {
        printf("%s%s", g->supportedLanguages[j], (j < g->supportedLanguagesCount - 1) ? ", " : "");
    }
        printf("] ## %d ## %.2f ## %d ## [", g->metacriticScore, g->userScore, g->achievements);

    for(int j = 0; j < g->publishersCount; j++){
        printf("%s%s", g->publishers[j], (j < g->publishersCount - 1) ? ", " : "");
    }
        printf("] ## [");

    for(int j = 0; j < g->developersCount; j++){
        printf("%s%s", g->developers[j], (j < g->developersCount - 1) ? ", " : "");
    }
        printf("] ## [");

    for(int j = 0; j < g->categoriesCount; j++){
        printf("%s%s", g->categories[j], (j < g->categoriesCount - 1) ? ", " : "");
    }
        printf("] ## [");

    for(int j = 0; j < g->genresCount; j++){
        printf("%s%s", g->genres[j], (j < g->genresCount - 1) ? ", " : "");
    }
        printf("] ## [");

    for(int j = 0; j < g->tagsCount; j++){
        printf("%s%s", g->tags[j], (j < g->tagsCount - 1) ? ", " : "");
    }
        printf("] ## \n");
}

// função para buscar um jogo pelo ID
Games *buscar_jogo_por_id(Games *all_games, int total_games, int id) {
    for (int i = 0; i < total_games; i++) {
        if (all_games[i].id == id){
            return &all_games[i];
        }
    }
    return NULL;
}

// função para ler um registro CSV
int ler_registro_csv(FILE *fp, char *buffer, size_t bufsize) {
    buffer[0] = '\0';
    char tmp[MAX_LINE];

    if (fgets(tmp, sizeof(tmp), fp) == NULL) return 0;
    strncpy(buffer, tmp, bufsize - 1);
    buffer[bufsize - 1] = '\0';

    // conta aspas para verificar se o registro está completo (par = completo)
    int contAspas = 0;
    for (char *p = buffer; *p; p++){
        if (*p == '\"'){
            contAspas++;
    }   }
    // Continua lendo linhas enquanto contAspas for ímpar E fgets for bem-sucedido
    while ((contAspas % 2) != 0 && fgets(tmp, sizeof(tmp), fp) != NULL) {
        size_t remaining = bufsize - strlen(buffer) - 1;
        if (remaining > 0){
            strncat(buffer, tmp, remaining); // strncat para concatenar strings com limite
        }
        for (char *p = tmp; *p; p++){
            if (*p == '\"'){
                contAspas++;
        }   }
    }

    // remove \r\n finais
    size_t len = strlen(buffer);
    while (len > 0 && (buffer[len-1] == '\n' || buffer[len-1] == '\r')){
        buffer[--len] = '\0';
    }
    return (buffer[0] != '\0');
}

int main() {
    FILE *fp = fopen(CSV_PATH, "r"); 
    if(fp == NULL) {
        printf("Erro ao abrir arquivo %s\n", CSV_PATH);
        return 1;
    }

    // Alocação inicial para todos os jogos
    int all_capacity = 4096;
    Games *all_games = (Games *)malloc(all_capacity * sizeof(Games));
    if (all_games == NULL) {
        printf("Erro: Falha ao alocar memoria para os jogos.\n");
        fclose(fp);
        return 1;
    }

    int total_games = 0;
    char line[MAX_LINE];
    char fields[14][MAX_LINE];
    bool reading_ok = true; // Novo flag para controlar falhas de alocação

    // Pula o cabeçalho 
    if(!ler_registro_csv(fp, line, sizeof(line))) {
        fclose(fp);
        free(all_games);
        return 1;
    }

    // Leitura e criação de todos os objetos Games 
    while (reading_ok && ler_registro_csv(fp, line, sizeof(line))) {
        int numFields = readCSVLine(line, fields, 14);
        
        if (numFields == 14) {
            
            if (total_games >= all_capacity) {
                int new_cap = all_capacity * 2;
                Games *tmp = (Games *)realloc(all_games, new_cap * sizeof(Games));
                if (tmp == NULL) {
                    fprintf(stderr, "Erro: realloc falhou em aumentar all_games\n");
                    reading_ok = false; 
                } else {
                    all_games = tmp;
                    all_capacity = new_cap;
                }
            }
            if (reading_ok) { 
                all_games[total_games++] = Games_create(fields, numFields);
            }

        } 
    }

    fclose(fp);

    // Leitura dos IDs a serem buscados
    char input[2000];
    int idBuscado = -1;
    int selected_ids[MAX_GAMES]; 
    int selected_ids_count = 0;


    while(fgets(input, sizeof(input), stdin) != NULL) { //Enquanto houver linhas para ler
        input[strcspn(input, "\n")] = '\0';
        trim(input);
        
        if (sscanf(input, "%d", &idBuscado) == 1) {
            if (selected_ids_count < MAX_GAMES) {
                selected_ids[selected_ids_count++] = idBuscado;
            }
        }
    }
    
    // Criação e preenchimento do array de jogos selecionados
    Games *selected_games = (Games *)malloc(selected_ids_count * sizeof(Games));
    if (selected_games == NULL) {
        printf("Erro: Falha ao alocar memoria para os jogos selecionados.\n");
        free(all_games);
        return 1;
    }

    int selected_games_count = 0;
    // Percorre os IDs na ORDEM 
    for(int i = 0; i < selected_ids_count; i++) {
        idBuscado = selected_ids[i];

        Games *found = buscar_jogo_por_id(all_games, total_games, idBuscado); // buscar_jogo_por_id
        if (found != NULL) {
            // Copia o jogo, mantendo a ordem de entrada.
            selected_games[selected_games_count++] = *found; 
        } 
    }
    
   
    for(int i = 0; i < selected_games_count; i++) {
        Games_imprimir(&selected_games[i]);
    }
  
    free(all_games);
    free(selected_games);

    return 0;
}