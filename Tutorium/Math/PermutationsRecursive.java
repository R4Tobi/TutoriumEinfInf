package Tutorium.Math;
/**
 * Klasse um sich Permutationen eines Arrays ausgeben zu lassen
 */
public class PermutationsRecursive {
    /**
     * Rekursive Methode, die Permutationen bestimmt
     * @param p String-Array, aus dem die Permutationen erstellt werden sollen.
     * @param index Zeigt, welcher Durchlauf in der Rekursion gerade gemacht wird
     */
    public static void findperm(String[] p, int index){ //index wird verwendet, um durch die Elemente zu iterieren
        if(index==p.length-1){ //Basisfall, der die Rekursion unterbricht
            printperm(p);
            return;
        }
        for(int i=index;i<p.length;i++){
            swap(p,i,index);// Tausche das Element an der aktuellen Position mit dem Element am Index
            findperm(p,index+1);// Rufe die Funktion rekursiv für den nächsten Index auf
            swap(p,i,index);// Setze das Array zurück, um weitere Permutationen zu erstellen
        }
    }

    /**
     * Gibt einen Array mit Permutationen aus
     * @param p String-Array
     */
    public static void printperm(String[]p){
        //Gibt den permutierten Array aus
        System.out.println(" ");
        for (String s : p) {
            System.out.print(s + " ");
        }
    }

    /**
     *
     * @param p Array, der als Basis zum Tauschen verwendet wird
     * @param a Position im Array, von der getauscht wird
     * @param b Position im Array, zu der getauscht wird
     */
    public static void swap(String[] p, int a, int b){
        String temp=p[a];
        p[a]=p[b];
        p[b]=temp;
    }

    /**
     * Aufruf der Permutationsfunktion
     * @param args Array dessen Permutationen erstellt werden soll.
     */
    public static void main(String[] args){
        findperm(args, 0);//index = 0 um mit dem ersten Element zu starten
    }
}
