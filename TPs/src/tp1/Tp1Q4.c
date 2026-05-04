#include <stdio.h>
#include <string.h>

int main() {
    char palavra[1000];

    fgets(palavra, sizeof(palavra), stdin);
    palavra[strcspn(palavra, "\n")] = '\0';

    while (strcmp(palavra, "FIM") != 0) {
        int len = strlen(palavra);
        for (int i = len - 1; i >= 0; i--) {
            printf("%c", palavra[i]);
        }
        printf("\n");

        fgets(palavra, sizeof(palavra), stdin);
        palavra[strcspn(palavra, "\n")] = '\0';
    }

    return 0;
}