import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Adfgvx {
    public static void main(String[] args) {
        System.out.println(encode("neuen bereitschaftsraum in planquadrat x24", "hinterhalt", "beobachtungsposten"));
    }

    private static String encode(String text, String subKeyword, String transKeyword) {
        String plainText = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        List<Character> matrix = fillMatrix(subKeyword);

        String substituted = plainText.chars()
                .mapToObj(c -> mapToSub(c, matrix))
                .collect(Collectors.joining());

        List<List<Character>> transMatrix = toTransMatrix(substituted, transKeyword);

        System.out.println(transMatrix);

        System.out.println(substituted);
        return "";
    }

    static List<List<Character>> toTransMatrix(String text, String keyword) {
        var cols = keyword.chars()
                .mapToObj(i -> {
                    List<Character> l = new ArrayList<>();
                    l.add((char) (i));
                    return l;
                })
                .toList();

        System.out.println(keyword.length());

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i) ;
            cols.get(i % keyword.length()).add(c);
        }

        return cols;
    }

    static final char[] ADFGVX = new char[]{'a', 'd', 'f', 'g', 'v', 'x'};

    static String mapToSub(int character, List<Character> matrix) {
        int index = matrix.indexOf((char) character);
        char first = ADFGVX[index / 6];
        char second = ADFGVX[index % 6];
        return "" + first + second;
    }

    static List<Character> fillMatrix(String keyword) {
        List<Character> matrix = new ArrayList<>(36);

        Set<Character> chars = new HashSet<>();

        keyword.chars()
                .filter(c -> {
                    if (chars.contains((char) c)) return false;
                    chars.add((char) c);
                    return true;
                })
                .mapToObj(c -> (char) c)
                .forEach(matrix::add);


        for (char i = 'a'; i <= 'z'; i++) {
            if (!chars.contains(i)) {
                matrix.add(i);
            }
        }

        for (char i = '0'; i <= '9'; i++) {
            if (!chars.contains(i)) {
                matrix.add(i);
            }
        }
        return matrix;
    }
}
