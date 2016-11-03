

import java.io.Serializable;
import java.util.*;

/** ------------------------------------------------------------------------
 * A List is created to have both a top dummy Node2 and a bottom dummy Node2.
 * Cursor is initialized to match the top field. 
 * 
 * The end field is always a reference to the bottom dummy Node2.
 * 
 * For each value of the cursor field, the data are stored in the next Node2,
 * i.e. cursor.next.data
 * 
 * -----------------------------------------
 *     list is empty
 * top.prev         null
 * top.data         dummy
 * top.next         links forward
 *                 
 * top.next.prev    links backward
 * top.next.data    dummy
 * top.next.next    null
 * 
 * Above, cursor = top // i.e. cursor index is 0
 * 
 * -----------------------------------------
 * 
 * Below, cursor = top // i.e. cursor index is 0 for first, as well as last, place
 * 
 * top.prev              null
 * top.data              dummy
 * top.next              links forward
 *                 
 * cursor.next.prev         links backward
 * cursor.next.data         "one"
 * cursor.next.next         links forward
 *                 
 * cursor.next.next.prev    links backward
 * cursor.next.next.data    dummy
 * cursor.next.next.next    null
 * 
 * -------------------------------------------
 * 
 * Below, cursor = top.next // i.e. cursor index is 1 for the end place
 * 
 * top.prev            null
 * top.data            dummy
 * top.next            links forward
 *                 
 * cursor.prev         links backward
 * cursor.data         "one"
 * cursor.next         links forward
 *                 
 * cursor.next.prev    links backward
 * cursor.next.data    dummy
 * cursor.next.next    null
 * 
 * -------------------------------------------
 * 
 * Unless the value of cursor is changed, each item will be entered in the
 * 0th position in the list.
 * 
 * --------------------------------------------------------------------------
 */

public class List implements Serializable
{
	private static final long serialVersionUID = 1L;

	protected Node2 top;     // A top dummy Node2 in the list
	protected Node2 cursor;  // The current Node2 in the list. 
	protected Node2 end;


	/**
	 * Invokes the other constructor.
	 */
	public List( )
	{
		this( "" );
	}

	/**
	 * Constructs the list with two dummy Node2s, 
	 * one Node2 to always lead the list, and a
	 * second Node2 to always trail the list.
	 */
	public List( String title )
	{
		this.top      = new Node2( null, title, null );    // dummy top Node2
		this.top.next = new Node2( this.top, null, null ); // dummy bottom Node2

		this.cursor   = this.top;
		this.end      = this.top.next;
	}

	/**
	 * Returns whether the list is empty.
	 */
	public boolean isEmpty( ) 
	{
		return this.top.next.next == null;
	}

	/**
	 * Returns whether the cursor points to
	 * the 0th location in the list
	 */
	public boolean atFirstPlace( )
	{
		return this.cursor == this.top;
	}

	/**
	 * Returns whether the cursor points to
	 * the last occupied location in the list
	 */
	public boolean atLastPlace( )
	{
		return this.isEmpty( ) || this.cursor.next.next.next == null;
	}

	/**
	 * Returns whether the cursor points to
	 * the end of the list, i.e. the location
	 * for appending an item to the list.
	 */
	public boolean atTheEnd( )
	{
		return this.cursor.next.next == null;  // this.cursor.next.next == this.end;
	}

	
	/**
	 * Advances the cursor to the next place in the list.
	 */
	public void goToNextPlace( )
	{
		if (! this.atTheEnd( ))   //this.cursor.next.next != null) 
		{       
			this.cursor = this.cursor.next;
		}
	}

	
	/**
	 * Backs up the cursor to the previous place in the list.
	 */
	public void goToPreviousPlace( )
	{
		if (! this.atFirstPlace( ))   //this.cursor.prev != null) 
		{          
			this.cursor = this.cursor.prev;
		}
	}

	/**
	 * Moves the cursor to the top, i.e. the first
	 * item of the list, cursor.next.getData()
	 */
	public void goToFirstPlace( )
	{
		this.cursor = this.top;
	}

	/**
	 * Moves the cursor to the end of the list, which is at 
	 * 
	 *                    this.end.prev.
	 */
	public void goToTheEnd( )
	{
		this.cursor = this.end.prev;
	}

	/**
	 * Moves the cursor to the Node2 immediately preceding 
	 * the trailing Node2 of the list.
	 */
	public void goToLastPlace( ) 
	{
		this.goToTheEnd( );
		if (! this.atFirstPlace( ))
		{
			this.goToPreviousPlace( );
		}
	}

