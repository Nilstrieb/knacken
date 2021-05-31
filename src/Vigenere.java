public class Vigenere {

    public static void main(String[] args) {
        System.out.println(encode("die vigenere verschluesselung ist ein " +
                "polyalphabetisches Verfahren, das wesentlich" +
                "sicherer ist als die monoalphabetischen Verfahren" +
                "wie etwa die caesar verschluesselung.", "OBWALDEN"));
    }


    private static final char[][] SQUARE;

    static {
        SQUARE = new char[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                SQUARE[i][j] = (char) ('A' + ((j + i) % 26));
            }
        }
    }

    private static String encode(String text, String secret) {
        final String preprocessedText = text.toUpperCase().replaceAll("\\W", "");
        final String secretRepeat = secret.repeat(text.length() / secret.length() + 1).toUpperCase();
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < preprocessedText.length(); i++) {
            final char c = preprocessedText.charAt(i);
            final char s = secretRepeat.charAt(i);

            final char[] cypher = SQUARE[s - 'A'];
            final char encoded = cypher[c - 'A'];
            result.append(encoded);
        }
        return result.toString();
    }
}
