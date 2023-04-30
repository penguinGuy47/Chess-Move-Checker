/**
 * Chess piece: Queen
 * Handles all queen generation
 * 
 * @author Kaleb Liang
 */
public class Queen extends ChessPiece {
   
    /**
     * Constructor
     * 
     * @param rowNum     The row number where the piece is located
     * @param columnNum  The column number where the piece is located
     * @param pieceColor The color of this queen
     */
    public Queen(int rowNum, int columnNum, int pieceColor){
        super(rowNum, columnNum, pieceColor);
    }

    /**
     * Gets the white queen label
     */
    public char getLabel() {
        return Board.WHITE_QUEEN_LABEL;
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

        boolean isLegal = ( (rowDiff < 8 && columnDiff==0) || (rowDiff==0 && columnDiff < 8) || (rowDiff == columnDiff) ) 
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
        char label = Board.WHITE_QUEEN_LABEL;
        boardData[rowNum][columnNum] = label;
        boolean isLegal1=true;
        boolean isLegal2=true;
        boolean isLegal3=true;
        boolean isLegal4=true;
        boolean isLegal5=true;
        boolean isLegal6=true;
        boolean isLegal7=true;
        boolean isLegal8=true;
        
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

        //up
        for (int i = rowNum-1; i>-1; i--){  
            int squareType = theBoard.getSquareInfo(i, columnNum);
            if (squareType == this.pieceColor){     //checks for friendly piece
                    isLegal5=false;
                }
                if (squareType == Board.BLACK_SQUARE && isLegal5){ //checks for black label
                    boardData[i][columnNum] = label;
                    isLegal5=false;
                }
            if (isLegal5 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD){ 
                boardData[i][columnNum] = label;
            }
        }
        
        //down
        for (int i = rowNum+1; i<=Board.BOARD_SIZE; i++){
            int squareType = theBoard.getSquareInfo(i, columnNum);
            if (squareType == this.pieceColor){     //checks for friendly piece
                    isLegal6=false;
                }
                if (squareType == Board.BLACK_SQUARE && isLegal6){ //checks for black label
                    boardData[i][columnNum] = label;
                    isLegal6=false;
                }
            if (isLegal6 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD){ 
                boardData[i][columnNum] = label;
            }
        }

        //right
        for (int j = columnNum+1; j<=Board.BOARD_SIZE; j++){
            int squareType = theBoard.getSquareInfo(rowNum, j);    
            if (squareType == this.pieceColor){     //checks for friendly piece
                    isLegal7=false;
                }
                if (squareType == Board.BLACK_SQUARE && isLegal7){ //checks for black label
                    boardData[rowNum][j] = label;
                    isLegal7=false;
                }
            if (isLegal7 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD){
                boardData[rowNum][j] = label;
            }   
        }
        
        //left
        for (int j = columnNum-1; j>-1; j--){
            int squareType = theBoard.getSquareInfo(rowNum, j);
            if (squareType == this.pieceColor){     //checks for friendly piece
                    isLegal8=false;
                }
                if (squareType == Board.BLACK_SQUARE && isLegal8){ //checks for black label
                    boardData[rowNum][j] = label;
                    isLegal8=false;
                }
            if (isLegal8 && squareType != this.pieceColor && squareType != Board.OFF_THE_BOARD){  
                boardData[rowNum][j] = label;
            }
        }
    }
}
