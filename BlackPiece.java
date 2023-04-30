/**
 * Class for black pieces -- does not worry about which piece
 * 
 * @author Mary Elaine Califf & Kaleb Liang
 */
public class BlackPiece extends ChessPiece
{
    /**
     * Constructor
     * 
     * @param rowNum    The row number where the piece is located
     * @param columnNum The column number where the piece is located
     */
    public BlackPiece(int rowNum, int columnNum)
    {
        super(rowNum, columnNum, Board.BLACK_SQUARE);
    }

    @Override
    public char getLabel()
    {
        return Board.BLACK_LABEL;
    }

    @Override
    public boolean isLegalMove(int destRow, int destCol, Board theBoard)
    {
        return false;
    }

    @Override
    public void generateLegalMoves(char[][] boardData, Board theBoard)
    {
        boardData[rowNum][columnNum] = Board.BLACK_LABEL;
    }

}
