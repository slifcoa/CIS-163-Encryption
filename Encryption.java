
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Encryption implements IEncryption
{
	private List message;
	private ArrayList<List> clipBoards;  
	private Stack undoCommands;


    private static Random random = new Random( );
    
    public static String[] helpPage = { 
            "Commands:",
            "a c #   \t means insert character 'c' at position #",
            "d c     \t means delete all instances of character 'c'",   

            "o       \t means obfuscate, i.e. insert a random character at any position",

            "c & # & \t means copy a range from & to # (inclusive) to clipboard &",
            "x & # & \t means cut a range from message and copy it to clipboard &",     
            "p # &   \t means paste, starting at #, from clipboard &",

            "r       \t means reverse the message",     
            "s & #   \t means swap two characters, & and #, in the message",        


            "H or h  \t means show this help page",         
            "Q or q  \t means quit! " +     "\n\t\t\t (Print the final encrypted message when the program terminates.)",    
            "S       \t means Save the text file"
    };



	public Encryption( ) 
	{
		message = new List( );

		undoCommands = new Stack( );
		clipBoards = new ArrayList<List>( );

		for (int i = 0; i < 1000; i++)
		{
			clipBoards.add( new List( ) );
		}
	}

	
	@Override
	public void encrypt( )
	{
		String commandLine, str;
		printHelpPage( );
		Scanner keyboard = new Scanner( System.in );

		System.out.println( "\nEnter a string (i.e. message)." );
		str = keyboard.nextLine( );

		setMessage( str );
		displayMessage( str );

		do
		{
			System.out.println( "Command:" );
			commandLine = keyboard.nextLine( );
			str = processCommand( commandLine );
			displayMessage( str );
		}
		while (!commandLine.equalsIgnoreCase( "q" ));

		keyboard.close( );
	}
	

	private void printIndices( int count )
	{
		for (int i = 0; i < count; i++) 
		{
			if (i < 10)
			{
				System.out.print (i + "  ");
			}
			else 
			{
				System.out.print(i + " ");
			}
		}
	}

	private void displayMessage( String str )
	{
		System.out.print ("Message:\n  ");
		printIndices( str.length( ) );

		System.out.println( "" );
		for (char c : str.toCharArray( )) 
		{
			System.out.print( "  " + c );
		}

		System.out.println( "" );
	}

	private void save( String filename )
	{

		PrintWriter out = null;
		try 
		{
			out = new PrintWriter( new BufferedWriter( new FileWriter( filename ) ) );
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}

		out.println( message );
		out.println( undoCommands.toString( ) );
		out.close( );
	}

	private void printHelpPage( )
	{
		for (String s: helpPage)
		{
			System.out.println( "\t" + s );
		}
	}
	
	private void setMessage( String str )
	{
		message.clearAll( );

		for (char c : str.toCharArray( ))
		{
			message.add( message.size( ), "" + c );
		}
	}

	private String processCommand( String commandLine )
	{
		Scanner text = new Scanner( commandLine );

		String command;
		int index;
		int start;
		int stop;
		String ch; 		
		FileChooser fileChooser;
		List clipBoard;
		int clipNum;
		
		
		try {
			command = text.next( ).substring( 0, 1 );
			switch (command.charAt( 0 )) 
			{
			
			case 'a':
				ch = text.next().substring( 0, 1 );
				index = text.nextInt( );				
		
				/*******************************************************************
				 *  TODO -- Verify!!!
				 *  
				 *  :) Insert two lines of code:
				 *          one line of code to change the message
				 *          a second line of code to create the undo command
				 * ******************************************************************
				 */
				
				message.add (index, ch );  					   // executing the command
				undoCommands.push( new Command( "d " + index  ) );  // creating the undo command
				
				break;
				
				
			case 'd':
				ch = text.next( );
				while ((index = message.find( ch )) != -1)
				{
					/*******************************************************************
					 *  TODO -- Verify!!!
					 *  
					 *  :) Insert two lines of code:
					 *          one line of code to change the message
					 *          a second line of code to create the undo command
					 * ******************************************************************
					 */
					
					message.remove( index );									  // executing the command
					undoCommands.push( new Command("a " + ch + " " + index ) );  // creating the undo command
				}
				
				break;
			
				
			case 'o':
				ch = "" + (char)(' ' + random.nextInt( 95 ));	// a printable character chosen at random
				index = random.nextInt( message.size( ) );
				
				/*******************************************************************
				 *  TODO -- Verify!!!
				 *  
				 *  :) Insert two lines of code:
				 *          one line of code to change the message
				 *          a second line of code to create the undo command
				 * ******************************************************************
				 */
				
				message.add( index, ch );  
				undoCommands.push( new Command( "d " + index ) );
				
				break;			


			case 'r':
				
				/*******************************************************************
				 *  TODO
				 *  
				 *  7) Insert two lines of code:
				 *          one line of code to reverse the message
				 *          a second line of code to create the undo command
				 * ******************************************************************
				 */
				message.reverse();
				undoCommands.push(new Command("r"));
				
				break;

				
			case 's':
				int j = text.nextInt( );
				int k = text.nextInt( );
				
				/*******************************************************************
				 *  TODO
				 *  
				 *  8) Insert two lines of code:
				 *          one line of code to swap two characters in the message
				 *          a second line of code to create the undo command
				 * ******************************************************************
				 */
				message.swap(j, k);
				undoCommands.push(new Command ("s " + k + " " + j));


				
				break;

			
			case 'c':	
				
				/*******************************************************************
				 *  TODO -- Verify!!!
				 *  
				 *  What is this copy command supposed to accomplish?
				 * *******************************************************************/
						
				start = text.nextInt( );
				stop = text.nextInt( );
				clipNum = text.nextInt( );
				clipBoard = clipBoards.get( clipNum );

				clipBoard.clearAll( );
				for (int i = start; i <= stop; i++) {
					ch = message.get( i );
					clipBoard.append( ch );
					message.goToNextPlace( );
				}
				System.out.println( "Clip board =>" + clipBoard );
				
				break;

			
			case 'p':
				index = text.nextInt( );	
				clipNum = text.nextInt( );
				
				clipBoard = clipBoards.get( clipNum );
				
				message.goToItem( index );
				clipBoard.goToFirstPlace( );
				while (! clipBoard.atTheEnd( ))
				{
					ch = clipBoard.get( );
		
					/*******************************************************************
					 *  TODO
					 *  
					 *  9) Insert two lines of code:
					 *          one line of code to paste the string/character, ch, into the message
					 *          a second line of code to create the undo command
					 * ******************************************************************
					 */
					message.add(index, ch);
					undoCommands.push(new Command("d " + index));

					
					
					
					clipBoard.goToNextPlace( );
					
					index++;			
				}
				
				break;


			case 'S':  
				
				/*******************************************************************
				 *  TODO -- Verify!!!
				 * *******************************************************************/
				
				String fileName = "message";

				fileChooser = new FileChooser( );
				fileName    = fileChooser.chooseFile( "save", fileName );  // file to write
				save( fileName );
				
				break;

				
			case 'h':
			case 'H':
				
				/*******************************************************************
				 *  TODO -- Verify help!!!
				 * *******************************************************************/
				
				printHelpPage( );
				
				break;
				
				
			case 'q':  
			case 'Q':  
				
				/*******************************************************************
				 *  TODO -- Verify quit!!!
				 * ******************************************************************/

				System.out.println ( "The encrypted message: " + message );
				
				break;

				
			case 'x':
							
				start = text.nextInt( );
				stop = text.nextInt( );
				
				clipNum = text.nextInt( );
				clipBoard = clipBoards.get( clipNum );
				
				clipBoard.clearAll( );
				for (int n = stop; n >= start; n--)
				{
					ch = message.remove( n );	
					clipBoard.add( ch );
					
					/*******************************************************************
					 *  TODO
					 *  
					 *  13) Insert one line of code to create the undo command
					 * ******************************************************************
					 */
					undoCommands.push(new Command("a " + clipBoard.get() + " " + n ));
					
					

				}
				System.out.println( "Clip board =>" + clipBoard );
				
				break;			
				
				
			default:
				System.out.println( "Not a command!" );
			}

		}
		catch (Exception e)
		{
			System.out.println( "Error in command! Try that command again" );
		}

		text.close( );

		return message.toString( );
	}

}