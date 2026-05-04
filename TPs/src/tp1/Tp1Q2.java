package tp1;
import java.util.Random;

public class Tp1Q2 {

    public static String alteracao(String palavra, Random gerador) {
        char letra1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        String resultado = "";
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == letra1) {
                resultado = resultado + letra2;
            } else {
                resultado = resultado + palavra.charAt(i);
            }
        }
        return resultado;
    }

    public static void main(String[] args) throws Exception {

        Random gerador = new Random();
        gerador.setSeed(4);
        String palavra = MyIO.readLine();

        while (!palavra.equals("FIM")) {
            MyIO.println(alteracao(palavra, gerador));
            palavra = MyIO.readLine();
        }

    }
}