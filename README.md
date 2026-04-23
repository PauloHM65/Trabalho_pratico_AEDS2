# Trabalhos Práticos — Algoritmos e Estruturas de Dados II

PUC Minas — ICEI — Repositório com os trabalhos práticos I e II da disciplina.

## Estrutura

- `src/tp1/` — arquivos fonte das 12 questões do TP1 (`.java` e `.c`)
- `src/tp2/` — arquivos fonte das 13 questões do TP2 (`.java` e `.c`)
- `src/restaurantes.csv` — dataset do TP2 (deve ser copiado para `/tmp/` antes de executar)

---

# Trabalho Prático I

Foco em **manipulação de strings**, **aritmética** e **recursividade**. Cada questão recebe linhas pela entrada padrão até a palavra `FIM` e escreve uma linha de saída por linha de entrada.

## Regras importantes

- Em **Java**: só são permitidos tipos primitivos e as classes `MyIO`, `String`, `Scanner`. Em `String`, apenas `charAt(int)` e `length()`.
- Em **C**: apenas tipos primitivos e funções de `stdio.h` / `stdlib.h`.
- Métodos recursivos: proibido usar estruturas de repetição, variáveis globais, parâmetros além dos do enunciado (exceto contador auxiliar) e múltiplos `return`.
- Para contar letras/vogais/consoantes: considerar apenas ASCII entre `A-Z` e `a-z` (sem acento).

## Questões

| #  | Linguagem | Título                                  | Assunto                                                                                             |
|----|-----------|-----------------------------------------|-----------------------------------------------------------------------------------------------------|
| 1  | Java      | Ciframento de César (iterativo)         | Desloca cada caractere em 3 posições no alfabeto                                                    |
| 2  | Java      | Alteração Aleatória (iterativo)         | Sorteia duas letras minúsculas (seed=4) e substitui todas as ocorrências da 1ª pela 2ª              |
| 3  | Java      | Is (iterativo)                          | Testa se a string é só vogais / só consoantes / número inteiro / número real → saída `X1 X2 X3 X4`  |
| 4  | C         | Inversão de String (iterativo)          | Retorna a string com os caracteres em ordem inversa                                                 |
| 5  | C         | Soma de Dígitos (recursivo)             | Soma os dígitos de um número inteiro (ex: `12345` → `15`)                                           |
| 6  | C         | Verificação de Anagrama (iterativo)     | Verifica se duas strings são anagramas (`SIM`/`NAO`)                                                |
| 7  | C         | Substring Mais Longa Sem Repetição      | Retorna o comprimento da maior substring sem caracteres repetidos                                   |
| 8  | Java      | Validação de Senha (iterativo)          | Testa se a senha tem ≥8 caracteres, maiúscula, minúscula, número e caractere especial               |
| 9  | C         | Ciframento de César (recursivo)         | Refaz a Q1 de forma recursiva                                                                       |
| 10 | C         | Is (recursivo)                          | Refaz a Q3 de forma recursiva                                                                       |
| 11 | Java      | Inversão de String (recursivo)          | Refaz a Q4 de forma recursiva                                                                       |
| 12 | Java      | Soma de Dígitos (recursivo)             | Refaz a Q5 em Java, mantendo a recursão                                                             |

---

# Trabalho Prático II

Dataset: **Restaurantes pelo Mundo** (`restaurantes.csv`).

Modelagem de restaurantes com atributos como id, nome, cidade, capacidade, avaliação, tipos de cozinha, faixa de preço, horário de funcionamento, data de abertura e status. O trabalho explora diferentes algoritmos de pesquisa, ordenação e estruturas de dados aplicados a essa coleção.

## Questões

| #  | Linguagem | Título                                  | Assunto                                                                                  |
|----|-----------|-----------------------------------------|------------------------------------------------------------------------------------------|
| 1  | Java      | Modelagem em Java                       | Classes `Restaurante`, `Data`, `Hora` e `ColecaoRestaurantes` com encapsulamento e getters |
| 2  | C         | Modelagem em C                          | Mesma modelagem com `struct`s, ponteiros e funções em snake_case                         |
| 3  | C         | Ordenação por Seleção em C              | Ordena a coleção pelo atributo **nome**                                                  |
| 4  | Java      | Ordenação por Inserção em Java          | Ordena a coleção pelo atributo **cidade**                                                |
| 5  | Java      | Pesquisa Sequencial em Java             | Busca por **nome** (SIM/NAO) percorrendo o arranjo linearmente                           |
| 6  | C         | Pesquisa Binária em C                   | Busca por **nome** em arranjo ordenado (usa a ordenação da Q3)                           |
| 7  | Java      | Ordenação por Mergesort em Java         | Ordena por **cidade**; empate decidido por **nome**                                      |
| 8  | C         | Ordenação por Quicksort em C            | Ordena por **avaliacao**; empate decidido por **nome**                                   |
| 9  | Java      | Ordenação por Heapsort em Java          | Ordena por **data_abertura**; empate decidido por **nome**                               |
| 10 | C         | Ordenação por Counting Sort em C        | Ordena pelo atributo **capacidade**                                                      |
| 11 | Java      | Lista com Alocação Sequencial em Java   | Lista de registros com inserção e remoção no início, posição e fim                       |
| 12 | C         | Pilha com Alocação Sequencial em C      | Pilha de registros (empilhar `I` / desempilhar `R`) exibida do topo                      |
| 13 | Java      | Fila Circular com Alocação Sequencial   | Fila circular de tamanho 5; insere média arredondada do ano de abertura                  |

## Arquivos de log (pesquisa e ordenação)

As questões de pesquisa e ordenação geram um log `matricula_<algoritmo>.txt` contendo, separado por tabulação:

- **Pesquisas:** matrícula, nº de comparações, tempo de execução
- **Ordenações:** matrícula, nº de comparações, nº de movimentações, tempo de execução

Logs esperados:

`matricula_selecao.txt`, `matricula_insercao.txt`, `matricula_sequencial.txt`, `matricula_binaria.txt`, `matricula_mergesort.txt`, `matricula_quicksort.txt`, `matricula_heapsort.txt`, `matricula_countingsort.txt`

---

# Como executar

## TP1

```bash
cd src/tp1
javac Tp1Q1.java
java Tp1Q1 < pub.in > saida.txt
diff saida.txt pub.out
```

Para C:

```bash
gcc Tp1Q4.c -o Tp1Q4
./Tp1Q4 < pub.in > saida.txt
```

## TP2

Antes, copie o dataset para `/tmp/` (caminho usado pelo Verde):

```bash
cp src/restaurantes.csv /tmp/
```

### Java

```bash
cd src
javac tp2/tp2Q1.java
java tp2.tp2Q1 < "tp2/pub (1).in" > saida.txt
diff saida.txt "tp2/pub (1).out"
```

### C

```bash
cd src/tp2
gcc tp2Q2.c -o tp2Q2
./tp2Q2 < "pub (1).in" > saida.txt
diff saida.txt "pub (1).out"
```

## Convenções

- **Java (TP2):** camelCase, classes com inicial maiúscula
- **C (TP2):** snake_case, tipos com inicial maiúscula
- Atributos privados com getters (encapsulamento)
- Entrada terminada por `FIM` (TP1 e maioria do TP2) ou `-1` (Q1/Q2 do TP2)
