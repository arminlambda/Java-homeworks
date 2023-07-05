public class DN05 {
    public static void main(String[] args) {

        // vse argumente podamo v niz
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg).append(" ");
        }
        String niz = sb.toString().trim();  // trim odstrani dodaten whitespace na koncu

        //naredimo tabelo za morebitne stevke
        int[] stevke = new int[10];

        //preveri za znak ce je stevilk 
        for (int i = 0; i < niz.length(); i++) {
            char c = niz.charAt(i);
            if (Character.isDigit(c)) {
                int stevka = Character.getNumericValue(c);
                stevke[stevka]++; //poveca vrednost elementa v tabeli
            }
        }

        // preveri stevilke
        boolean najdeneStevke = false;
        for (int stevka : stevke) {
            if (stevka > 0) {
                najdeneStevke = true;
                break;
            }
        }   if (najdeneStevke == false) {  // fix typo
                System.out.printf("V nizu '%s' ni stevk", niz);
                return;
        }

        // najdi najpogostejsoS
        int najpogostejsa = 0;
        for (int stevka : stevke) {
            if (stevka > najpogostejsa) {
                najpogostejsa = stevka;
            }
        }

        //izpis
        System.out.print("'" + niz + "' -> ");
        for (int i = 0; i < stevke.length; i++) {
            if (stevke[i] == najpogostejsa) {
                System.out.print(i + " ");
            }
        }
        System.out.printf("(%d)", najpogostejsa);
    }
}







