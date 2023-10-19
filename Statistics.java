import java.util.*;

import static java.lang.Integer.parseInt;

public class Statistics {
    public static void main(String[] args){
        //Kontrollieren ob einer der Parameter kein Integer ist
        for (String arg : args) {
            try {
                Integer.parseInt(arg);
            } catch (final NumberFormatException e) {
                System.out.println("The Parameter \"" + arg + "\" is not an integer.");
            }
        }

        
    }
}