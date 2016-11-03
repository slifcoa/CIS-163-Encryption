

/** -----------------------------------------------------------------------
 * 
 * Description:  Node defines a link in a chained structure.
 * 
 * ------------------------------------------------------------------------ */

public class Node
 { 
    protected Object data;   // data stored in this node 
    public    Node   next;   // reference to next node in the chain

    public Node( )
    { 
        this( null, null ); 
    } 

    public Node( Object data, Node next )
    { 
        this.data = data; 
        this.next = next; 
    } 

    public Object getData( )
    { 
        return data; 
    } 

    public void setData( Object data )
    { 
        this.data = data; 
    } 
    
    public String toString()
    {
    	return "" + data;
    }

} 