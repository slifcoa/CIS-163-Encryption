import javax.swing.JOptionPane;

public class Driver {
	public static void main( String[ ] args )
	{
		String prompt = "Enter E to Encrypt, D to Decrypt, or Q to Quit.";
		Encryption e = new Encryption( );
		Decryption d = new Decryption( );

		String[ ] suggestion = {"Encrypt", "Decrypt", "Quit" };
		int num = 0;

		String str = JOptionPane.showInputDialog( prompt, suggestion[ num ] );
		str = str.toUpperCase( );
		num = (num + 1) % 3;
		while (str.charAt( 0 ) != 'Q')
		{
			if (str.toUpperCase( ).charAt( 0 ) == 'E')
			{
				e.encrypt( );
			}
			else
			{
				d.decrypt( );
			}

			str = JOptionPane.showInputDialog( prompt, suggestion[ num ] );
			str = str.toUpperCase( );
			num = (num + 1) % 3;
		}

		System.out.println( "\n <<< End of Execution >>>" );
		//		System.exit( 0 );
	}

}