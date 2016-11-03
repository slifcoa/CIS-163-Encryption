

/** -----------------------------------------------------------------------
 * 
 * Description:  An implementation of a stack as a linked structure.
 * 
 * A stack is a structure with three methods:
 * 
 *                           push, pop, and peek
 *                           
 * As a linked implementation,
 * 
 *                             this.top.data 
 *                             
 * denotes the top item on the stack.
 * 
 * <<< Examples >>>
 * 
 * --------------
 * An empty stack
 * --------------     	
 *     					                       Stack s = new Stack();
 * this.top                  null					
 *               
 *                 
 * ----------------------------
 * A stack that contains 1 item
 * ----------------------------
 *     					                            s.push( "one" );
 * this.top.data              "one"
 * this.top.next              null
 *             
 *                 
 * ----------------------------
 * A stack that contains 3 item 
 * ---------------------------- 
 *     					                            s.push( "one" );
 *     					                            s.push( "two" );
 *     					                            s.push( "three" );
 * this.top.data              "three"
 * this.top.next              link forward
 *                 
 * this.top.next.data         "two"
 * this.top.next.next         link forward
 *                 
 * this.top.next.next.data    "one"
 * this.top.next.next.next    null
 * 
 * --------------------------------------------------------------------------
 */

public class Stack
{
	protected Node top;

	public Stack( )
	{
		this.top = null;
	}

	/**
	 * The push method creates a new node, the reference to which is 
	 * assigned to this.top.
	 */
	public void push( Object obj )
	{
		this.top = new Node(obj, top);
		/***************************************************
		 *  TODO
		 *  
		 *  1) Write the code to define the push method. 
		 *  
		 *  << One line of code. >>
		 *  
		 * *************************************************
		 */

	}

	/**
	 * Given that this.top is not null, the "pop" method returns the 
	 *
	 *                            this.top.data
	 *                            
	 *  value, first saved in a local variable, after it assigns 
	 *  
	 *                            this.top.next
	 *  to
	 *                               this.top 
	 *                               
	 */
	public Object pop( )
	{
		Object item = null;

		try
		{
			if ( this.top != null)
			{
				item = this.peek();
				this.top = this.top.next;
				return item;
				/*******************************************************************
				 *  TODO
				 *  
				 *  2) Write the code to complete the definition for the pop method.
				 *  
				 *     <<Two lines of code.>>
				 * ******************************************************************
				 */
				
			}
			else
			{
				throw new Exception( );
			}
		}
		catch (Exception e)
		{
			System.out.println( "     Exception: attempt to pop an empty stack" );
		}

		return item;
	}

	public Object peek( )
	{
		if (this.top != null)
		{
			return this.top.getData( );
		}
		else
		{
			return null;
		}
	}

	public int size( )
	{
		int count = 0;

		Node link = this.top;
		while (link != null)
		{
			link = link.next;
			count++;
		}

		return count;
	}

	public String toString()
	{
		String str = "";

		Node link = this.top;
		while (link != null)
		{
			str += link.toString() + "\n" ;  // The order is important!
			link = link.next;
		}

		return str;
	}

	public static void main( String[ ] args )
	{
		Stack s = new Stack();
		String str;

		s.push( "one" );
		s.push( "two" );
		s.push( "three" );

		System.out.println( "     top:  " + s.peek( ) );
		System.out.println( "     size: " + s.size( ) );
		str = (String)(s.pop());

		str = (String)s.pop();
		str = (String)s.pop();
		str = (String)s.pop();
		str = (String)s.pop();
	}
}