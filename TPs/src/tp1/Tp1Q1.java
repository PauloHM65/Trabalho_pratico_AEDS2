package tp1;
public class Tp1Q1 {

    public static void main(String[] args) throws Exception {

        String palavra = MyIO.readLine();
        String resultado = "";

        while (!palavra.equals("FIM")) {
            for (int i = 0; i < palavra.length(); i++) {
                resultado = resultado + (char)(palavra.charAt(i) + 3);
            }
            MyIO.println(resultado);
            palavra = MyIO.readLine();
            resultado = "";
        }

    }
}