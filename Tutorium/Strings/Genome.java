package Tutorium.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Genome {
    public static String readFile(String path) {
        String result = "";
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result += data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found.");
        }
        return result;
    }

    public static void decodeGenome(String data){
        String startCodon = "atg";
        String[] stopCodons = {"tag", "tga", "taa"};
        int index = 0;
        int counter = 0;

        while (index < data.length()) {
            //Nach einem Startcodon suchen
            int startIndex = data.indexOf(startCodon, index);
            if (startIndex == -1) {
                break; //wenn der startIndex -1 ist wurden keine weiteren Codons gefunden
            }

            //Berechnen der restlichen Länge des Strings
            int remainingLength = data.length() - startIndex;

            //Prüfen, ob das codon mindestens aus einem Start und einem Endcodon besteht
            if (remainingLength < 6) {
                break; //Schleife abbrechen, wenn es nicht genug charaktere gibt (mindestens 6)
            }

            // Nach einem Stop-Codon suchen
            int stopIndex = Integer.MAX_VALUE; //Ausgehend das bis Integer.MAX_VALUE ein codon sein wird

            for (String stopCodon : stopCodons) {
                int tempStopIndex = data.indexOf(stopCodon, startIndex);
                if (tempStopIndex != -1 && tempStopIndex < stopIndex) {
                    stopIndex = tempStopIndex;
                }
            }

            //Einzelnes Gen bauen und ausgeben
            if ((stopIndex - startIndex) % 3 == 0) {
                String gene = data.substring(startIndex, stopIndex + 3);
                System.out.println("Gene " + counter + ": " + gene);
                index = stopIndex + 3;
                counter++;
            } else {
                index = startIndex + 1;
            }
        }
    }

    public static void main(String[] args){
        String data = readFile("./data/ecoli.txt");
        decodeGenome(data);
    }
}
