package tp1;
import java.util.Scanner;

public class Tp1Q11 {

    public static String invertida(String str) {
        StringBuilder resp = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            resp.append(str.charAt(i));
        }

        return resp.toString();
    }

    public static boolean isFim(String s) {
        return s.length() >= 3 &&
               s.charAt(0) == 'F' &&
               s.charAt(1) == 'I' &&
               s.charAt(2) == 'M';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String texto = sc.nextLine();

            if (isFim(texto)) break;

            String invertida = invertida(texto);
            invertida = invertida.replaceAll("\\s+$", ""); // remove espaços finais

            System.out.println(invertida);
        }

        sc.close();
    }
}