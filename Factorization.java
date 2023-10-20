import java.util.Arrays;
import java.util.Date;

public class Factorization {
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
        if(Integer.parseInt(args[0]) <= 0){
            System.out.println("The parameter can't be negative or zero.");
            return;
        }

        //Gebe alle Primzahlen bis zum gewünschten Parameter aus
        System.out.println("Die Zahl " + args[0] + "lässt sich in die folgenden Primfaktor zerlegen: ");

        //Execution Zeit berechnen und ausgeben
        System.out.println("Execution time: " + (new Date().getTime() - time) + "ms");
    }

    public static int[] primeFactors(int number){
        return new int[0];
    }
}
