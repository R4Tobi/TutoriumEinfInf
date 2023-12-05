package Tutorium.Arrays;

public class Sudoku {
    /**
     * Ruft die checksudoku Funktion auf
     * @param args are ignored
     */
    public static void main(String[] args) {
        int[][] sudoku = {
                {1, 7, 9, 8, 6, 3, 2, 5, 4},
                {5, 3, 4, 1, 7, 2, 6, 9, 8},
                {8, 6, 2, 9, 4, 5, 3, 1, 7},
                {3, 5, 6, 4, 9, 7, 8, 2, 1},
                {9, 1, 8, 3, 2, 6, 4, 7, 5},
                {4, 2, 7, 5, 1, 8, 9, 6, 3},
                {6, 8, 3, 2, 5, 1, 7, 4, 9},
                {7, 4, 5, 6, 3, 9, 1, 8, 2},
                {2, 9, 1, 7, 7, 4, 5, 3, 6} //7 repeats twice in column 5
        };
        System.out.println(checksudoku(sudoku));
    }

    /**
     * Kontrolliert, ob das Sudoku Feld 9x9 ist.
     * @param sudoku Sudoku Feld
     * @return true/false
     */
    public static boolean isValidSudoku(int[][] sudoku){
        boolean columns = sudoku.length == 9;

        try{
            for(int i = 0; i < 9; i++){
                if(sudoku[i].length != 9){
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        return columns;
    }

    /**
     * checks a specified column in the sudoku
     * @param sudoku Sudoku Feld
     * @param column index der Spalte (von der gestartet wird)
     * @return true/false
     */
    public static boolean checkcolumn(int[][] sudoku, int column) {
        boolean[] check = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int a = sudoku[i][column];
            if (a != 0 && check[a]) {
                return false;
            }
            check[a] = true;
        }
        return true;
    }

    /**
     * checks a specified row in the sudoku
     * @param sudoku Sudoku Feld
     * @param row index der Reihe (von der gestartete wird)
     * @return true/false
     */
    public static boolean checkrow(int[][] sudoku, int row) {
        for (int i = 0; i < 9; i++) {
            boolean[] check = new boolean[10];
            int a = sudoku[row][i];
            if (a != 0 && check[a]) {
                return false;
            }
            check[a] = true;
        }
        return true;
    }

    /**
     * Kontrolliert die 3x3 Bereiche im sudoku
     * @param sudoku Sudoku Feld
     * @param row index der Reihe (von der gestartete wird)
     * @param column index der Spalte (von der gestartet wird)
     * @return true/false
     */

    public static boolean check3x3(int[][] sudoku, int row, int column) {
        boolean[] check = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = sudoku[row + i][column + j];
                if (num != 0 && check[num]) {
                    return false;
                }
                check[num] = true;
            }
        }
        return true;
    }

    /**
     * Kontrolliert das ganze Sudoku Feld mithilfe der andern Funktionen
     * @param sudoku Sudoku Feld
     * @return true/false
     */
    public static boolean checksudoku(int[][] sudoku) {
        if(!isValidSudoku(sudoku)){
            System.out.println("The Sudoku is not a 9x9 matrix.");
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (!checkrow(sudoku, i) || !checkcolumn(sudoku, i)) {
                return false;
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!check3x3(sudoku, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}