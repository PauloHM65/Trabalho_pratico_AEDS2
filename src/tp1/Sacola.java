package tp1;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Collections;

public class Sacola {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();

            Stack<Integer> pilha = new Stack<>();
            Queue<Integer> fila = new LinkedList<>();
            PriorityQueue<Integer> filaPri = new PriorityQueue<>(Collections.reverseOrder());

            boolean ehPilha = true, ehFila = true, ehFilaPri = true;

            for (int i = 0; i < n; i++) {
                int op = sc.nextInt();
                int x = sc.nextInt();

                if (op == 1) {
                    pilha.push(x);
                    fila.add(x);
                    filaPri.add(x);
                } else {
                    if (pilha.isEmpty() || pilha.pop() != x) ehPilha = false;
                    if (fila.isEmpty() || fila.poll() != x) ehFila = false;
                    if (filaPri.isEmpty() || filaPri.poll() != x) ehFilaPri = false;
                }
            }

            String resultado;
            int count = (ehPilha ? 1 : 0) + (ehFila ? 1 : 0) + (ehFilaPri ? 1 : 0);

            if (count == 0) resultado = "impossible";
            else if (count > 1) resultado = "not sure";
            else if (ehPilha) resultado = "stack";
            else if (ehFila) resultado = "queue";
            else resultado = "priority queue";

            System.out.println(resultado);
        }

        sc.close();
    }
}
