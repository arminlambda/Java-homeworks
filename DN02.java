public class DN02 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Napaka pri uporabi programa!");
            return;

        }

        String besede = String.join(" ", args);
        int dolzina = besede.length() + 4;

        String frame = "*";
        for (int i = 0; i < dolzina - 1; i++) {
            frame += "*";

        }

        System.out.println(frame);
        System.out.println("* " + besede + " *");
        System.out.println(frame);

        
    }
}
