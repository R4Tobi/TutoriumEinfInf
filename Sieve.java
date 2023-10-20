import java.util.Arrays;
import java.util.Date;

public class Sieve {
    public static void main(String[] args){
        //Für die Execution-Zeit
        long time = new Date().getTime();

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
        if(Integer.parseInt(args[0]) < 0){
            System.out.println("The parameter can't be negative.");
            return;
        }

        //Gebe alle Primzahlen bis zum gewünschten Parameter aus
        System.out.println("Alle Primzahlen bis " + args[0] + ": " + Arrays.toString(eratosthenesSieve(Integer.parseInt(args[0]))));

        //Execution Zeit berechnen und ausgeben
        System.out.println("Execution time: " + (new Date().getTime() - time) + "ms");
    }

    public static int[] eratosthenesSieve(int n){
        //Erstellen eines neuen Arrays mit dem Inhalt von 0 bis n
        int[] array = new int[n];
        for(int index = 2; index < n; index++){
            array[index] = index;
        }
        System.out.println(Arrays.toString(array));
        //Ende der Schleife ist erreicht, wenn die hälfte aller möglichen operationen erreicht ist (1*9 = 9*1), also reicht die Wurzel der höchsten zahl als Anzahl der Durchgänge
        double end = Math.sqrt(n);
        //Zählerschleife für dividenden
        for(int jndex = 2; jndex < end; jndex++){
            //Schleife für Indizes vom Array
            for(int index = 0; index < n; index++){
                //setze jede zahl aus dem array 0, die durch den divisor, aber nicht durch sich selbst teilbar ist
                if(array[index] % jndex == 0 && array[index] != jndex){
                    array[index] = 0;
                }
            }
            //Ausgabe des gefilterten Arrays zur Kontrolle
            System.out.println(Arrays.toString(array));
        }

        int[] returnArr = new int[0];
        for(int entry : array){
            if(entry != 0){
                returnArr = pushInt(returnArr, entry);
            }
        }
        return returnArr;
    }

    public static int[] pushInt(int[] array, int push) {
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
}
