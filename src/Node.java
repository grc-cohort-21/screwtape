import java.util.ArrayList;
import java.util.List;


// ## Wave 1
// You will implement the constructor for the `Node` class that accepts a list of integers. It should have the `Node` under construction take the first value in the list. It should have its `next` point to a new `Node` that holds the second value in the list. That second node should point back to the first one, and so on for the 3rd, 4th, 5th, etc. nodes. In this way, the constructed node will be the head of a doubly linked list. You will NOT use the LinkedList java class. See the Javadoc for details.

// As part of Wave 1, you will also implement additional unit tests. See `NodeTest` for details on what to implement.


/**
 * Represents a Node in a doubly linked list.
 */
public class Node 
{
  /** The value stored in this node. */
  public int value;

  /** The previous node in the linked list. */
  public Node prev;

  /** The next node in the linked list. */
  public Node next;

  /**
   * Creates a single node with the specified value.
   * prev and next are kept as null.
   *
   * @param value The value to store in this node.
   */
  public Node(int value) {
    this.value = value;
  }

  /**
   * Creates a doubly linked list from the given list of integers.
   * The head of the list will be the constructed Node.
   * 
   * For example, if there were the list:
   * [5, 7, 3]
   * 
   * Then the linked list that would be created would be:
   * 5 <-> 7 <-> 3
   * And this node would be the head, holding a value of 5.
   *
   * @param list The list of integers to initialize the doubly linked list.
   * @throws IllegalArgumentException If the list is null or empty.
   */
  public Node(List<Integer> list) 
  {
    // TODO: implement this
    //This happens first so it can catch any null or empty, making sure the list is valid
    // || left side runs first....cannot do isEmpty on something null
    if (list == null || list.isEmpty())
    {
     
        throw new IllegalArgumentException();
    }


    
    //setting the value field of this.value
    value = list.get(0);

    //create a node variable called current
    //this is referring to the instance of the entire instance of node instead of just a field. 
    Node current = this;

    

    //list is size, array is length
    //start at index 0, -1 to not access an index out of bounds
    for (int i= 0; i< list.size() -1; i++) 
    {
      //creating a node called nextnode 
      //declaring it list.get which is current index plus 1. 
      Node nextNode = new Node(list.get(i +1));
      //current index points to the next node 5 -> 7
      //.next, .prev these are just pointers, does not mean they are iterating. We dont iterate until we = sign it.  
      current.next = nextNode;
      //5 <- 7 creates this but seven to five
      nextNode.prev = current;
      //5 -> 7 
      current = current.next;
    }

  }


  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() 
  {
    // TODO: Implement this
    List<Integer> val = new ArrayList<>();


    //create a node variable called current
    //this is referring to the instance of the entire instance of node instead of just a field. 
    Node current = this;

    while (current != null)
    {
      val.add(current.value);
      current = current.next;
    }

    return val;
  }

}

