#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef struct {
    int ano;
    int mes;
    int dia;
} Data;

typedef struct {
    int hora;
    int minuto;
} Hora;

typedef struct {
    int id;
    char nome[100];
    char cidade[100];
    int capacidade;
    double avaliacao;
    char tipos_cozinha[10][50];
    int num_tipos_cozinha;
    int faixa_preco;
    Hora horario_abertura;
    Hora horario_fechamento;
    Data data_abertura;
    bool aberto;
} Restaurante;

typedef struct {
    int tamanho;
    Restaurante* restaurantes[1000];
} Colecao_Restaurantes;

Data parse_data(char* s) {
    Data d;
    sscanf(s, "%d-%d-%d", &d.ano, &d.mes, &d.dia);
    return d;
}

void formatar_data(Data* data, char* buffer) {
    sprintf(buffer, "%02d/%02d/%04d", data->dia, data->mes, data->ano);
}

Hora parse_hora(char* s) {
    Hora h;
    sscanf(s, "%d:%d", &h.hora, &h.minuto);
    return h;
}

void formatar_hora(Hora* hora, char* buffer) {
    sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto);
}

Restaurante* parse_restaurante(char* line) {
    Restaurante* r = (Restaurante*) malloc(sizeof(Restaurante));
    line[strcspn(line, "\r\n")] = 0; // Removendo quebra de linha

    char* saveptr;
    char* token = strtok_r(line, ",", &saveptr);
    r->id = atoi(token);

    token = strtok_r(NULL, ",", &saveptr);
    strcpy(r->nome, token);

    token = strtok_r(NULL, ",", &saveptr);
    strcpy(r->cidade, token);

    token = strtok_r(NULL, ",", &saveptr);
    r->capacidade = atoi(token);

    token = strtok_r(NULL, ",", &saveptr);
    r->avaliacao = strtod(token, NULL);

    // Tipos cozinha
    token = strtok_r(NULL, ",", &saveptr);
    char coz_str[200];
    strcpy(coz_str, token);
    char* c_saveptr;
    char* c_tok = strtok_r(coz_str, ";", &c_saveptr);
    r->num_tipos_cozinha = 0;
    while(c_tok != NULL) {
        strcpy(r->tipos_cozinha[r->num_tipos_cozinha++], c_tok);
        c_tok = strtok_r(NULL, ";", &c_saveptr);
    }

    // Faixa Preço
    token = strtok_r(NULL, ",", &saveptr);
    int count = 0;
    for(int i = 0; token[i] != '\0'; i++) if(token[i] == '$') count++;
    r->faixa_preco = count;

    // Horários
    token = strtok_r(NULL, ",", &saveptr);
    char hor_str[50];
    strcpy(hor_str, token);
    char* h_saveptr;
    char* h_tok = strtok_r(hor_str, "-", &h_saveptr);
    r->horario_abertura = parse_hora(h_tok);
    h_tok = strtok_r(NULL, "-", &h_saveptr);
    r->horario_fechamento = parse_hora(h_tok);

    token = strtok_r(NULL, ",", &saveptr);
    r->data_abertura = parse_data(token);

    token = strtok_r(NULL, ",", &saveptr);
    if (strcmp(token, "true") == 0 || strcmp(token, "True") == 0) r->aberto = true;
    else r->aberto = false;

    return r;
}

void formatar_restaurante(Restaurante* r, char* buffer) {
    char da_buf[20], ha_buf[10], hf_buf[10];
    formatar_data(&r->data_abertura, da_buf);
    formatar_hora(&r->horario_abertura, ha_buf);
    formatar_hora(&r->horario_fechamento, hf_buf);

    char cozinha_buf[200] = "[";
    for(int i = 0; i < r->num_tipos_cozinha; i++) {
        strcat(cozinha_buf, r->tipos_cozinha[i]);
        if(i < r->num_tipos_cozinha - 1) strcat(cozinha_buf, ",");
    }
    strcat(cozinha_buf, "]");

    char preco_buf[5] = "";
    for(int i = 0; i < r->faixa_preco; i++) strcat(preco_buf, "$");

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## %s ## %s ## %s-%s ## %s ## %s]",
            r->id, r->nome, r->cidade, r->capacidade, r->avaliacao,
            cozinha_buf, preco_buf, ha_buf, hf_buf, da_buf, r->aberto ? "true" : "false");
}

void ler_csv_colecao(Colecao_Restaurantes* colecao, char* path) {
    FILE* f = fopen(path, "r");
    if (!f) return;
    char line[1024];
    fgets(line, sizeof(line), f); // Ignora cabeçalho
    colecao->tamanho = 0;
    while(fgets(line, sizeof(line), f)) {
        colecao->restaurantes[colecao->tamanho++] = parse_restaurante(line);
    }
    fclose(f);
}

Colecao_Restaurantes* ler_csv() {
    Colecao_Restaurantes* c = (Colecao_Restaurantes*) malloc(sizeof(Colecao_Restaurantes));
    ler_csv_colecao(c, "tmp/restaurantes.csv");
    return c;
}
int main() {
    Colecao_Restaurantes* colecao = ler_csv();
    int id;

    Restaurante* sub_colecao[1000];
    int n_sub = 0;

    while (scanf("%d", &id) == 1 && id != -1) {
        for (int i = 0; i < colecao->tamanho; i++) {
            if (colecao->restaurantes[i]->id == id) {
                sub_colecao[n_sub++] = colecao->restaurantes[i];
                break;
            }
        }
    }

    int comparacoes = 0;
    int movimentacoes = 0;

    clock_t start = clock();

    for (int i = 0; i < n_sub - 1; i++) {
        int menor_idx = i;
        for (int j = i + 1; j < n_sub; j++) {
            comparacoes++;
            if (strcmp(sub_colecao[j]->nome, sub_colecao[menor_idx]->nome) < 0) {
                menor_idx = j;
            }
        }
        if (menor_idx != i) {
            Restaurante* temp = sub_colecao[i];
            sub_colecao[i] = sub_colecao[menor_idx];
            sub_colecao[menor_idx] = temp;
            movimentacoes += 3; 
        }
    }

    clock_t end = clock();
    double tempo_execucao = ((double)(end - start) / CLOCKS_PER_SEC) * 1000.0;
    for (int i = 0; i < n_sub; i++) {
        char buffer[1024];
        formatar_restaurante(sub_colecao[i], buffer);
        printf("%s\n", buffer);
    }

    FILE* log = fopen("matricula_selecao.txt", "w");
    if (log != NULL) {
        fprintf(log, "827112\t%d\t%d\t%.2f\n", comparacoes, movimentacoes, tempo_execucao);
        fclose(log);
    }

    return 0;
}