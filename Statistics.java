//global imports

//local imports
import Utils.*;

public class Statistics {
    public static void main(String[] args){
        //Kontrollieren ob einer der Parameter kein Integer ist
        for (String arg : args) {
            try {
                Integer.parseInt(arg);
            } catch (final NumberFormatException e) {
                System.out.println("The Parameter \"" + arg + "\" is not an integer.");
                return;
            }
        }

        //Alle String Parameter mit dem Paket Utils in Integer Umwandeln
        int[] intArgs = Convert.StringArrToIntArr(args);

        //Ausgabe der Summe
        System.out.println("Summe: " + summe(intArgs));

        //Ausgabe des Mittelwerts
        System.out.println("Mittelwert: " + mittelwert(intArgs));

        //Ausabe der Varianz
        System.out.println("Varianz: " + varianz(intArgs));
    }

    public static int summe(int[] numbers){
        int summe = 0;
        //summiere jeden Eintrag aus dem Array auf die Summe
        for(int number :numbers){
            summe += number;
        }
        return summe;
    }

    public static double mittelwert(int[] numbers){
        //Berechnen des aritmetischen Mittels durch die Summe der Zahlen und der Anzahl der Einträge
        int summe = summe(numbers);
        return ((double) summe / numbers.length);
    }

    public static double varianz(int[] numbers){
        double mittelwert = mittelwert(numbers);
        double varianz = 0;
        for(int number : numbers){
            varianz += (Math.pow(number - mittelwert, 2));
        }
        varianz /= (numbers.length - 1);

        return varianz;
    }

}