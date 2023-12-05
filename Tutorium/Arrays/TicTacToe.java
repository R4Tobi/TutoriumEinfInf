package Tutorium.Arrays;
/**
 * Klasse, mit der ein TicTacToe-Feld überprüft, und der Gewinner ermittelt werden kann.
 */
public class TicTacToe {
    /**
     * Testrahmen und Aufruf der Funktionen
     * @param args Argumente (werden ignoriert)
     */
    public static void main(String[] args) {
        //create new char-Array
        char[][] board = {
                {'x', 'o', 'o'},
                {' ', 'x', 'o'},
                {' ', ' ', 'o'},
        };

        checkBoard(board);
    }
    /**
     * Überprüfen, ob das Board das richtige Format hat.
     * @param board 2-Dimensionaler char-Array (3x3 Matrix)
     * @return <code>boolean</code> - true, wenn das Board richtig ist, false, wenn nicht.
     */
    public static boolean isValidBoard(char[][] board) {
        // Überprüfen, ob die Matrix eine 3x3-Matrix ist
        if (board.length != 3 || board[0].length != 3) {
            return false;
        }

        //Überprüfen, ob die einzigen Zeichen 'o' und 'x' sind, indem jedes Feld geprüft wird
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'o' && board[i][j] != 'x' && board[i][j] != ' ') {
                    return false;
                }
            }
        }

        return true; // Die Matrix ist eine gültige 3x3-Matrix mit 'o' und 'x'.
    }
    /**
     * Gibt aus, welcher Spieler bei einem TicTacToe Spiel gewonnen hat.
     * @param board 2-Dimensionaler char-Array (3x3 Matrix)
     */
    public static void checkBoard(char[][] board){
        //Überprüfen der Matrix
        if(!isValidBoard(board)){
            System.out.println("Ungültiges Board.");
            return;
        }
        //Überprüfen der Reihen (3 Möglichkeiten)
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                //Weil alle Elemente in der Reihe gleich sind, reicht es das Erste zu überprüfen
                if (board[row][0] == 'x') {
                    System.out.println("x hat gewonnen.");
                    return;
                } else if (board[row][0] == 'o') {
                    System.out.println("o hat gewonnen");
                    return;
                }
            }
        }

        //Überprüfen der Zeilen (3 Möglichkeiten)
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                //Weil alle Elemente in der Zeile gleich sind, reicht es das Erste zu überprüfen
                if (board[0][col] == 'x') {
                    System.out.println("x hat gewonnen");
                    return;
                } else if (board[0][col] == 'o') {
                    System.out.println("o hat gewonnen");
                    return;
                }
            }
        }

        //Überprüfen der Diagonalen (2 Möglichkeiten), deswegen nur ein if-Statement
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            //Weil alle Elemente in der Diagonale ind der Mitte gleich sind, reicht es diese zu überprüfen
            if (board[1][1] == 'x') {
                System.out.println("x hat gewonnen.");
                return;
            } else if (board[1][1] == 'o') {
                System.out.println("o hat gewonnen.");
                return;
            }
        }
        System.out.println("Keiner hat gewonnen.");
    }
}
