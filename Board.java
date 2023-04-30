import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Manages the board of pieces
 * 
 * @author Mary Elaine Califf
 */
public class Board
{
    // constants for identifying the contents of squares -- could be replaced with
    // an enum
    public static final int EMPTY_SQUARE = 0;
    public static final int BLACK_SQUARE = 1;
    public static final int WHITE_SQUARE = 2;
    public static final int OFF_THE_BOARD = 3;

    // other useful constants
    public static final int BOARD_SIZE = 8; // board is assumed to be square: BOARD_SIZExBOARD_SIZE
    public static final char EMPTY_LABEL = '_';
    public static final char BLACK_LABEL = 'b';
    public static final char WHITE_PAWN_LABEL = 'P';
    public static final char WHITE_ROOK_LABEL = 'R';
    public static final char WHITE_KNIGHT_LABEL = 'N';
    public static final char WHITE_BISHOP_LABEL = 'B';
    public static final char WHITE_QUEEN_LABEL = 'Q';
    public static final char WHITE_KING_LABEL = 'K';

    private ChessPiece[][] theBoard = new ChessPiece[BOARD_SIZE][BOARD_SIZE];

    /**
     * Provides information about what is on the board at a particular location
     * 
     * @param row The row number
     * @param col The column number
     * @return The color of the piece if there is a piece, the empty square value if
     *         there is no piece, and an off the board indicator in the location is
     *         not within the bounds of the board
     */
    public int getSquareInfo(int row, int col)
    {
        if (!(isOnBoard(row) && isOnBoard(col))) // invalid location
        {
            return OFF_THE_BOARD;
        }
        else if (theBoard[row][col] == null)
            return EMPTY_SQUARE;
        else
            return theBoard[row][col].getColor();
    }

    /**
     * Read a board from a file (or the keyboard)
     * 
     * @param input The Scanner attached to the input source
     */
    public void readBoard(Scanner input)
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            String rowData = input.nextLine();
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                theBoard[row][col] = createPiece(rowData.charAt(col), row, col);
            }
        }
    }

    /**
     * Write a board to an open PrintWriter
     * 
     * @param output The PrintWriter attached to the output destination
     */
    public void writeBoard(PrintWriter output)
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                if (theBoard[row][col] == null)
                    output.print(EMPTY_LABEL);
                else
                    output.print(theBoard[row][col].getLabel());
            }
            output.println();
        }
    }

    /**
     * Determine whether there is a legal move from the source location to the
     * destination location
     * 
     * @param fromRow The row number of the source location
     * @param fromCol The column number of the source location
     * @param toRow   The row number of the destination
     * @param toCol   The column number of the destination
     * @return true if there is a piece that legally the move; false otherwise
     */
    public boolean checkMove(int fromRow, int fromCol, int toRow, int toCol)
    {
        boolean isLegal = false;
        if (isOnBoard(fromRow) && isOnBoard(fromCol) && isOnBoard(toRow) && isOnBoard(toCol)
                && theBoard[fromRow][fromCol] != null)
        {
            // we have a piece at the location and there destination is on the board
            isLegal = theBoard[fromRow][fromCol].isLegalMove(toRow, toCol, this);
        }
        return isLegal;
    }

    /**
     * Moves a piece from the source location to the destination location iff there
     * is a legal move to be made
     * 
     * @param fromRow The row number of the source location
     * @param fromCol The column number of the source location
     * @param toRow   The row number of the destination
     * @param toCol   The column number of the destination
     * @return true if the move was made; false otherwise
     */
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol)
    {
        if (checkMove(fromRow, fromCol, toRow, toCol))
        {
            theBoard[fromRow][fromCol].move(toRow, toCol);
            theBoard[toRow][toCol] = theBoard[fromRow][fromCol];
            theBoard[fromRow][fromCol] = null;
            return true;
        }
        else
            return false;
    }

    public void displayPossibleMoves(int row, int col, PrintWriter output)
    {
        char[][] displayBoard = new char[BOARD_SIZE][BOARD_SIZE];

        // intialize the board to have all empty squares
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                displayBoard[i][j] = EMPTY_LABEL;

        // now if there is a piece at the location, have the piece indicate the possible
        // moves
        if (isOnBoard(row) && isOnBoard(col) && theBoard[row][col] != null)
            theBoard[row][col].generateLegalMoves(displayBoard, this);

        // and then print the board
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                output.print(displayBoard[i][j]);
            }
            output.println();
        }
    }

    // used to create chess pieces that correspond to the board label
    private ChessPiece createPiece(char label, int row, int column)
    {
        if (label == BLACK_LABEL)
            return new BlackPiece(row, column);
        else if (label == WHITE_KING_LABEL)
            return new King(row, column, WHITE_SQUARE);
        // you will need to comment out some of the following in order to test
        // incrementally
        else if (label == WHITE_KNIGHT_LABEL)
            return new Knight(row, column, WHITE_SQUARE);
        else if (label == WHITE_PAWN_LABEL)
            return new WhitePawn(row, column);
        else if (label == WHITE_ROOK_LABEL)
            return new Rook(row, column, WHITE_SQUARE);
        else if (label == WHITE_BISHOP_LABEL)
            return new Bishop(row, column, WHITE_SQUARE);
        else if (label == WHITE_QUEEN_LABEL)
            return new Queen(row, column, WHITE_SQUARE);

        else
            return null;

    }

    // check to see if an index is within the bounds of the board
    private boolean isOnBoard(int index)
    {
        return index >= 0 && index < BOARD_SIZE;
    }
}
