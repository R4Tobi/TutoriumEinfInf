package Tutorium.Strings;

/**
 * Class, which is used to produce a 'CowSay' Output
 */
public class CowSay {
    /**
     * main methods checks the Arguments for the program and calls the method to actually 'make' the cow.
     * @param args Array with Flags like width of the textbox, and style of the cow.
     */
    public static void main(String[] args) { // default values for cow
        String message = ""; //variable for the message
        int width = 40; //default width
        String eyes = "oo"; //default eyes
        boolean tongue = false;
        int flagCounter = 0;

        if(args.length < 3){
            System.err.println("A Minimum of 3 Arguments is needed: e.g.: CowSay -W 6 Hello");
            System.exit(-1);
        }

        for (int i = 0; i < args.length; i++) { //processes the arguments given
            if(args[i].startsWith("-")){
                switch(args[i]){
                    case "-W": //switch case is used to compare a condition with multiple cases
                        if (i + 1 < args.length) { // Set custom width if provided
                            try{
                                width = Integer.parseInt(args[i + 1]); //converts given string argument to int
                                i++; //skip the next argument
                            }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                                System.err.println("Cannot convert -W [num] to int. (not given or not type of Integer)");
                                System.exit(-1);
                            }
                        }
                        break;
                    case "-b": // borg mode: eyes "=="
                        eyes = "==";
                        flagCounter++;
                        break;
                    case "-d": // dead mode:"XX" eyes and stick tongue out
                        eyes = "XX";
                        tongue = true;
                        flagCounter++;
                        break;
                    case "-g": // greedy mode:"$$" eyes
                        eyes = "$$";
                        flagCounter++;
                        break;
                    case "-p": // paranoid mode:"@@" eyes
                        eyes = "@@";
                        break;
                    case "-s": // stoner mode:"**" eyes and stick tongue out
                        eyes = "**";
                        tongue = true;
                        flagCounter++;
                        break;
                    case "-t": // tired mode:"--" eyes and stick tongue out
                        eyes = "--";
                        flagCounter++;
                        break;
                    case "-w": // wired mode:"OO" eyes
                        eyes = "OO";
                        flagCounter++;
                        break;
                    case "-y": // youthful mode:".." eyes
                        eyes = "..";
                        flagCounter++;
                        break;
                    case "-e":
                        try{
                            if (args[i + 1].length() == 2) {
                                // Set custom eyes if provided
                                eyes = args[i + 1];
                                i++; // Skip the next argument
                            }else{
                                System.err.println("Length of Eyes must be 2.");
                                System.exit(-1);
                            }
                            flagCounter++;
                        }catch(ArrayIndexOutOfBoundsException e){
                            System.err.println("No String given for eyes.");
                            System.exit(-1);
                        }
                        break;
                    case "-T": // toggle tongue option (invert)
                        tongue = !tongue;
                        break;
                    default: //the remaining arguments are the message, if no flag is found it triggers the String
                        System.out.println("Wrong flag used. Use the following:\n-W l: Gibt die Breite der Textbox an.\n-b: Borg Mode\n-d: Dead Mode\n-p: Paranoid Mode\n-s: Stoner Mode\n-t: Tired Mode\n-w: Wired Mode\n-y: Youthful Mode\n-e eyes: der String eyes gibt an, wie die Augen aussehen sollen.\n-T: Ändert das Verhalten der Zunge");
                        System.exit(-1);
                }
            }else{
                if(i < args.length - 1){
                    message += args[i] + " ";
                }else{
                    message += args[i];
                }
            }
        }
        if(message.isBlank()){
            System.err.println("Message is empty.");
            System.exit(-1);
        }
        if(flagCounter <= 1){
            String cowSaid = getCowSaid(message, width, eyes, tongue);// display the cow textbox
            System.out.println(cowSaid);
        }else{
            System.err.println("More than one Argument(-[b,d,g,p,s,t,w,y,e]) given. Should be one.");
            System.exit(-1);
        }
    }

    /**
     * Builds the cow with the textbox
     * @param message text to be showed
     * @param width width of the textbox
     * @param eyes String for the eyes
     * @param tongue true if tongue should be showed
     * @return String with cow, textbox
     */
    private static String getCowSaid(String message, int width, String eyes, boolean tongue) {// builds the textbox
        String border = "-".repeat(width+2); //+2 because of the spaces in the textbox (padding to the border).

        String messageFormatted = "";
        String[] messageChar = message.split("");


        messageFormatted += (" " + border + " \n"); //add border before the textbox

        String tempString = "";
        for (int i = 0; i < messageChar.length; i++) {
            //standard prefix and postfix
            String prefix = "| ";
            String postfix = " |\n";
            //prefix and postfix for the first line
            if(i == width - 1 && messageChar.length > width){
                prefix = "/ ";
                postfix = " \\ \n";
            }
            //prefix and postfix for the last line
            if(i== messageChar.length - 1 && messageChar.length > width){
                prefix = "\\ ";
                postfix = " / \n";
            }
            //append to temp string as long thew maximum width is, then break the line and create a new one.
            tempString += messageChar[i];
            if(tempString.length() == width){
                messageFormatted += (prefix + tempString + postfix);
                tempString = "";
            }
            //append the last string with spaces behind the temp string for the right format/length of the string
            if(messageChar.length - i <= width && i + 1 == messageChar.length){
                while(tempString.length() < width){
                    tempString += " ";
                }
                messageFormatted += (prefix + tempString + postfix);
            }
        }

        messageFormatted += (" " + border); //add border after the textbox

        // creates bottom border
        // cow art
        String cowArt = "      \\    ^__^\n" +
                "         \\  (" + eyes + ")\\_______\n" +
                "            (__)\\       )\\/\\\n" +
                "                ||----w |\n" +
                "                ||     ||";
        if (tongue) { // adds tongue when the condition is true
            cowArt = cowArt.replace("    ||----w |", " U  ||----w |");
        }
        return "\n" +  messageFormatted + "\n " + cowArt;
    }
}