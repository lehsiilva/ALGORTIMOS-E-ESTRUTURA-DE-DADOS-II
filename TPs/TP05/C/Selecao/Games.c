#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define MAX_GAMES 5000
#define CSV_PATH "/tmp/games.csv"

typedef struct Games{
    int id;
    char name[150];
    char releaseDate[15];
    int estimatedOwners;
    float price;
    char supportedLanguages[1000];
    int metacriticScore;
    float userScore;
    int achievements;
    char publishers[400];
    char developers[400];
    char categories[500];
    char genres[500];
    char tags[2000];
} Games;

Games all_games[MAX_GAMES];
int total_games = 0;

Games jogosSelecionados[MAX_GAMES];
int contJogosSelecionados = 0;

unsigned long long comparacoes = 0;
unsigned long long movimentacoes = 0;

clock_t startTime, endTime; // Variáveis para medir o tempo de execução

// Função para converter o mês de string para número
int parseMonth(char *month){

    char minusculo[10];
    int len = strlen(month);

    if (len > 9){
        len = 9;
    }

    for (int i = 0; i < len; i++){
        minusculo[i] = tolower(month[i]);
    }

    minusculo[len] = '\0';

    if (strstr(minusculo, "jan")){
        return 1;
    }
    if (strstr(minusculo, "feb")){
        return 2;
    }
    if (strstr(minusculo, "mar")){
        return 3;
    }   
    if (strstr(minusculo, "apr")){
        return 4;
    }
    if (strstr(minusculo, "may")){
        return 5;
    }
    if (strstr(minusculo, "jun")){
        return 6;
    }
    if (strstr(minusculo, "jul")){
        return 7;
    }
    if (strstr(minusculo, "aug")){
        return 8;
    }
    if (strstr(minusculo, "sep")){
        return 9;
    }
    if (strstr(minusculo, "oct")){
        return 10;
    }
    if (strstr(minusculo, "nov")){
        return 11;
    }
    if (strstr(minusculo, "dec")){
        return 12;
    }
    return 1;
}

// função para formatar a data
void parseDate(char *entrada, char *saida){
    if (entrada == NULL || strlen(entrada) == 0){
        strcpy(saida, "01/01/1900");
        return;
    }

    char cleanentrada[50];
    int j = 0;
    int len = strlen(entrada);

    for (int i = 0; i < len && j < 49; i++){
        if (entrada[i] != '"'){
            cleanentrada[j++] = entrada[i];
        }
    }

    cleanentrada[j] = '\0';
    char *start = cleanentrada;

    while (isspace((unsigned char)*start)){ //isspace para verificar espaço em branco
        start++;
    }

    char *fim = start + strlen(start) - 1;

    while (fim > start && isspace((unsigned char)*fim)){
        fim--;
    }

    fim[1] = '\0';
    char temp[50];
    strncpy(temp, start, 49); //strncpy para copiar a string
    temp[49] = '\0';
    char *parts[3];
    int count = 0;
    char *token = strtok(temp, " "); //strtok para separar a string em tokens

    while (token && count < 3){
        parts[count++] = token;
        token = strtok(NULL, " ");
    }

    if (count == 3){

        int day = atoi(parts[1]); //atoi para converter string para int
        int month = parseMonth(parts[0]); //parseMonth para converter mês de string para número
        sprintf(saida, "%02d/%02d/%s", day, month, parts[2]);

    }else if (count == 2){

        int month = parseMonth(parts[0]);
        sprintf(saida, "01/%02d/%s", month, parts[1]);

    }else if (count == 1){

        sprintf(saida, "01/01/%s", parts[0]);

    }else{

        strcpy(saida, "01/01/1900");
    }
}

int parseInt(char *str, int def){
    if (str == NULL || strlen(str) == 0){
        return def;
    }

    char clean[50];
    int j = 0;

    for (int i = 0; str[i] && j < 49; i++){
        if (isdigit(str[i])){ //isdigit para verificar se é dígito
            clean[j++] = str[i];
        }
    }

    clean[j] = '\0';
    
    if (strlen(clean) == 0){
        return def;
    }

    return atoi(clean);
}

float parseFloat(char *str, float def){

    if (str == NULL || strlen(str) == 0){
        return def;
    }

    if (strstr(str, "Free to Play")){
        return 0.0;
    }

    float val = 0;
    sscanf(str, "%f", &val);

    return val;
}

// funçao para converter user score
float parseUserScore(char *str){

    if (str == NULL || strlen(str) == 0 || strstr(str, "tbd")){
        return -1.0;
    }

    float val = -1.0;
    sscanf(str, "%f", &val);

    return val;
}

void clearColchetes(char *entrada, char *saida){
    if (entrada == NULL){
        saida[0] = '\0';
        return;
    }

    char temp[2000];
    int k = 0;

    for (int i = 0; entrada[i] && k < 1999; i++){

        char c = entrada[i];

        if (c != '[' && c != ']' && c != '\'' && c != '\"' && c != '\n' && c != '\r'){
            temp[k++] = c;
        }
    }

    temp[k] = '\0';
    int inicio = 0;

    while (temp[inicio] == ' ' || temp[inicio] == ','){
        inicio++;
    }

    int fim = strlen(temp) - 1;

    while (fim > inicio && (temp[fim] == ' ' || temp[fim] == ',')){
        fim--;
    }

    int j = 0;

    for (int i = inicio; i <= fim && j < 999; i++){

        saida[j++] = temp[i];

        if (temp[i] == ',' && i + 1 <= fim && temp[i + 1] != ' ' && temp[i + 1] != ','){
            if (j < 999){
                saida[j++] = ' ';
            }
        }
    }

    saida[j] = '\0';
}

