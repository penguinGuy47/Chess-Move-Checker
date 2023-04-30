/**
 * Chess piece: Knight
 * Handles all knight generation
 * 
 * @author Kaleb Liang
 */
public class Knight extends ChessPiece {

    /**
     * Constructor
     * 
     * @param rowNum     The row number where the piece is located
     * @param columnNum  The column number where the piece is located
     * @param pieceColor The color of this knight
     */
    public Knight(int rowNum, int columnNum, int pieceColor) {
        super(rowNum, columnNum, pieceColor);
    }

    /**
     * Gets the white knight label
     */
    public char getLabel() {
        return Board.WHITE_KNIGHT_LABEL;
    }

    /**
     * Checks if a move is legal
     * 
     * @param destRow  destination row number
     * @param destCol  destination column number
     * @param theBoard board object
     */
    @Override
    public boolean isLegalMove(int destRow, int destCol, Board theBoard) {
        int rowDiff = Math.abs(destRow - rowNum);
        int columnDiff = Math.abs(destCol - columnNum);
        int squareType = theBoard.getSquareInfo(destRow, destCol);

        boolean isLegal = (rowDiff == 1 && columnDiff == 2 || rowDiff == 2 && columnDiff == 1)
                && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD;
        return isLegal;
    }

    /**
     * Displays legal moves by the chess piece
     * 
     * @param destRow  destination row number
     * @param destCol  destination column number
     * @param theBoard board object
     */
    @Override
    public void generateLegalMoves(char[][] boardData, Board theBoard) {
        char label = Board.WHITE_KNIGHT_LABEL;
        boardData[rowNum][columnNum] = label;
        int one = Math.abs(1);
        int two = Math.abs(2);
        for (int i = rowNum - 2; i <= rowNum + 2; i++) {
            for (int j = columnNum - 2; j <= columnNum + 2; j++) {
                int remain = rowNum - i;
                int remain2 = columnNum - j;
                if (Math.abs(remain) == one && Math.abs(remain2) == two
                        || Math.abs(remain) == two && Math.abs(remain2) == one) {
                    int squareType = theBoard.getSquareInfo(i, j);
                    if (squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD)
                        boardData[i][j] = label;
                }
            }
        }
    }
}