	/**
	 * Changes the cursor to reference the Node2 specified by the index parameter.
	 */
	public void goToItem( int index )
	{
		//         if (index < 0)
		//         {
		//             throw new IllegalArgumentException();
		//         }

		this.goToFirstPlace( );
		while ( index > 0 && !this.atTheEnd( ))
		{
			this.goToNextPlace( );
			index--;
		}
	}

	/**
	 * The get method returns the item, cursor.next.getData()
	 */
	public String get( ) 
	{
		String str = "";
		if (!this.isEmpty( ) && !this.atTheEnd( ))
		{
			str = this.cursor.next.getData( );
		}
		else
		{
			System.out.println( "Exception: no data to get at the end of the list." );
		}
		return str;
	}

	/**
	 * get moves the cursor to the specified index position, 
	 * and then invokes get() to return the item.
	 */
	public String get( int index )
	{
		this.goToItem( index );     
		return this.get( );
	}


	/**
	 * Returns the first element in the list.
	 */
	public String getFirst( )
	{
		return this.get( 0 );
	}

	/**
	 * Returns the last element in the list.
	 */
	public String getLast( )
	{
		Node2 anchor = this.cursor;		// saves cursor
		
		if (this.isEmpty( ))
		{
			return null;
		}

		this.goToLastPlace( );
		String str = this.get( );
		
		this.cursor = anchor;			// restores cursor
		return str;
	}


	/**
	 * set moves the cursor to the specified index position, and 
	 * then invokes set() to assign the string value parameter, str,
	 * to the existing node.
	 */
	public void set( String str )
	{
		if (! this.atTheEnd())
		{
			this.cursor.next.setData( str );
		}
	}

	
	
	/**
	 * set moves the cursor to the specified index position, and 
	 * then invokes set() to assign the string value parameter, str,
	 * to the existing node.
	 */
	public void set( int index, String str )
	{
		Node2 anchor = this.cursor;		// saves cursor
		
		this.goToItem( index );     

		this.set( str );                // this.cursor.next.setData( str );
		
		this.cursor = anchor;			// restores cursor
	}

	

	/**
	 * inserts a Node2 for the element immediately after
	 * the Node2 referenced by cursor.
	 */
	public void add( String element ) 
	{ 
		this.cursor.next = new Node2( this.cursor, element, this.cursor.next );
		this.cursor.next.next.prev = this.cursor.next;
	}

	/**
	 * Inserts a Node2 for the element in the specified index place.
	 */
	public void add( int index, String element )
	{
		this.goToItem( index );
		this.add( element );
	}

	/**
	 * Inserts a Node2 for the element immediately after
	 * the top dummy node.
	 */
	public void prefix( String element )
	{ 
		this.top.next = new Node2( this.top, element, this.top.next );
		this.top.next.next.prev = this.top.next;
	}

	/**
	 * No element is at the end of the list. Whenever an element is added
	 * to the end of the list, it becomes the last element.
	 *
	 *
	 * This append method inserts a Node2 for the element at the end of the
	 * list, which, physically, will immediately come before the end (dummy)
	 * node.
	 */
	public void append( String element )
	{		
		Node2 anchor = this.cursor;		// saves cursor

		this.goToTheEnd( );
		this.add( element );

		this.cursor = anchor;			// restores cursor
	}

	/**
	 * Returns the value, curror.next.data, and re-links the list to exclude
	 * the node that contained the data.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remove( ) throws Exception
	{
		String str = this.cursor.next.getData( );

		if (this.atTheEnd( ))
		{
			throw new Exception( "No element at the end of the list." );
		}

		this.cursor.next      = this.cursor.next.next;
		this.cursor.next.prev = this.cursor.next;

		return str;
	}

	/**
	 * First moves the cursor to the index specified location in the list,
	 * and then calls this.remove to remove and return the value.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remove( int index )
	{
		Node2 anchor = this.cursor;   // saves the cursor
		
		String str = "";
		try
		{
			this.goToItem( index );
			str = this.remove( );
		}
		catch (Exception e)
		{
			System.out.println( "Exception: attempt to remove an item beyond on the end of the list" );
		}

		this.cursor = anchor;	// restores the cursor
		return str;
	}

	/**
	 * Returns a count of how many elements are contained within the list.
	 */
	public int size( )
	{
		Node2 anchor = this.cursor;   // saves the cursor
		int count = 0;

		/*******************************************************************
		 *  TODO
		 *  
		 *  3) Use a for loop to complete the code to return a count, i.e.
		 *     the size, of how many elements are contained in the list. 
		 *     
		 *     Use the above methods to your advantage.
		 *     
		 * ******************************************************************
		 */
		this.goToTheEnd();
		for(int i = 0; i < this.getIndex(); i++){
		//for(this.goToFirstPlace(); !this.atTheEnd(); this.goToLastPlace()){
			count++;
		}


		this.cursor = anchor;	// restores the cursor
		return count;
	}


