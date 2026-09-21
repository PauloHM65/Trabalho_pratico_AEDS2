#include <stdio.h>

int anagrama(char *palavra)
{
    int letras[256] = {0};
    int i = 0;
    int segundaPalavra = 0;

    while (palavra[i] != '\0' && palavra[i] != '\n')
    {
        if (palavra[i] == ' ')
        {
            segundaPalavra = 1;
        }
        else if (segundaPalavra == 0)
        {
            letras[(unsigned char)palavra[i]]++;
        }
        else
        {
            letras[(unsigned char)palavra[i]]--;
        }

        i++;
    }
    for (i = 0; i < 256; i++)
    {
        if (letras[i] != 0)
        {
            return 0;
        }
    }

    return 1;
}

int main(void)
{
    char palavra[200];

    while (fgets(palavra, sizeof(palavra), stdin) != NULL)
    {
        if (palavra[0] == 'F' &&
            palavra[1] == 'I' &&
            palavra[2] == 'M')
        {
            break;
        }
        if (anagrama(palavra))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }

    return 0;
}