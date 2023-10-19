//global imports

//local imports
import Utils.*;

import java.util.Arrays;

import static Utils.Push.pushInt;

public class Sieve {
    public static void main(String[] args){
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
    }

    public static int[] eratosthenesSieve(int n){
        //Erstellen eines neuen Arrays mit dem Inhalt von 0 bis n
        int[] array = new int[n];
        for(int index = 0; index < n; index++){
            array[index] = index;
        }
        //entferne jede nummer aus dem array die keine Primzahl ist
        for(int jndex = 0; jndex < n; jndex++){
            if(array[jndex] < 2){
                array[jndex] = 0;
                continue;
            }
            for(int kndex = 2; kndex <= Math.sqrt(array[jndex]); kndex++){
                if (array[jndex] % kndex == 0) {
                    array[jndex] = 0;
                    break;
                }
            }
        }
        int[] returnArr = new int[0];
        for(int entry : array){
            if(entry != 0){
                returnArr = pushInt(returnArr, entry);
            }
        }
        return returnArr;
    }
}
