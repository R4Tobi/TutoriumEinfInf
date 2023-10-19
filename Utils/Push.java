package Utils;

public class Push {

    public static int[] pushInt(int[] array, int push) {
        //verlängere den Array um eine Position
        int[] longer = new int[array.length + 1];
        //füge die werte dem neuen Array hinzu
        for (int i = 0; i < array.length; i++) longer[i] = array[i];
        //setze den neuen Wert an der letzten Position ein
        longer[array.length] = push;
        return longer;
    }
}
