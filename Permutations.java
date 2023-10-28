import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generatePermutations(args)));
    }
    public static String[] generatePermutations(String[] array) {
        //ArrayList erstellen
        List<String[]> permutations = new ArrayList<>();

        //Anzahl der schleifendurchläufe
        int n = array.length;
        //Erstellen eines Arrays der Länge n für Permutationen und Kontrolle dieser in der Schleife
        int[] c = new int[n];

        //Array aus dem Argument in die Liste für Permutationen Einsetzen
        permutations.add(Arrays.copyOf(array, n));

        int index = 0;
        /*
        HEAP's-Algorithmus:
        - Der Algorithmus erzeugt (n-1)! Permutationen der ersten n-1 Elemente, wobei das letzte Element an jedes dieser Elemente angehängt wird.
          Dadurch werden alle Permutationen erzeugt, die mit dem letzten Element enden. (while-Schleife)
        - Wenn n ungerade ist, vertauscht man das erste und das letzte Element, wenn n gerade ist, vertauscht man das i-te Element (i ist der Zähler, der bei 0 beginnt)
          und das letzte Element und wiederholt den obigen Algorithmus, bis index kleiner als n ist. (Verzweigung)
        - In jeder Iteration erzeugt der Algorithmus alle Permutationen, die mit dem aktuellen letzten Element enden.
         */
        while (index < n) {
            if (c[index] < index) {
                // Wenn c[index] kleiner als index ist, bedeutet das, dass wir eine neue Permutation erstellen können
                // Verwendung des Heap Algorithmus, um Elemente zu vertauschen und neue Permutationen zu erzeugen
                if (index % 2 == 0) {
                    //Das Erste Element mit den index-ten vertauschen, wenn der index gerade ist
                    swap(array, 0, index);
                } else {
                    //ist der Array ungerade, wird das c[index]-nte Element mit dem index-ten vertauscht
                    swap(array, c[index], index);
                }
                //der vertauschte Array wird in die Liste von Permutations hinzugefügt
                permutations.add(Arrays.copyOf(array, n));
                //c[index] wird erhöht und index danach auf 0 gesetzt, um weitere Permutationen zu erzeugen (für das index-te Feld)
                c[index]++;
                index = 0;
            } else {
                // sobald c[index] genauso groß ist wie index selbst wird der index erhöht und c[index] auf 0 gesetzt und somit in das nächste Feld gewechselt
                c[index] = 0;
                index++;
            }
        }
        System.out.println(permutations.toArray(new String[0][0]).length + " Permutationen, soll: " + n + "!" ); // Anzahl der erstellten Permutationen Ausgeben

        //Die Liste in Einen 2-Dimensionalen String-Array umwandeln
        String[] result = new String[0];

        for (String[] permutation : permutations) {
            result = pushString(result, Arrays.toString(permutation)); // Gebe jede erzeugte Permutation aus
        }
        return result;
    }

    /* ------------------------------------------------------------*/
    //
    //                     HILFSFUNKTIONEN
    //
    /*______________________________________________________________*/

    private static void swap(String[] array, int a, int b) {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * push-Funktion für String[]-Arrays (Hilfsfunktion).
     * @param array ursprünglicher Array
     * @param push Wert, der am Ende des Arrays hinzugefügt werden soll
     * @return <code>String[]</code>
     */
    public static String[] pushString(String[] array, String push) {
        //verlängere den Array um eine Position
        String[] longer = new String[array.length + 1];
        //füge die werte dem neuen Array hinzu
        for (int i = 0; i < array.length; i++){
            longer[i] = array[i];
        }
        //setze den neuen Wert an der letzten Position ein
        longer[array.length] = push;
        return longer;
    }
}