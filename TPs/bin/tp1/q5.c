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
    scanf("%d", &numero);
    printf(soma(numero));

    return 0;
}
