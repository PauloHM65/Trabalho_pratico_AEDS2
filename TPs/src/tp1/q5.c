#include <stdio.h>
#include <stdlib.h>

int soma(int numero)
{
    if(numero == 0 )
        return 0;
        
    return numero%10 + soma(numero/10);
}

int main(void)
{
    int numero;

      while (scanf("%d", &numero) != EOF)
    {
        printf("%d\n", soma(numero));
    }

    return 0;
}
