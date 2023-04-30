/**
 * Chess Piece: Bishop
 * Handles all Bishop generation
 * 
 * @author Kaleb Liang
 */
public class Bishop extends ChessPiece{
    
    /**
     * Constructor
     * 
     * @param rowNum     The row number where the piece is located
     * @param columnNum  The column number where the piece is located
     * @param pieceColor The color of this bishop
     */
    public Bishop(int rowNum, int columnNum, int pieceColor){
        super(rowNum, columnNum, pieceColor);
    }

    /**
     * Gets chess piece label
     */
    public char getLabel() {
        return Board.WHITE_BISHOP_LABEL;
    }

    /**
     * Checks if move is legal
     * 
     * @param destRow destination row number
     * @param destCol destination column number
     * @param theBoard board object
     */
    @Override
    public boolean isLegalMove(int destRow, int destCol, Board theBoard)
    {
        int rowDiff = Math.abs(destRow - rowNum);
        int columnDiff = Math.abs(destCol - columnNum);
        int squareType = theBoard.getSquareInfo(destRow, destCol);

        boolean isLegal = (rowDiff == columnDiff)
            && (squareType != this.pieceColor) && (squareType != Board.OFF_THE_BOARD);
        return isLegal;
    }

    /**
     * Displays legal moves by the chess piece
     * 
     * @param boardData 2D array for chess piece location
     * @param theBoard board object
     */
    @Override
    public void generateLegalMoves(char[][] boardData, Board theBoard)
    {
        char label = Board.WHITE_BISHOP_LABEL;
        boardData[rowNum][columnNum] = label;
        boolean isLegal1=true;
        boolean isLegal2=true;
        boolean isLegal3=true;
        boolean isLegal4=true;

        //upper right diagonal 
        for (int i = rowNum - 1, j = columnNum + 1; i > -1 && j < Board.BOARD_SIZE; i--, j++){
            int squareType = theBoard.getSquareInfo(i, j);
            if (squareType == this.pieceColor)     //checks for friendly piece
                isLegal1=false;
            if (squareType == Board.BLACK_SQUARE && isLegal1){ //checks for black label
                boardData[i][j] = label;
                isLegal1=false;
            }
            if (isLegal1 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD)
            boardData[i][j] = label;
        }

        //lower right diagonal
        for (int j = columnNum + 1, i = rowNum + 1; j < Board.BOARD_SIZE && i < Board.BOARD_SIZE; j++, i++){
            int squareType = theBoard.getSquareInfo(i, j);
            if (squareType == this.pieceColor)    //checks for friendly piece
                isLegal2=false;
            if (squareType == Board.BLACK_SQUARE && isLegal2){ //checks for black label
                boardData[i][j] = label;
                isLegal2=false;
            }
            if (isLegal2 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD)
            boardData[i][j] = label;
        }
        
        //upper left diagonal
        for (int i = rowNum - 1, j = columnNum - 1; i > -1 && j > -1; i--, j--){
            int squareType = theBoard.getSquareInfo(i, j);
            if (squareType == this.pieceColor)   //checks for friendly piece
                isLegal3=false;
            if (squareType == Board.BLACK_SQUARE && isLegal3){ //checks for black label
                boardData[i][j] = label;
                isLegal3=false;
            }
            if (isLegal3 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD)
            boardData[i][j] = label;
        }

        //lower left diagonal
        for (int j = columnNum -1, i = rowNum + 1; i < Board.BOARD_SIZE && j > -1; j--, i++){
            int squareType = theBoard.getSquareInfo(i, j);
            if (squareType == this.pieceColor)    //checks for friendly piece
                isLegal4=false;
            if (squareType == Board.BLACK_SQUARE && isLegal4){ //checks for black label
                boardData[i][j] = label;
                isLegal4=false;
            }
            if (isLegal4 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD)
            boardData[i][j] = label;
        }

    }
}
