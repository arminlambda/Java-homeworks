    import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;

public class DN07 {

    public static void main(String[] args) {

        int kakoIzvede = Integer.parseInt(args[0]);
        File dir = new File(args[1]);

        switch (kakoIzvede) {
            case 1:
                izpisi_datoteke(dir);
                break;
            case 2:
                najvecja_datoteka(dir);
                break;
            case 3:
                // izpis_vsebine(dir, Integer.parseInt(args[2]));
                break;
            case 4:

                break;
            case 5:
                /* zdruzi_datoteko(f, args[2]); */
                break;
            case 6:

                break;
            case 7:
                drevo(dir);
                break;
            case 8:

                break;
            case 9:

                break;

        }
    }

    public static void izpisi_datoteke(File f) {

        File[] listOfFiles = f.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            long bytes = listOfFiles[i].length();
            double kilobytes = bytes / (double) 1000;
            if (listOfFiles[i].isFile()) {
                System.out.printf("%20s %20s  %8.3f\n", listOfFiles[i].getName(), "Datoteka", kilobytes);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.printf("%20s %20s  %8.3f\n", listOfFiles[i].getName(), "Mapa", kilobytes);
            }
        }
    }

    public static void najvecja_datoteka(File f) {

        File[] datoteke = f.listFiles(File::isFile);

        if (datoteke == null || datoteke.length == 0) {
            return;
        }

        File najvecja = datoteke[0];
        File najmanjsa = datoteke[0];

        for (File datoteka : datoteke) {
            if (datoteka.length() > najvecja.length()) {
                najvecja = datoteka;
            }

            if (datoteka.length() < najmanjsa.length()) {
                najmanjsa = datoteka;
            }
        }

        double najvecjaVelikostKB = najvecja.length() / 1000.0;
        double najmanjsaVelikostKB = najmanjsa.length() / 1000.0;

        System.out.printf("%s %.3f%n", najvecja.getName(), najvecjaVelikostKB);
        System.out.printf("%s %.3f%n", najmanjsa.getName(), najmanjsaVelikostKB);

    }

    /*public void izpis_vsebine(File f, int n) {
        File[] listOfFiles = f.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                Scanner sc = new Scanner();
            } else {
                System.out.println("ni tekstovna datoteka");
            }
        }
    }*/


    /*static void drevo(File f){
        drevoHelper(f, "");
    }
    
    static void drevoHelper(File f, String indent){
        File[] listOfFiles = f.listFiles();
        System.out.println(indent+"/"+f.getName());
        for (File file : listOfFiles) {
            if(file.isFile()){
                System.out.println(indent+"    "+file.getName());
            }
            if(file.isDirectory()){
                drevoHelper(file, indent + "    ");
            }
        }
    }*/

    static void drevo(File f) {
        drevoH(f, "");
    }
    
    
    static void drevoH(File f, String indent) {
        File[] listOfFiles = f.listFiles();
        
        System.out.println(indent + "/" + f.getName());
        
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(indent + "    " + file.getName());
            }
            if (file.isDirectory()) {
                
                drevoH(file, indent + "    ");
            }
        }
    }

}
