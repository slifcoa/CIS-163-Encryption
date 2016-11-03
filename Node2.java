 

import java.io.Serializable;

public class Node2 implements Serializable
{
    protected String data;  // data stored in this node 
    public Node2 prev;       // reference to previous node in the chain
    public Node2 next;       // reference to next node in the chain

    public Node2( )
    {
        this(null, null, null);
    }

    public Node2(Node2 prev, String data, Node2 next )
    {
        this.data = data; 
        this.prev = prev;
        this.next = next; 
    }

    public String getData()
    { 
        return data; 
    } 

    public void setData(String data)
    { 
        this.data = data; 
    } 

}