	/**
	 * Returns the index in the list for temp; or -1 if it is not in the list.
	 */
	public int find( String temp )
	{
		Node2 anchor = this.cursor;	// saves the cursor

		this.goToFirstPlace( );
		int index = 0;
		while (! this.atTheEnd( ))
		{
			if (this.cursor.next.getData( ).equals( temp ))
			{
				this.cursor = anchor;	// restores the cursor
				return index;
			}
			this.goToNextPlace( );
			index++;
		}

		this.cursor = anchor;		// restores the cursor
		return -1;
	}


	/**
	 * Returns a string for all of the elements in the list from 
	 * top to bottom.
	 */
	public String toString( )
	{
		Node2 anchor = this.cursor;   // saves the cursor
		String returnString = "";

		/*************************************************************************
		 *  TODO
		 *  
		 *  4) Use a for loop to concatenate a string for the chain of elements
		 *     in the list.
		 *     
		 *     Use the above List methods to your advantage.
		 *  
		 * ************************************************************************
		 */
		
		for(this.goToFirstPlace(); !this.atTheEnd(); this.goToNextPlace()){
			returnString += this.get();
		}

		
		
		
		
		
		this.cursor = anchor; // restores the cursor
		return returnString;
	}

	/**
	 * Returns a string for the elements in the list 
	 * indexed between start and stop, inclusively.
	 */
	public String display( int start, int stop )
	{
		Node2 anchor = cursor;   // saves the cursor	
		String returnString = "";

		if (start < 0 || start > stop || stop > this.size( ))
		{
			throw new IllegalArgumentException( );
		}	

		this.goToItem( start );

		while (! this.atTheEnd() && start <= stop)
		{
			/*******************************************************************
			 *  TODO
			 *  
			 *  5) Insert one line of code to concatenate, with the returnString,
			 *     the string for the next element in the chain of elements.
			 * ******************************************************************
			 */
			returnString += this.get(start);
			

			this.goToNextPlace();
			start++;
		}

		this.cursor = anchor;	   // restores the cursor
		return returnString;
	}
	
	
	/**
	 * Returns the index for the cursor position in the list.
	 */
	public int getIndex( )
	{
		Node2 anchor = cursor;	   // saves the cursor

		int index = 0;
		this.cursor = this.top;

		while (this.cursor != anchor)
		{
			this.goToNextPlace( );
			index++;
		}

		this.cursor = anchor;      // restores the cursor -- redundant
		return index;
	}

	/**
	 * Swaps two elements in the list, the element at index j
	 * with the element at index k.
	 */
	public void swap( int j, int k )
	{
		Node2 anchor = this.cursor;	   // saves the cursor

		String temp = this.get( j );	
		this.set( j, this.get( k ) );

		/*******************************************************************
		 *  TODO
		 *  
		 *  6) Insert one line of code to complete the swap method
		 * ******************************************************************
		 */
		this.set(k, temp);

		
		
		this.cursor = anchor;	   // restores the cursor
	}

	
	/**
	 * Empties the list.
	 */
	public void clearAll( )
	{
		this.goToFirstPlace( );
		try
		{
			while (! this.isEmpty( ))
			{
				this.remove( );
			}
		}
		catch (Exception e)
		{
			System.out.println( "Exception. Attempt to remove from an empty list." );
		}
	}

	/**
	 * Reverses the order of the list.
	 */
	public void reverse( )
	{
		Node2 anchor = this.cursor;

		this.goToItem( 1 );
		while ( ! this.atTheEnd( ) )
		{
			try
			{
				this.prefix( this.remove( ) );
			}
			catch (Exception e)
			{
			}
		}

		this.cursor = anchor;
	}

	public static void main( String[ ] args )
	{
		List list = new List( "  List\n  ----\n" );

		list.add( "one " );
		list.add( "two " );
		list.add( "three " );

		System.out.println( list.toString( ) );

		list.reverse();
		System.out.println( list.toString( ) );

		list.reverse();
		System.out.println( list.toString( ) );

	}
}