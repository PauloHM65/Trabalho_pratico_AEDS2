package tp1;
public class Tp1Q3 {

    public static boolean isVocagal(String palavra) {
        boolean resultado = true;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) != 'a' && palavra.charAt(i) != 'A' &&
                    palavra.charAt(i) != 'e' && palavra.charAt(i) != 'E' &&
                    palavra.charAt(i) != 'i' && palavra.charAt(i) != 'I' &&
                    palavra.charAt(i) != 'o' && palavra.charAt(i) != 'O' &&
                    palavra.charAt(i) != 'u' && palavra.charAt(i) != 'U') {
                resultado = false;
                i = palavra.length();
            }
        }
        return resultado;
    }

    public static boolean isConsoante(String palavra) {
        boolean resultado = true;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == 'a' || palavra.charAt(i) == 'A' ||
                    palavra.charAt(i) == 'e' || palavra.charAt(i) == 'E' ||
                    palavra.charAt(i) == 'i' || palavra.charAt(i) == 'I' ||
                    palavra.charAt(i) == 'o' || palavra.charAt(i) == 'O' ||
                    palavra.charAt(i) == 'u' || palavra.charAt(i) == 'U'||
                    palavra.charAt(i) >= '0' || palavra.charAt(i) <= '9') {
                resultado = false;
                i = palavra.length();
            }
        }
        return resultado;
    }

    public static boolean isInt(String palavra) {
        boolean resultado = true;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) < '0' || palavra.charAt(i) > '9') {
                resultado = false;
                i = palavra.length();
            }
        }
        return resultado;
    }

    public static boolean isFloat(String palavra) {
        boolean resultado = true;
        int spc = 0;
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) < '0' || palavra.charAt(i) > '9') {
                if (palavra.charAt(i) == ',' || palavra.charAt(i) == '.') {
                    spc++;
                } else {
                    resultado = false;
                    i = palavra.length();
                }
            }
        }
        if (spc > 1)
            resultado = false;
        return resultado;
    }

    public static void main(String[] args) throws Exception {

        String palavra = MyIO.readLine();

        while (!palavra.equals("FIM")) {
            MyIO.print(isVocagal(palavra) ? "SIM" : "NAO");
            MyIO.print(" ");
            MyIO.print(isConsoante(palavra) ? " SIM" : " NAO");
            MyIO.print(" ");
            MyIO.print(isInt(palavra) ? " SIM" : " NAO");
            MyIO.print(" ");
            MyIO.println(isFloat(palavra) ? " SIM" : " NAO");

            palavra = MyIO.readLine();
        }

    }
}