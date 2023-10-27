public class TicTacToe {
    public static void main(String[] args) {
        //create new char-Array
        char[][] board = {
                {'x', 'o', 'o'},
                {' ', 'x', 'o'},
                {' ', ' ', 'o'},
        };

        checkBoard(board);
    }

    public static void checkBoard(char[][] board){
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
