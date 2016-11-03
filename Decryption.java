

import java.io.*;
import java.util.Scanner;

public class Decryption implements IDecryption
{

	private List message;


	public Decryption( ) 
	{
		message = new List( );
	}

	
	@Override	
	public void decrypt( )
	{
		String filename;
		FileChooser fileChooser;

		fileChooser = new FileChooser( );
		filename = fileChooser.chooseFile( "open", "message.txt" );  // the file to read

		System.out.println( );
		String original = decrypt( filename );
		System.out.println( original );
	}

	
	private String decrypt( String filename )
	{
		String userMessage = "";

		Scanner reader = this.open( filename );
		this.setMessage( reader.nextLine( ) );

		while (reader.hasNext( ))
		{
			userMessage = this.processCommand( reader.nextLine( ) );
		} 

		reader.close( );

		return userMessage;
	}

	private Scanner open(String filename)
	{
		Scanner	scanner = null;
		try
		{
			scanner = new Scanner( new File( filename ) );
		}
		catch (FileNotFoundException e)
		{
			
			e.printStackTrace( );
		}
		return scanner;
	}
	
	private void setMessage( String str )
	{
		message.clearAll( );

		for (char c : str.toCharArray( ))
		{
			message.add( message.size( ), "" + c );
		}
	}

	/*
	 * A decryption command does not have an undo; it is an undo command!
	 */
	private String processCommand( String commandLine )
	{
		Scanner text = new Scanner( commandLine );

		String command;
		String ch;
		int    index;

		try {
			command = text.next( ).substring( 0, 1 );
			switch (command.charAt( 0 )) 
			{
			case 'a':
				ch   = text.next().substring( 0, 1 );
				index = text.nextInt( );				
				
				/*******************************************************************
				 *  TODO
				 *  
				 *  9) Insert one line of code to add string/character, ch, into the
				 *     message list at the position given by index.
				 *         
				 * ******************************************************************
				 */
			
				message.add(index, ch);
				
				
				break;

			case 'd':
				index = text.nextInt( );
				
				/*******************************************************************
				 *  TODO
				 *  
				 *  10) Insert one line of code to delete the character in the 
				 *      message (i.e. list) at the position given by index.
				 *         
				 * ******************************************************************
				 */
				message.remove(index);

				
				break;
			
			case 'r':
				
				/*******************************************************************
				 *  TODO
				 *  
				 *  11) Insert one line of code to reverse the list.
				 *         
				 * ******************************************************************
				 */
				
				message.reverse();
				

				break;

			case 's':
				int j = text.nextInt( );
				int k = text.nextInt( );
				/*******************************************************************
				 *  TODO
				 *  
				 *  12) Insert one line of code to swap two characters in the message
				 *          
				 * ******************************************************************
				 */
				message.swap(j, k);

				
				
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