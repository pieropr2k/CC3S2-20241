package org.example;

public class LeftPadUtils {
    private static final String SPACE = " ";
    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    /**
     * @param str
     * @param size
     * @param padStr
     * @return left-padded string or {@code null}
     */
    // Para ver la algoritmia del programa en accion es recomendable que size sea mayor al tamaño de str
    public static String leftPad(final String str, final int size, String padStr) {
        // Verifica si el string str es nulo, si lo es se retorna null
        if (str == null) { // 1
            return null;
        }
        // Verifica si el string *padStr* es vacio, si lo es *padStr* sera " "
        if (isEmpty(padStr)) { // 2
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        // Si size es menor o igual que el tamaño de str entonces se returna str
        if (pads <= 0) { // 3
            return str;
        }
        // Si pads es igual al tamaño de padStr entonces se retorna la union de padStr con str
        if (pads == padLen) { // 4
            return padStr.concat(str);
        // Si pads es menor al tamaño de padStr entonces se retorna la union del substring de padStr con str
        } else if (pads < padLen) { // 5
            return padStr.substring(0, pads).concat(str);
        // Si pads es mayor a padLen (pads > padLen) entonces se retorna
        // el string *padding* el cual contiene letras de *padStr* se unido con *str*
        } else { // 6
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }
}