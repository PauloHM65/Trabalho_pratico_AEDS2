#include <stdio.h>

void Inserction(int *array, int n, long long *cmp, long long *mov)
{
    for (int i = 1; i < n; i++)
    {
        int tmp = array[i];
        (*mov)++;
        int j = i - 1;
        while ((j >= 0) && (++(*cmp), array[j] > tmp))
        {
            array[j + 1] = array[j];
            (*mov)++;
            j--;
        }
        array[j + 1] = tmp;
        (*mov)++;
    }
}

#ifndef BENCHMARK_BUILD
int main(void)
{
    int array[] = {12, 4, 8, 2, 14, 17, 6, 18, 10, 16, 15, 5, 13, 9, 1, 11, 7, 3};
    int n = sizeof(array) / sizeof(array[0]);
    long long cmp = 0, mov = 0;

    Inserction(array, n, &cmp, &mov);
    printf("COMPARACOES: %lld \t MOVIMENTACOES: %lld\n", cmp, mov);
    for (int i = 0; i < n; i++) printf("%d ", array[i]);
    printf("\n");

    return 0;
}
#endif
