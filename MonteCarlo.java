import java.util.Arrays;
import java.util.Random;

public class MonteCarlo {

    public static void main (String[] args){
        /*
        *
        *             Überprüfen der Argumente
        *               - 2 Argumente gegeben
        *               - 2. Arguments ist vom Typ Int und ist positiv
        *
        * */
        if(args.length != 2){
            throw new IllegalArgumentException("2 Arguments Expected. " + args.length + " Arguments given.");
        }
        try {
            Integer.parseInt(args[1]);
        } catch (final NumberFormatException e) {
            System.out.println("The Parameter \"" + args[1] + "\" is not an integer.");
            return;
        }
        if(Integer.parseInt(args[1]) <= 0){
            throw new IllegalArgumentException("Second Argument is supposed to be a positive number.");
        }

        //Argumente in feste Variablen schreiben und in den richtigen Typen umwandeln
        String argOperation = args[0];
        int argInt = Integer.parseInt(args[1]);

        //Aus dem ersten Argument die Operation bestimmen
        switch(argOperation){
            case "pi" -> System.out.println(approxPi(argInt));
            case "zonk" -> zonk(argInt);
            case "dice" -> System.out.println("Würfel");
            default -> System.out.println("No Operation found. Choose from: \n - pi \n - zonk \n - dice");
        }
    }

    public static double approxPi (int n){
        int smallerOne = 0;
        for(int index = 0; index < n; index++){
            double x = Math.random();
            double y = Math.random();
            if(x*x + y*y < 1){
                smallerOne++;
            }
        }
        return ((double) smallerOne / n) * 4;
    }

    public static void zonk(int n) {;
        int stayed = 0;
        int switched = 0;

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            //Preis und die Wahl des Spielers festlegen
            int prizeDoor = random.nextInt(3);
            int playerChoice = random.nextInt(3);

            //eine der Türen zufällig öffnen
            int revealedDoor;
            do {
                revealedDoor = random.nextInt(3);
            } while (revealedDoor == prizeDoor || revealedDoor == playerChoice);

            //Eine zufällige Tür auswählen, die anders ist als die geöffnete Tür und die ausgewählte tür ist
            int switchChoice;
            do {
                switchChoice = random.nextInt(3);
            } while (switchChoice == playerChoice || switchChoice == revealedDoor);

            //Das Ergebnis in die Variablen setzen
            if (playerChoice == prizeDoor) {
                stayed++;
            } else if (switchChoice == prizeDoor) {
                switched++;
            }
        }

        //Ergebnisse auswerten
        double stayWinPercentage = (double) stayed / n * 100;
        double switchWinPercentage = (double) switched / n * 100;

        System.out.println("Results for " + n + " Simulations");
        System.out.println("Stayed: " + stayed);
        System.out.println("Switched: " + switched);
        System.out.println("Stay Win Percentage: " + stayWinPercentage + "%");
        System.out.println("Switch Win Percentage: " + switchWinPercentage + "%");
    }
}
