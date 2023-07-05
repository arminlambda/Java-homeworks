import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Planet {
    String ime;
    int radij;

    public Planet(String ime, int radij) {
        this.ime = ime;
        this.radij = radij;
    }

    public double povrsina() {
        return 4 * Math.PI * Math.pow(radij, 2);
    }
}

public class DN08 {

    public static Planet[] preberiPlanete(String imeDatoteke) throws FileNotFoundException {
        Planet[] planeti = new Planet[8];
        Scanner sc = new Scanner(new File(imeDatoteke));
        int i = 0;
        while (sc.hasNextLine() && i < 8) {
            String vrstica = sc.nextLine();
            String[] podatki = vrstica.split(":");
            planeti[i] = new Planet(podatki[0], Integer.parseInt(podatki[1]));
            i++;
        }
        sc.close();
        return planeti;
    }

    public static double izracunajSkupnoPovrsino(Planet[] planeti, String planetiNizi) {
        double povrsina = 0;
        String[] niziPlanetov = planetiNizi.split("\\+");
        for (String niz : niziPlanetov) {
            for (Planet planet : planeti) {
                if (planet != null && planet.ime.equalsIgnoreCase(niz.trim())) {
                    povrsina += planet.povrsina();
                    break;
                }
            }
        }
        return povrsina;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Planet[] planeti = preberiPlanete(args[0]);
        String planetiNizi = args[1];
        double skupnaPovrsina = izracunajSkupnoPovrsino(planeti, planetiNizi);
        System.out.printf("Povrsina planetov \"%s\" je %.0f milijonov km2\n", planetiNizi, skupnaPovrsina / 1000000);
    }
}
