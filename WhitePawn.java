/**
 * Chess piece: White Pawn
 * Handles all white pawn generation
 * 
 * @author Kaleb Liang
 */
public class WhitePawn extends ChessPiece {
    
    /**
     * Constructor
     * 
     * @param rowNum     The row number where the piece is located
     * @param columnNum  The column number where the piece is located
     */
    public WhitePawn(int rowNum, int columnNum)
    {
        super(rowNum, columnNum, Board.WHITE_SQUARE);
    }

    /**
     * Gets the white pawn label
     */
    public char getLabel() 
    {
        return Board.WHITE_PAWN_LABEL;
    }

    /**
     * Checks if a move is legal
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

        boolean isLegal = (rowDiff == 1 && columnDiff ==0) || (rowDiff == 2 && columnDiff == 0) ||
        (rowDiff == 1 && columnDiff == 1)
            && (squareType != this.pieceColor) && (squareType != Board.OFF_THE_BOARD);
        return isLegal;
    }

    /**
     * Displays legal moves by the chess piece
     * 
     * @param destRow destination row number
     * @param destCol destination column number
     * @param theBoard board object
     */
    @Override
    public void generateLegalMoves(char[][] boardData, Board theBoard) 
    {
        char label = Board.WHITE_PAWN_LABEL;
        boardData[rowNum][columnNum] = label;
        boolean isLegal=true;       //vertical movement
        boolean isLegal2=false;     //diagonal movement
        for (int i = rowNum + 1; i <= rowNum + 1; i++)
        {
            for (int j = columnNum-1; j<=columnNum+1; j++) 
            {
            int squareType = theBoard.getSquareInfo(i, j);
            if (squareType != Board.EMPTY_SQUARE)   //checks if destination row is empty
                isLegal=false;
            if (squareType == Board.BLACK_SQUARE)  //checks if there is a piece black label diagonal
                isLegal2=true;
            if (rowNum==1)                         //generates move if pawn can move forward two spots
                boardData[rowNum+2][columnNum] = label;    
            if (squareType == Board.BLACK_SQUARE && isLegal2 && j!=columnNum) //generates diagonal move
                boardData[i][j] = label;   
            if (isLegal && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD && j==columnNum)
                boardData[i][columnNum] = label; 
            }
        }
    }
}

