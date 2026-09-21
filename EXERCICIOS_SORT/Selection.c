#include <stdio.h>

void swap(int *arr, int menor, int i)
{
    int tmp = arr[i];
    arr[i] = arr[menor];
    arr[menor] = tmp;
}

void selection(int *array, int n, long long *cmp, long long *mov)
{
    for (int i = 0; i < (n - 1); i++)
    {
        int menor = i;
        for (int j = i + 1; j < n; j++)
        {
            (*cmp)++;
            if (array[menor] > array[j]) {
                menor = j;
            }
        }
        (*mov) += 3;
        swap(array, menor, i);
    }
}

#ifndef BENCHMARK_BUILD
int main(void)
{
    int array[] = {12, 4, 8, 2, 14, 17, 6, 18, 10, 16, 15, 5, 13, 9, 1, 11, 7, 3};
    int n = sizeof(array) / sizeof(array[0]);
    long long cmp = 0, mov = 0;

    for (int i = 0; i < n; i++) printf("%d   ", array[i]);
    printf("\n");

    selection(array, n, &cmp, &mov);
    printf("Movi: %lld   |   Comp: %lld \n", mov, cmp);

    for (int i = 0; i < n; i++) printf("%d   ", array[i]);
    printf("\n");

    return 0;
}
#endif
