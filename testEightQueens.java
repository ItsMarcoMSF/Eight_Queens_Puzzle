

public class testEightQueens {
    public static void main (String[] args) {
        EightQueens test1 = new EightQueens();

        test1.setQueen(0, 0);
        test1.setQueen(1,1);
        //String[][] testCopy = new String[8][8];
        //testCopy= test1.getBoard();
        //test1.setQueen(1, 3);
        //test1.backtrack(1,4);
        //test1.emptySquare(0,0);
        //test1.setThreatened();
        //test1.removeThreatened();
        boolean a = test1.setQueens(6);

        /*for (String[] row : testCopy) {
            for (String item : row) {
                System.out.print(item + "   ");
            }
            System.out.println();
        }
        System.out.println();*/

        for (char[] row : test1.chessBoard) {
            for (char item : row) {
                System.out.print(item + "   ");
            }
            System.out.println();
        }

        System.out.println(a);
    }
}
