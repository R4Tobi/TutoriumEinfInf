package Tutorium.Strings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Klasse, die verwendet wird um aus einem Bild ein ASCII-Image zu erstellen
 */
public class Dithering {
    /**
     * Funktion die das ASCII-Bild erstellt
     * @param filePath Pfad zum Bild das umgewandelt werden soll.
     */
    public static void dithering(String filePath, boolean invert) {
        //  wir nehmen ein Farbbild mit RBG-Werten an.
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("Error while Reading the Image");
            System.exit(1);
        }

        int m = img.getHeight();
        int n = img.getWidth();

        ArrayList<double[]> grayValues = new ArrayList<>();

        for (int i=0 ;i<m; ++i) {
            for (int j=0; j<n; ++j) {
                int value = img.getRGB(j, i);// Pixelwert in interner Darstellung
                System.out.println(value);

                Color c = new Color(value);
                int r,g,b;                       // Rot-, Grün- und Blauwert (jeweils in 0,...,255)
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();

                // Wandle ganzzahlige Werte in [0,...,255] um in reelle Zahlen in [0,1].
                double rr = r/255.0;
                double gg = g/255.0;
                double bb = b/255.0;

                double gray = 0.3*rr + 0.59*gg + 0.11*bb;

                grayValues.add(new double[]{j, i, gray});
            }
        }


        String[] characters = new String[]{" ","~","+","=","*","o","x","0","&","#"};
        String[] result = new String[m];
        Arrays.fill(result, "");

        int i = 0;
        for(double[] value : grayValues){
            if(value[2] >= 0 == value[2] < 0.1){
                result[i] += characters[0];
            }else if(value[2] >= 0.1 && value[2] < 0.2){
                result[i] += characters[1];
            }else if(value[2] >= 0.2 && value[2] < 0.3){
                result[i] += characters[2];
            }else if(value[2] >= 0.3 && value[2] < 0.4){
                result[i] += characters[3];
            }else if(value[2] >= 0.4 && value[2] < 0.5){
                result[i] += characters[4];
            }else if(value[2] >= 0.5 && value[2] < 0.6){
                result[i] += characters[5];
            }else if(value[2] >= 0.6 && value[2] < 0.7){
                result[i] += characters[6];
            }else if(value[2] >= 0.7 && value[2] < 0.8){
                result[i] += characters[7];
            }else if(value[2] >= 0.8 && value[2] < 0.9){
                result[i] += characters[8];
            }else if(value[2] >= 0.9 && value[2] <= 1){
                result[i] += characters[9];
            }
            if(i == value[1] - 1){
                i++;
            }
        }
        for(String str : result){
            System.out.println(str);
        }
    }

    /**
     * ruft dithering-Funktion auf. Als arg wird nur der Dateipfad akzeptiert
     * @param args
     */
    public static void main(String[] args){
        if(args.length != 2){
            System.err.println("Expected 2 Arguments. " + args.length + " Arguments given.\n Expected FilePath(String) and invert(boolean)");
            System.exit(1);
        }
        if((Objects.equals(args[1], "true")) || String.valueOf(args[1]) == "false"){
            System.err.println("Second Argument is not a valid Boolean");
        }

        dithering(args[0], Boolean.parseBoolean(args[1]));
    }
}