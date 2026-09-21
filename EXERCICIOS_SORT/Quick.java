public class Quick {
    static long comp = 0;
    static long mov = 0;

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        mov += 3;
    }

    static void imprimirArray(String label, int[] array) {
        System.out.printf(label);
        for (int t = 0; t < array.length; t++) {
            System.out.print(array[t] + " ");
        }
        System.out.println();
    }

    static void quicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir, pivo = array[(dir + esq) / 2];

        while (i <= j) {
            while (array[i] < pivo) {
                comp++;i++;
            }comp++;
            while (array[j] > pivo) {
                comp++;j--;
            }comp++;

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (j > esq) {
            quicksort(esq, j, array);
        }
        if (i < dir) {
            quicksort(i, dir, array);
        }
    }

    public static void main(String[] args) {
        int array[];
        if (args.length > 0) {
            array = new int[args.length];
            for (int k = 0; k < args.length; k++) array[k] = Integer.parseInt(args[k]);
        } else {
            array = new int[] { 67, 25, 4, 56, 8, 10, 101, 5, 115 };
        }

        imprimirArray("Inicial:", array);

        long t0 = System.nanoTime();
        quicksort(0, array.length - 1, array);
        long t1 = System.nanoTime();

        imprimirArray("ORDENADO:", array);

        System.out.println("---");
        System.out.println("Comparacoes:   " + comp);
        System.out.println("Movimentacoes: " + mov);
        System.out.println("Tempo (ns):    " + (t1 - t0));

        System.out.println("QUICK_RESULT comp=" + comp + " mov=" + mov + " tempo_ns=" + (t1 - t0));
    }
}
