package Tutorium.Math;

import java.util.Arrays;

/**
 * Klasse, die zur Bestimmung der Primfaktoren einer Zahl verwendet werden kann.
 * */
public class Factorization {
    /**
     * Prüft, ob der Parameter ein positiver Integer ist und gibt die Primfaktoren aus
     * @param args String-Array
     */
    public static void main(String[] args){
        //Kontrollieren des Parameters
        if(args.length != 1){
            System.out.println("1 Argument expected. " + args.length + " Arguments given.");
            return;
        }
        try {
            Integer.parseInt(args[0]);
        } catch (final NumberFormatException e) {
            System.out.println("The Parameter \"" + args[0] + "\" is not an integer.");
            return;
        }
        if(Integer.parseInt(args[0]) <= 0){
            System.out.println("The parameter can't be negative or zero.");
            return;
        }

        //Gebe alle Primzahlen bis zum gewünschten Parameter aus
        System.out.println("Die Zahl " + args[0] + " lässt sich in die folgenden Primfaktor zerlegen: " + Arrays.toString(primeFactors(Integer.parseInt(args[0]))));
    }

    /**
     * Wird verwendet, um die Primfaktoren des Parameters zu erhalten
     * @param number Zahl, für die wir die Primfaktoren benötigen
     * @return <code>int[]</code> - die Primfaktoren der Zahl
     */
    public static int[] primeFactors(int number){
        int[] result = new int[0];
        int[] primes = primesUntil(number);
        int remainder = number;

        //Die Schleife wird so lange ausgeführt, bis der Rest der Division kleiner oder gleich eins ist.
        while (remainder > 1){
            //gehe jede Primzahl durch
            for(int prime : primes){
                //Damit die Primzahl passt, muss der Modulo-Wert 0 sein
                if(remainder % prime == 0){
                    result = pushInt(result, prime);
                    remainder /= prime;
                }
            }
        }
        Arrays.sort(result);
        return result;
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
        for (int i = 0; i < array.length; i++){
            longer[i] = array[i];
        }
        //setze den neuen Wert an der letzten Position ein
        longer[array.length] = push;
        return longer;
    }


    private static int[] primesUntil(int n) {
        //Erstellen eines neuen Arrays mit dem Inhalt von 0 bis n
        int[] array = new int[n];
        for(int index = 2; index < n; index++){
            array[index] = index;
        }
        //entferne jede nummer aus dem array die keine Primzahl ist
        double end = Math.sqrt(n);

        for(int jndex = 2; jndex < end; jndex++){
            for(int index = 0; index < n; index++){
                if(array[index] % jndex == 0 && array[index] != jndex){
                    array[index] = 0;
                }
            }
        }

        int[] returnArr = new int[0];
        for(int entry : array) {
            if (entry != 0) {
                returnArr = pushInt(returnArr, entry);
            }
        }
        return returnArr;
    }
}
