public class DN06 {

        public static void main(String[] args) {
            String niz = args[0];
            int kontrolnaVsota = bsdChecksum(niz);
            String nsn = nsnediv(niz, kontrolnaVsota);
            System.out.println(nsn);
        }
        static int bsdChecksum(String niz) {    
            int checksum = 0;
            for (int i = 0; i < niz.length(); i++) {
                checksum = (checksum >> 1) + ((checksum & 1) << 15);
                checksum += niz.charAt(i);
                checksum &= 0xffff;       
            }
            return checksum;
        }
        static String nsnediv(String niz, int vsota) {
            String najmanjsiNiz = "";
            int dolzina = niz.length();
            for (int i = 0; i < dolzina; i++) {
                najmanjsiNiz += "a";
            }
            int najmanjsaVsota = bsdChecksum(najmanjsiNiz);
            while (najmanjsaVsota != vsota) {
                najmanjsiNiz = povecaj(najmanjsiNiz);
                najmanjsaVsota = bsdChecksum(najmanjsiNiz);
            }
            return najmanjsiNiz;
        }
        static String povecaj(String niz) {
            int dolzina = niz.length();
            int a = dolzina - 1;
            while (a >= 0 && niz.charAt(a) == 'z') {
                a--;
            }
            if (a < 0) {
                return "a" + niz;
            } else {
                char zadnjiZnak = niz.charAt(a);
                char noviZnak = (char) (zadnjiZnak + 1);
                String nc = "";
                for (int i = a + 1; i < dolzina; i++) {
                    nc += "a";
                }
                return niz.substring(0, a) + noviZnak + nc;
            }
        }
    }
    


