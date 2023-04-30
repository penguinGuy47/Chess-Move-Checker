import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A program for checking check moves Reads from a file of information and write
 * results to a separate file File names are obtained from the user
 * 
 * @author Mary Elaine Califf
 */
public class ChessMoveChecker
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter the input file name: ");
        String inputFileName = keyboard.next();
        System.out.print("Please enter the output file name: ");
        String outputFileName = keyboard.next();

        // create the chess board object
        Board chessBoard = new Board();

        // open the input and output files
        try
        {
            Scanner infile = new Scanner(new File(inputFileName));
            PrintWriter outfile = new PrintWriter(outputFileName);

            while (infile.hasNext())
            {
                // read the next command
                String command = infile.next();
                handleCommand(command, chessBoard, infile, outfile);

            }
            infile.close();
            outfile.close();
        }
        catch (IOException e)
        {
            System.err.println("Error with files: " + e.getLocalizedMessage());
        }

    }

    private static void handleCommand(String command, Board theBoard, Scanner infile, PrintWriter outfile)
    {
        switch (command)
        {
        case "readBoard":
            infile.nextLine(); // set up to read the board
            theBoard.readBoard(infile);
            break;
        case "writeBoard":
            outfile.println();
            theBoard.writeBoard(outfile);
            break;
        case "checkMove":
            handleMove(theBoard, infile, outfile, false);
            break;
        case "makeMove":
            handleMove(theBoard, infile, outfile, true);
            break;
        case "genPossMoves":
            displayPossibleMoves(theBoard, infile, outfile);
            break;
        default:
            System.err.println("Unrecognized command: " + command);

        }
    }

    private static void handleMove(Board theBoard, Scanner infile, PrintWriter outfile, boolean doIt)
    {
        // read the locations -- we're cheating and putting the actual board indices in
        // rather than using a standard chess notation
        int fromRow = infile.nextInt();
        int fromCol = infile.nextInt();
        int toRow = infile.nextInt();
        int toCol = infile.nextInt();
        boolean succeeded = false;

        if (doIt)
        {
            succeeded = theBoard.makeMove(fromRow, fromCol, toRow, toCol);
            if (succeeded)
            {
                outfile.println("Moved from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")");
                outfile.println("New board state: ");
                theBoard.writeBoard(outfile);
            }
        }
        else
        {
            succeeded = theBoard.checkMove(fromRow, fromCol, toRow, toCol);
            if (succeeded)
                outfile.println("Can move from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ")");
        }
        if (!succeeded)
            outfile.println(
                    "Move from (" + fromRow + "," + fromCol + ") to (" + toRow + "," + toCol + ") is not possible");
    }

    private static void displayPossibleMoves(Board theBoard, Scanner infile, PrintWriter outfile)
    {
        // read the locations -- we're cheating and putting the actual board indices in
        // rather than using a standard chess notation
        int fromRow = infile.nextInt();
        int fromCol = infile.nextInt();

        outfile.println("Possible moves from (" + fromRow + "," + fromCol + ")");
        theBoard.displayPossibleMoves(fromRow, fromCol, outfile);
        outfile.println();
    }
}
