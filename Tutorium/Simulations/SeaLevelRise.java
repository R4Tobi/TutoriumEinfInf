package Tutorium.Simulations;

import java.util.Arrays;

public class SeaLevelRise {
    /**
     * Calls the function to visualize the rising of the sea level for different landscapes.
     * @param args args given by program call. Should have a length of 2: Operation, Profile
     * @throws InterruptedException Uses Thread.sleep() for better Visualization
     */
    public static void main(String[] args) throws InterruptedException {
        //check arguments for length of Arguments
        if(args.length != 2){
            System.err.println("Expected two Argument. " + args.length + " Arguments given.");
            System.exit(-1);
        }
        //check if the second Argument only contains Integers
        String[] argSplitted = args[1].split("");
        int[] argSplittedInt = new int[argSplitted.length];
        try{
            for(int i = 0; i < argSplitted.length; i++){
                argSplittedInt[i] = Integer.parseInt(argSplitted[i]);
            }
        }catch(NumberFormatException e){
            System.err.println("Cannot parse Argument to Int.");
            System.exit(-1);
        }

        //Choose the right Operation and loop the sea level rising as long as the whole coastline is underwater.
        switch(args[0]){
            case "seawater":{
                int i = 0;
                while(i < getMax(argSplittedInt)){
                    riseSeaLevelWithoutGroundwater(i,argSplittedInt);
                    //sleep 1000ms = 1s
                    Thread.sleep(1000);
                    i++;
                }
                break;
            }
            case "groundwater":{
                int i = 0;
                while(i < getMax(argSplittedInt)){
                    riseSeaLevelWithGroundwater(i,argSplittedInt);
                    //sleep 1000ms = 1s
                    Thread.sleep(1000);
                    i++;
                }
                break;
            }
            //If a wrong Operation is called give the user the right ones.
            default:{
                System.out.println("Wrong Operation:" + args[0] + "\n Choose from:\n - seawater \n - groundwater");
                System.exit(-1);
            }
        }
    }

    /**
     * Returns the highest value in an Array
     * @param arr Array of Integer
     * @return highest Integer
     */
    private static int getMax(int[] arr){
        int max = arr[0];
        for(int h : arr){
            if(max < h){
                max = h;
            }
        }
        return max;
    }

    /**
     * Visualizes the Sea-Level for rising with groundwater
     * @param level levels of landscape
     * @param height height of Sea-Level
     */
    public static void showSeaLevel(int[] level, int height) {
        int max = getMax(level);
        //create a 2-Dimensional charArr with the width of the levels and the height of the greatest Level.
        char[][] result = new char[level.length][max];

        //create the landscape in the CharArray, i iterates through the width, and j as long as the level's height.
        for(int i = 0; i < level.length; i++){
            for(int j = 0; j < level[i];j++){
                result[i][j] = '#';
            }
        }

        //Create a single String(formatted with \n) from the CharArray
        String resultString = "";
        for(int j = max-1; j >= 0;--j){
            for(int i = 0; i < level.length; i++){
                //create the landscape with #
                if(result[i][j] == '#'){
                    resultString += "#";
                //if j is equal to the hieght of the Sea-Level add a wave if there isn't a landscape(#).
                }else if(j==height){
                    resultString += "~";
                //Fill with spaces where is no water and no landscape
                }else{
                    resultString += " ";
                }
            }
            //after each row add a \n to create a new line.
            resultString += "\n";
        }
        System.out.println(resultString);
    }
    /**
     * Visualizes the Sea-Level for rising without groundwater
     * @param level levels of landscape
     * @param isValley Array with Boolean Values. Indicates if the Sea-Level should be applied or not for the specified level.
     * @param height height of Sea-Level
     */
    public static void showSeaLevel(int[] level,boolean[] isValley, int height) {
        //same code as above, just one difference (commented)
        int max = getMax(level);

        char[][] result = new char[level.length][max];

        for(int i = 0; i < level.length; i++){
            for(int j = 0; j < level[i];j++){
                result[i][j] = '#';
            }
        }

        String resultString = "";
        for(int j = max-1; j >= 0;--j){
            for(int i = 0; i < level.length; i++){
                if(result[i][j] == '#'){
                    resultString += "#";
                //only add the wave, if the level is not inside a valley
                }else if(j==height && !isValley[i]){
                    resultString += "~";
                }else{
                    resultString += " ";
                }
            }
            resultString += "\n";
        }
        System.out.println(resultString);
    }

    /**
     * Rises the Sea-Level with the Groundwater. Landscapes under the water are ignored.
     * @param m height of Sea-Level
     * @param level levels of landscape
     */
    public static void riseSeaLevelWithGroundwater(int m, int[] level){
        int[] result = new int[level.length];
        for (int i = 0; i < level.length; i++) {
            int current = level[i];
            boolean isUnderwater = current - m <= 0;
            if (isUnderwater) {
                result[i] = 0; // Preserve land if present, adjust for water level
            } else {
                result[i] = current;
            }
        }
        System.out.println(Arrays.toString(result).replaceAll(", ", "").replace("[", "").replace("]", ""));
        showSeaLevel(result, m);
    }

    /**
     * Rises the Sea-Level without rising the Groundwater. Landscapes under the water are ignored.
     * @param m height of Sea-Level
     * @param level levels of landscape
     */
    public static void riseSeaLevelWithoutGroundwater(int m, int[] level) {
        int[] result = new int[level.length];
        boolean[] isValley = new boolean[level.length];

        for (int i = 0; i < level.length; i++) {
            int current = level[i];
            boolean isUnderwater = current - m <= 0;

            // Check if there is land in the region around the current position (considering water level)
            boolean hasLandAround = hasLandAround(level, i, m);
            isValley[i] = hasLandAround;

            if(!isUnderwater || hasLandAround) {
                result[i] = current;
            }else{
                result[i] = 0;
            }
        }
        System.out.println(Arrays.toString(result).replaceAll(", ", "").replace("[", "").replace("]", ""));
        showSeaLevel(result,isValley, m);
    }

    /**
     *
     * @param level all levels of the landscape
     * @param currentIndex he Landscape-Level, which is going to be checked.
     * @param m height of the Sea-Level
     * @return boolean, true when there is land around and the level is not 0.
     */
    private static boolean hasLandAround(int[] level, int currentIndex, int m) {
        if (level[currentIndex] == 0) {
            return false;
        }
        boolean hasRight = false;
        boolean hasLeft = false;
        int maxRight = level.length - 1;

        int i = currentIndex - 1;
        while(i < maxRight){
            if(level[i] == 0){
                break;
            }
            if(level[i] > m + 1){
                hasRight = true;
                break;
            }
            i++;
        }

        int j = currentIndex;
        while(j >= 0){
            if(level[j] == 0){
                break;
            }
            if(level[j] > m){
                hasLeft = true;
                break;
            }
            j--;
        }
        if(hasLeft == hasRight){
            return hasRight;
        }else{
            return false;
        }
    }
}