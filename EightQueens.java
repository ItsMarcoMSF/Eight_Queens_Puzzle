import java.util.Arrays;

/**
 * @author Viet An Truong <a href="mailto:vietan.truong@ucalgary.ca">
 * vietan.truong@ucalgary.ca</a>
 * @version 1.9
 * @since 1.0
 */
public class EightQueens {
    public char[][] chessBoard;
    public int queens = 0;

    /**
     * Default constructor. Dynamically allocate new 2D array chessBoard and populates all items with "o"
     */
    public EightQueens() {
        chessBoard = new char[8][8];
        for (char[] row : chessBoard) {
            Arrays.fill(row, 'o');
        }
    }

    /**
     * public method getBoard
     * @return a new 2D array that is a copy of the current chessBoard
     */
    public char[][] getBoard() {
        char[][] result = new char[8][8];
        for (int i = 0; i < this.chessBoard.length; i++) {
            result[i] = Arrays.copyOf(this.chessBoard[i], this.chessBoard[i].length);
        }
        return result;
    }

    public EightQueens clone() {
        EightQueens result = new EightQueens();
        result.chessBoard = this.getBoard();
        result.queens = this.queens;

        return result;
    }

    /**
     * public method setThreatened.
     * Marks all the square that is threatened by a Queen already on the chessboard with
     * an "x"
     */
    public void setThreatened () {
        int row = 0;
        int column = 0;
        int isQueen = 0;
        for(int r = 0; r < chessBoard.length; r++){
            for (int c = 0; c < chessBoard[r].length; c++){
                if (chessBoard[r][c]=='Q') {
                    row = r;
                    column = c;
                    isQueen = 1;
                }
                if (isQueen == 1) {
                    for (int i = 0; i < chessBoard.length; i++) {
                        for (int j = 0; j < chessBoard[i].length; j++) {
                            if (i == row || j == column || (i + j) == (row + column) || (i - j) == (row - column)) {
                                if (!(i == row && j == column)) {
                                    this.chessBoard[i][j] = 'x';
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Remove all squares that are marked with an "x" and replace them with "o"'s
     */
    public void removeThreatened () {
        for(int r = 0; r < chessBoard.length; r++) {
            for (int c = 0; c < chessBoard[r].length; c++) {
                if (chessBoard[r][c]=='x') {
                    chessBoard[r][c] = 'o';
                }
            }
        }
    }

    /**
     * This method places a Queen ("Q") on the chessBoard regardless of it being threatened or not.
     * @param row index for row
     * @param column index for column
     */
    public void setQueen(int row, int column) {
        this.chessBoard[row][column] = 'Q';
        queens++;
    }

    /**
     * This method simply removes a Queen on the chessBoard and replace it with "o".
     * Can also be used to remove "x"
     * @param row index for row
     * @param column index for column
     */
    public void emptySquare(int row, int column) {
        this.chessBoard[row][column] = 'o';
        queens--;
    }

    /**
     * This method backtracks the state of the chessBoard when running setQueens(int remains)
     * It does so by first remove all threatened squares and the queen that didn't work out.
     * And then it updates the board again with a new set of threatened squares.
     * @param row index for row of the queen that didn't work
     * @param column index for column of the queen that didn't work
     */
    public void backtrack (int row, int column) {
        removeThreatened();
        emptySquare(row, column);
        setThreatened();
    }

    /**
     * Set the remains number of queens to the chessBoard so that no queens can attack one another.
     * @param remains remaining queens to be put on the chessBoard
     * @return True if all queens are placed successfully, False if can't find solution or invalid inputs
     */
    public boolean setQueens(int remains) {
        if (invalidStartingPosition()){
            System.out.println("Invalid Starting Position.");
            return false;
        }
        if (remains < 0 || remains > 8) {
            System.out.println("Please enter a valid number from 0 to 8");
            return false;
        }
        if (remains + queens > 8) {
            System.out.println("Two many queens to insert. There is/are already " + queens + " on the board");
            return false;
        }
        if (remains == 0) {
            return true;
        }
        setThreatened();
        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++){
                if(this.chessBoard[i][j]!='x' && this.chessBoard[i][j]!='Q') {
                    setQueen(i, j);
                    setThreatened();
                    if (setQueens(remains-1)) {
                        removeThreatened();
                        return true;
                    }
                    backtrack(i, j);
                }
            }
        }
        return false;
    }

    private boolean invalidStartingPosition() {
        int row = 0;
        int column = 0;
        int isQueen = 0;
        for(int r = 0; r < chessBoard.length; r++){
            for (int c = 0; c < chessBoard[r].length; c++){
                if (chessBoard[r][c]=='Q') {
                    row = r;
                    column = c;
                    isQueen = 1;
                }
                if (isQueen == 1) {
                    for (int i = 0; i < chessBoard.length; i++) {
                        for (int j = 0; j < chessBoard[i].length; j++) {
                            if (i == row || j == column || (i + j) == (row + column) || (i - j) == (row - column)) {
                                if (!(i == row && j == column)) {
                                    if (this.chessBoard[i][j] == 'Q') {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