// Função para ler um registro CSV e preencher a estrutura Games
void parseCSVLine(char *line, Games *g){

    char *campos[14];
    int idx = 0;
    int aspas = 0;
    char temp[5000];
    int j = 0;
    strncpy(temp, line, 4999);
    temp[4999] = '\0';
    char *posAtual = temp;
    char *campo_start = temp;

    for (int i = 0; temp[i] && idx < 14; i++) {
        if (temp[i] == '"') {
            aspas = !aspas;
        } else if (temp[i] == ',' && !aspas) {
            temp[i] = '\0';
            campos[idx++] = strdup(campo_start);//strdup para duplicar string
            campo_start = temp + i + 1;
        }
    }

    if (idx < 14){
        campos[idx++] = strdup(campo_start);
    }

    for (int k = 0; k < idx; k++){
        char *newline = strchr(campos[k], '\n'); //strchr para encontrar caractere na string
        if (newline){
            *newline = '\0';
        }
    }

    if (idx < 14){
        for (int k = 0; k < idx; k++){
            free(campos[k]);
        }
        g->id = -1;
        return;
    }

    char *namecampo = campos[1];

    if (namecampo[0] == '"'){
        namecampo++;
        namecampo[strlen(namecampo) - 1] = '\0';
    }

    g->id = atoi(campos[0]);

    strncpy(g->name, namecampo, 149);
    g->name[149] = '\0';

    parseDate(campos[2], g->releaseDate);

    g->estimatedOwners = parseInt(campos[3], 0);

    g->price = parseFloat(campos[4], 0.0);

    clearColchetes(campos[5], g->supportedLanguages);

    g->metacriticScore = parseInt(campos[6], -1);

    g->userScore = parseUserScore(campos[7]);

    g->achievements = parseInt(campos[8], 0);

    clearColchetes(campos[9], g->publishers);

    clearColchetes(campos[10], g->developers);

    clearColchetes(campos[11], g->categories);

    clearColchetes(campos[12], g->genres);

    clearColchetes(campos[13], g->tags);

    for (int k = 0; k < idx; k++){
        free(campos[k]);
    }
}

// Função para carregar todos os jogos do CSV
void carregarAllJogos(){
    FILE *f = fopen(CSV_PATH, "r");
    if (!f){

        printf("Erro ao abrir %s\n", CSV_PATH);
        exit(1);
    }

    char line[5000];
    fgets(line, sizeof(line), f);

    while (fgets(line, sizeof(line), f)){

        if (strlen(line) > 10){
            parseCSVLine(line, &all_games[total_games]);

            if (all_games[total_games].id != -1){
                total_games++;
            }
        }
    }

    fclose(f);
}

// Função para buscar um jogo pelo ID
Games *buscar_jogo_por_id(int id){
    for (int i = 0; i < total_games; i++){
        if (all_games[i].id == id){
            return &all_games[i];
        }
    }
    return NULL;
}

void Games_imprimir(Games *g){
    printf("=> %d ## %s ## %s ## %d ## %.2f ## [%s] ## %d ## %.1f ## %d ## [%s] ## [%s] ## [%s] ## [%s] ## [%s] ##\n",
           g->id, g->name, g->releaseDate, g->estimatedOwners, g->price,
           g->supportedLanguages, g->metacriticScore, g->userScore, g->achievements,
           g->publishers, g->developers, g->categories, g->genres, g->tags);
}

//Criar arquivo log
void createLog(){
    char *matricula = "874205";
    char fileName[100];
    sprintf(fileName, "%s_selecao.txt", matricula);
    double tempo = ((double)(endTime - startTime)) / CLOCKS_PER_SEC;

    FILE *f = fopen(fileName, "w");

    if (f == NULL){
        printf("Erro ao criar arquivo de log.\n");
        return;
    }

    fprintf(f, "%s\t%llu\t%llu\t%.6f\n", matricula, comparacoes, movimentacoes, tempo);
    fclose(f);
}

void swap(int i, int j){
    movimentacoes += 3;
    Games temp = jogosSelecionados[i];
    jogosSelecionados[i] = jogosSelecionados[j];
    jogosSelecionados[j] = temp;
}

//Comparar jogos pela chave de pesquisa (name)
int CompararJogos(Games g1, Games g2){
    comparacoes++;
    int nameCmp = strcmp(g1.name, g2.name);

    if (nameCmp != 0){
        return nameCmp;
    }

    comparacoes++;

    return g1.id - g2.id;
}

// Ordena pela chave de pesquisa (name)
void selectionSort(){
    for (int i = 0; i < (contJogosSelecionados - 1); i++){
        int menor = i;
        for (int j = (i + 1); j < contJogosSelecionados; j++){
            if (CompararJogos(jogosSelecionados[j], jogosSelecionados[menor]) < 0){
                menor = j;
            }
        }
        swap(i, menor);
    }
}

int main(){

    carregarAllJogos();
    char input[50];
    scanf("%s", input);

    while (strcmp(input, "FIM") != 0) { //busca o jogo pelo ID e adiciona ao array de jogos selecionados
        int id = atoi(input);
        Games *g = buscar_jogo_por_id(id);
        if (g) {
            jogosSelecionados[contJogosSelecionados] = *g;
            contJogosSelecionados++;
        }
        scanf("%s", input); 
    }

    startTime = clock();
    selectionSort();
    endTime = clock();
    createLog();

    for (int i = 0; i < contJogosSelecionados; i++){
        Games_imprimir(&jogosSelecionados[i]);
    }

    return 0;
}
