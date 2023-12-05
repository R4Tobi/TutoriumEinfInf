package Tutorium.Simulations;
import java.util.Random;
/**
 Klasse, um Zufallsversuche zu simulieren. (Annäherung an PI, Ziegenproblem, Würfeln)
 */
public class MonteCarlo {
    /**
     * Prüft, ob die Argumente richtig sind und führt die gewählte Operation aus.
     * @param args String-Array der Kardinalität 2: erstes Arguments ist die Operation, zweites Argument die Zahl der Durchläufe
     * @throws IllegalArgumentException wenn die Argumente nicht den richtigen Typen haben.
     */
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
            case "pi": {
                System.out.println(approxPi(argInt));
                break;
            }
            case "zonk": {
                zonk(argInt);
                break;
            }
            case "dice": {
                dice(argInt,5,5);
                dice(argInt,5,6);
                break;
            }
            default: {
                System.out.println("No Operation found. Choose from: \n - pi \n - zonk \n - dice");
            }
        }
    }

    /**
     * Funktion um sich der Kreiszahl Pi durch Zufallsversuche anzunähern.
     * @param n Anzahl der Durchläufe
     * @return <code>double</code> - Annäherung an die Zahl Pi
     */

    public static double approxPi (int n){
        int smallerOne = 0;
        for(int index = 0; index < n; index++){
            // x und y zwischen 0 und 1 zufällig bestimmen
            double x = Math.random();
            double y = Math.random();
            //Gleichung für den Einheitskreis, alle werte unter 1 gehören in den Kreis
            if(x*x + y*y < 1){
                smallerOne++;
            }
        }
        //Anzahl der Zahlen im Kreis durch alle getesteten Zahlen
        return ((double) smallerOne / n) * 4;
    }

    /**
     * Wahrscheinlichkeitsbestimmung für das Ziegenproblem oder Monty-Hall-Problem
     * @param n Anzahl der durchläufe um die Wahrscheinlichkeit zu bestimmen
     */
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

        //Ergebnisse auswerten und ausgeben
        double stayWinPercentage = (double) stayed / n * 100;
        double switchWinPercentage = (double) switched / n * 100;

        System.out.println("Results for " + n + " Simulations");
        System.out.println("Stayed: " + stayed);
        System.out.println("Switched: " + switched);
        System.out.println("Stay Win Percentage: " + stayWinPercentage + "%");
        System.out.println("Switch Win Percentage: " + switchWinPercentage + "%");
    }

    /**
     * Gibt über die Konsole aus nach wie vielen Versuchen im Durchschnitt die Folge <code>first</code> -> <code>second</code> auftritt.
     * @param n Anzahl der Durchläufe.
     * @param first Erste Zahl die gewürfelt werden soll.
     * @param second Zweite Zahl die gewürfelt werden soll.
     */
    public static void dice(int n, int first, int second){
        //Array in dem die Schritte bis zum nächsten Paar gespeichert werden
        int[] tries = new int[0];
        int steps = 0;

        for(int index = 0; index < n; index++){
            //Zweimal würfeln
            int throwOne = (int) (Math.random() * 6 + 1);
            int throwTwo = (int) (Math.random() * 6 + 1);
            //steps mit jeder Iteration erhöhen
            steps++;
            if(throwOne == first && throwTwo == second) {
                //Schritte hinzufügen
                tries = pushInt(tries, steps);
                //Schritte wieder auf 0 setzen
                steps = 0;
            }
        }
        //Durchschnittswert bilden (Summe durch Anzahl)
        int summe = 0;
        for(int step : tries){
            summe += step;
        }
        //Ergebnis ausgeben
        System.out.println("Durchschnittliche Versuche bis " + first + "->"  + second +  ": " + ((double) summe/tries.length));
    }

    /* ------------------------------------------------------------*/
    //
    //                     HILFSFUNKTIONEN
    //
    /*______________________________________________________________*/

    private static int[] pushInt(int[] array, int push) {
        //verlängere den Array um eine Position
        int[] longer = new int[array.length + 1];
        //füge die werte dem neuen Array hinzu
        for (int i = 0; i < array.length; i++) longer[i] = array[i];
        //setze den neuen Wert an der letzten Position ein
        longer[array.length] = push;
        return longer;
    }
}
