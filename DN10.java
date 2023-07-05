import java.util.*;

//java DN10 abcabc ababcd

public class DN10 {
    public static void main(String[] args) {
        

        TreeSet<String> skupniPodnizi = getVsiPodnizi(args[0]);

        for (int i = 1; i < args.length; i++) {
            TreeSet<String> substrings = getVsiPodnizi(args[i]);
            skupniPodnizi.retainAll(substrings);
        }

        String naj = "";
        for (String s : skupniPodnizi) {
            if (s.length() > naj.length()) {
                naj = s;
            }
        }

        System.out.println(naj);
    }

    public static TreeSet<String> getVsiPodnizi(String niz) {
        TreeSet<String> podnizi = new TreeSet<String>();
        for (int i = 0; i < niz.length(); i++) {
            for (int j = i + 1; j <= niz.length(); j++) {
                podnizi.add(niz.substring(i, j));
            }
        }
        return podnizi;
    }
}
