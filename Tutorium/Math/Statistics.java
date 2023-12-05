package Tutorium.Math;
/**
 * Klasse, die Methoden zur Statistik enthält.
 */
public class Statistics {
    /**
     * Prüft, ob alle Argumente Integer sind, und führt alle Operationen aus.
     * @param args Array der Strings enthält.
     */
    public static void main(String[] args){
        //Kontrollieren ob einer der Parameter ein Integer ist
        for (String arg : args) {
            try {
                Integer.parseInt(arg);
            } catch (final NumberFormatException e) {
                System.out.println("The Parameter \"" + arg + "\" is not an integer.");
                return;
            }
        }

        //Alle String Parameter mit dem Paket Utils in Integer Umwandeln
        int[] intArgs = StringArrToIntArr(args);

        //Ausgabe der Summe
        System.out.println("Summe: " + summe(intArgs));

        //Ausgabe des Mittelwerts
        System.out.println("Mittelwert: " + mittelwert(intArgs));

        //Ausgabe der Varianz
        System.out.println("Varianz: " + varianz(intArgs));

        //Ausgabe der Standardabweichung
        System.out.println("Standardabweichung " + standardabweichung(intArgs));

        //Ausgabe des Histogramms
        System.out.println("Histogramm: \n" + histogramm(intArgs));
    }

    /**
     * Berechnet die Summe aller Zahlen in einem Integer-Array.
     * @param numbers Array der aus Integern besteht
     * @return <code>int</code> - Summe
     */
    public static int summe(int[] numbers){
        int summe = 0;
        //summiere jeden Eintrag aus dem Array auf die Summe
        for(int number :numbers){
            summe += number;
        }
        return summe;
    }

    /**
     * Berechnet den Mittelwert anhand der Summe der Zahlen und der Länge des Arrays.
     * @param numbers Array aus Integern.
     * @return <code>double</code> - Mittelwert der Zahlen im Array als Gleitkommazahl.
     */
    public static double mittelwert(int[] numbers){
        //Berechnen des arithmetischen Mittels durch die Summe der Zahlen und der Anzahl der Einträge
        int summe = summe(numbers);
        return ((double) summe / numbers.length);
    }

    /**
     * Berechnet die Varianz nach der Formel: 1/n * Ʃ(n, i = 1) (x<sub>i</sub> - ˉx)<sup>2</sup>
     * @param numbers Array, aus dem der Mittelwert erzeugt werden soll.
     * @return <code>double</code> - Varianz als Gleitkommazahl.
     */
    public static double varianz(int[] numbers){
        double mittelwert = mittelwert(numbers);
        double varianz = 0;
        //Die Schleife berechnet die Summe(Iteration) der Formel
        for(int number : numbers){
            varianz += (Math.pow(number - mittelwert, 2));
        }
        //Berechnet den vorderen Teil der Gleichung 1/(n-1)
        varianz /= (numbers.length - 1);

        return varianz;
    }

    /**
     * Berechnet die Standardabweichung, als die Quadratwurzel der Varianz
     * @param numbers Array, aus dem die Standardabweichung erzeugt werden soll.
     * @return <code>double</code> - Standardabweichung
     */
    public static double standardabweichung(int[] numbers){
        return (Math.sqrt(varianz(numbers)));
    }

    /**
    * Gibt ein Histogramm in der Konsole aus.
    * @param numbers Array, in dem die Zahlen stehen, die im Histogramm ausgewertet werden sollen.
    * @return <code>String</code> - formatiertes Histogramm als String
    * */
    public static String histogramm(int[] numbers){
        int highest = numbers[0];
        int lowest = numbers[0];

        for(int number : numbers){
            if(highest < number){
                highest = number;
            }
            if(lowest > number){
                lowest = number;
            }
        }
        //Erstelle einen neuen Array der die Länge des Intervalls der Nummern hat. Mit +1 wird der Index auf die tatsächliche Länge angepasst.
        int length = (highest-lowest) + 1;
        int[] entries = new int[length];

        //Erhöhen der werte in den entries. Index entspricht dem Wert im Intervall
        for(int number : numbers){
            entries[number-lowest]++;
        }

        //Erstellen des Histogramms
        String histogramm = "";
        //für jeden Eintrag in entries wird ein neuer Balken erstellt, der die Länge des Wertes des Eintrags hat
        for(int index = 0;index < entries.length; index++){
            //hash stellt die Länge des Balkens dar
            String hash = "";
            for(int jndex = 0; jndex < entries[index];jndex++){
                hash += "#";
            }
            //gucken das die zahlen am anfang das histogramm nicht verfälschen
            StringBuilder indexString = new StringBuilder((index + lowest) + ":");
            while(indexString.length() <= 4){
                indexString.append(" ");
            }
            //neue Zeile im Histogramm
            histogramm += indexString + hash + "\n";
        }
        return histogramm;
    }

    /**
     * Konvertiert ein String-Array in ein Integer-Array (Hilfsfunktion)
     * @param string String-Array der (!) nachweislich nur "Integer" enthält
     * @return <code>int[]</code>
     */
    public static int[] StringArrToIntArr(String[] string) {
        int[] result = new int[string.length];
        //jeder Eintrag im String Array wird zu einem Integer umgewandelt
        for (int i = 0; i < string.length; i++) {
            result[i] = Integer.parseInt(string[i]);
        }
        return result;
    }
}