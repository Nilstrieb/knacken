import java.util.*;
import java.util.stream.Collectors;

public class Caesar {
    public static void main(String[] args) {
        // System.out.println(normal("dies ist die erste uebung zum verschluesseln von mitteilungen", 9));

        // System.out.println(withAlphabet("die verschluesselung mit dem kunterbunten geheimalphabet",
        //          "LPAIQJBCTRWZYDSKEGFXHUONVM"));

        // System.out.println(keyword("die matura wird nach sechs jahren gymnasium abgelegt", "kantonsschule"));

        System.out.println(knacken("""
                EHSSC NMVVPS FU YWP VM UVHSV EFU OFOVE CPHS HV EMTZHSVU
                UREMMJ MO ZFVRERSHOV HLY ZFDHSYSC. EMZPXPS, ZEHV EHSSC FU
                HAMWV VM YFURMXPS FL EFU LPZ CPHS HV EMTZHSVU ZFJJ VWSL EFU
                ZMSJY WNUFYP YMZL…"""));
        /*knacken2("""
                XDY IKJKAHMCALYQDPECY SYOPECHRYPPYHRJB DPQ DI WYDQAHQYO XYP
                EKIMRQYOP JDECQ IYCO AGQRYHH. PDY DPQ SDYH WR YDJZAEC WR
                GJAEGYJ RJX PKIDQ ZRYO XYJ PDECYOYJ XAQYJARPQARPEC JDECQ
                BYYDBJYQ""");*/
    }

    static String normal(String s, int shift) {
        return s.toLowerCase().chars()
                .map(c -> {
                    if (isAlpha(c)) {
                        int n = c + shift;
                        if (n > Z) {
                            n = (96 + (n - Z));
                        }
                        return n;
                    }
                    return c;
                })
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }

    static String withAlphabet(String s, String alphabet) {
        final String a = alphabet.toLowerCase();
        return s.toLowerCase().chars()
                .map(c -> {
                    if (isAlpha(c)) {
                        int index = c - A;
                        return a.charAt(index);
                    }
                    return c;
                })
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }

    private static final char Z = 122;
    private static final char A = 97;

    static String keyword(String s, String keyword) {
        StringBuilder a = new StringBuilder();
        for (char c : keyword.toCharArray()) {
            if (!a.toString().contains(String.valueOf(c))) {
                a.append(c);
            }
        }
        int last = keyword.charAt(keyword.length() - 1);
        for (int i = last + 1; i <= Z; i++) {
            if (!a.toString().contains(String.valueOf((char) i))) {
                a.append((char) i);
            }
        }
        for (int i = A; i < last; i++) {
            if (!a.toString().contains(String.valueOf((char) i))) {
                a.append((char) i);
            }
        }

        return withAlphabet(s, a.toString());
    }

    private static final String BUCHSTABEN = "enirsatdhulgocmbfwkzpvjyxq";
    private static final String LETTERS = "etaoinsrhldcumfpgwybvkxjqz";

    static String knacken(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();

        s.toLowerCase().chars()
                .filter(Caesar::isAlpha)
                .forEach(c -> chars.merge((char) c, 1, Integer::sum));

        // list an allen chars sortiert nach reihenfolge
        List<Character> list = chars.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .toList();

        for (char character : s.toCharArray()) {
            if (list.contains((character))) {
                System.err.println("wtf " + character);
            }
        }

        List<Character> expected = new ArrayList<>();
        LETTERS.chars().forEach(c -> expected.add((char) c));


        return s.toLowerCase().chars()
                .map(c -> {
                    if (!isAlpha(c)) {
                        return c;
                    }
                    return expected.get(list.indexOf((char) c));
                })
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }

    static void knacken2(String s) {
        for (int i = 0; i < 26; i++) {
            System.out.println(normal(s, i));
        }
    }


    static boolean isAlpha(int c) {
        return c <= 122 && c >= 97;
    }
}
