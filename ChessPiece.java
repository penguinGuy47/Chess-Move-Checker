/**
* Instantiate all chess pieces
* 
* @author Kaleb Liang
*/
public abstract class ChessPiece {

    protected int rowNum, 
    columnNum,
    pieceColor;

    /**
     * Constructor for all chess pieces
     * 
     * @param rowNum     The row number where the piece is located
     * @param columnNum  The column number where the piece is located
     * @param pieceColor The color of this piece
     */
    public ChessPiece(int rowNum, int columnNum, int pieceColor) {
        this.rowNum=rowNum;
        this.columnNum=columnNum;
        this.pieceColor=pieceColor;
    }

    /**
     * Moves chess piece
     * 
     * @param toRow row destination
     * @param toCol column destination
     */
    public void move(int toRow, int toCol) {
        this.rowNum=toRow;
        this.columnNum=toCol;
    }

    /**
     * Gets color of chess piece
     */
    public int getColor(){
        return pieceColor;
    }

    /**
     * Abstract method to get label
     */
    public abstract char getLabel();

    /**
     * Abstract method for chess pieces
     * 
     * @param destRow stores the row on board
     * @param destCol stores the column on board
     * @param theBoard holds chess piece info in array from Board class
     */
    public abstract boolean isLegalMove(int destRow, int destCol, Board theBoard);

    /**
     * Abstract method for chess pieces
     * 
     * @param boardData holds board data in array
     * @param theBoard holds chess piece info in array from Board class
     */
    public abstract void generateLegalMoves(char [][] boardData, Board theBoard);
}
