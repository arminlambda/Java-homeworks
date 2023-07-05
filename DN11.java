import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DN11 {
    static class Kraj {
        private String ime;
        private String kraticaDrzave;

        public Kraj(String ime, String kraticaDrzave) {
            this.ime = ime;
            this.kraticaDrzave = kraticaDrzave;
        }

        public String getIme() {
            return ime;
        }

        public String getKraticaDrzave() {
            return kraticaDrzave;
        }

        @Override
        public String toString() {
            return ime + " (" + kraticaDrzave + ")";
        }
    }

    static abstract class Vlak {
        private String oznaka;
        private Kraj zacetniKraj;
        private Kraj koncniKraj;
        private int trajanjeVoznje;

        public Vlak(String oznaka, Kraj zacetniKraj, Kraj koncniKraj, int trajanjeVoznje) {
            this.oznaka = oznaka;
            this.zacetniKraj = zacetniKraj;
            this.koncniKraj = koncniKraj;
            this.trajanjeVoznje = trajanjeVoznje;
        }

        public String getOznaka() {
            return oznaka;
        }

        public Kraj getZacetniKraj() {
            return zacetniKraj;
        }

        public Kraj getKoncniKraj() {
            return koncniKraj;
        }

        public int getTrajanjeVoznje() {
            return trajanjeVoznje;
        }

        public abstract String opis();

        public abstract double cenaVoznje();

        @Override
        public String toString() {
            return "Vlak " + oznaka + " (" + opis() + ") " + zacetniKraj + " -- " + koncniKraj + " (" + trajanjeVoznje / 60 + "h " + trajanjeVoznje % 60 + " min)";
        }
    }

    static class RegionalniVlak extends Vlak {
        private double ckm;

        public RegionalniVlak(String oznaka, Kraj zacetniKraj, Kraj koncniKraj, int trajanjeVoznje, double ckm) {
            super(oznaka, zacetniKraj, koncniKraj, trajanjeVoznje);
            this.ckm = ckm;
        }

        public String opis() {
            return "regionalni";
        }

        public double cenaVoznje() {
            double c = getTrajanjeVoznje() * 70 / 60 * ckm;
            return c;
        }
    }

    static class EkspresniVlak extends Vlak {
        private double ckm;
        private double doplacilo;

        public EkspresniVlak(String oznaka, Kraj zacetniKraj, Kraj koncniKraj, int trajanjeVoznje, double ckm, double doplacilo) {
            super(oznaka, zacetniKraj, koncniKraj, trajanjeVoznje);
            this.ckm = ckm;
            this.doplacilo = doplacilo;
        }

        public String opis() {
            return "ekspresni";
        }

        public double cenaVoznje() {
            double c = getTrajanjeVoznje() * 100 / 60 * ckm + doplacilo;
            return c;
        }
    }

    static class EuroRail {
        private List<Kraj> kraji;
        private List<Vlak> vlaki;

        public EuroRail() {
            kraji = new ArrayList<>();
            vlaki = new ArrayList<>();
        }

        public void dodajKraj(Kraj kraj) {
            kraji.add(kraj);
        }

        public void dodajVlak(Vlak vlak) {
            vlaki.add(vlak);
        }

        public List<Vlak> getVlaki() {
            return vlaki;
        }
    }

    public static void main(String[] args) {
        EuroRail euroRail = new EuroRail(); 
        File f1 = new File(args[1]); 
        File f2 = new File(args[2]); 

        try {
           
            BufferedReader brKraji = new BufferedReader(new FileReader(f1));
String line;
Set<String> uniqueNames = new HashSet<>();

while ((line = brKraji.readLine()) != null) {
    String[] data = line.split(";");
    if (data.length == 2) {
        String ime = data[0];
        String kraticaDrzave = data[1];
        
        if (!uniqueNames.contains(ime)) {
            uniqueNames.add(ime); 
            
            Kraj kraj = new Kraj(ime, kraticaDrzave);
            euroRail.dodajKraj(kraj);
        }
    }
}

brKraji.close();

         
            BufferedReader brPovezave = new BufferedReader(new FileReader(f2));

            while ((line = brPovezave.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 5) { 
                    String oznakaVlaka = data[0];
                    String zacetniKrajIme = data[1];
                    String koncniKrajIme = data[2];
                    int trajanjeVoznje=0;
                    if (data[3].contains(".")) {
                        String[] timeParts = data[3].split("\\.");
                     trajanjeVoznje=Integer.parseInt(timeParts[0])*60+Integer.parseInt(timeParts[1]);
                    } else {
                        trajanjeVoznje=Integer.parseInt(data[3]);
                    }
                    
                    double doplacilo = Double.parseDouble(data[4]);
            
            
                    Kraj zacetniKraj = null;
                    Kraj koncniKraj = null;
                    List<Kraj> kraji = euroRail.getKraji();
                    for (Kraj kraj : kraji) {
                        if (kraj.getIme().equals(zacetniKrajIme)) {
                            zacetniKraj = kraj;
                        }
                        if (kraj.getIme().equals(koncniKrajIme)) {
                            koncniKraj = kraj;
                        }
                    }
            
                    if (zacetniKraj != null && koncniKraj != null) {
                        EkspresniVlak vlak = new EkspresniVlak(oznakaVlaka, zacetniKraj, koncniKraj, trajanjeVoznje, doplacilo);
                        euroRail.dodajVlak(vlak);
                    }
                }
                else if (data.length==4){
                    String oznakaVlaka = data[0];
                    String zacetniKrajIme = data[1];
                    String koncniKrajIme = data[2];
                    int trajanjeVoznje=0;
                    if (data[3].contains(".")) {
                        String[] timeParts = data[3].split("\\.");
                        if(timeParts.length==2){
                     trajanjeVoznje=Integer.parseInt(timeParts[0])*60+Integer.parseInt(timeParts[1]);
                    } }else {
                        trajanjeVoznje=Integer.parseInt(data[3]);
                    }
                    
                    Kraj zacetniKraj = null;
                    Kraj koncniKraj = null;
                    List<Kraj> kraji = euroRail.getKraji();
                    for (Kraj kraj : kraji) {
                        if (kraj.getIme().equals(zacetniKrajIme)) {

                            zacetniKraj = kraj;
                        }
                        if (kraj.getIme().equals(koncniKrajIme)) {
                            koncniKraj = kraj;
                        }
                    }
            
                    if (zacetniKraj != null && koncniKraj != null&& !(oznakaVlaka.equals("RG0000"))) {
                        RegionalniVlak vlak = new RegionalniVlak(oznakaVlaka, zacetniKraj, koncniKraj, trajanjeVoznje, trajanjeVoznje);
                        euroRail.dodajVlak(vlak);
                    }
                }
            }
            brPovezave.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        switch(args[0])
        {
            case"1":{
                List<Kraj> kraji = euroRail.getKraji();
                List<Vlak> vlaki = euroRail.getVlaki();
        
                System.out.println("Kraji, povezani z vlaki:");
                for (Kraj kraj : kraji) {
                    System.out.println(kraj);
                }
                System.out.println();
                System.out.println("Vlaki, ki povezujejo kraje:");
                for (Vlak vlak : vlaki) {
                    System.out.println(vlak);
                }
            }
            break;
        }
       
    }
}