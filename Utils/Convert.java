package Utils;

public class Convert {
    public static int[] StringArrToIntArr(String[] s) {
        int[] result = new int[s.length];
        //jeder Eintrag im String Array wird zu einem Integer umgewandelt
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }
}
