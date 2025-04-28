import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Node in a doubly linked list.
 */
public class Node {
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
  public Node(List<Integer> list) {
    // TODO: implement this

    //for loop using given list as index
    //head is first node created using first element in given list
    //next pointer points to new node created using next element in list
    //prev prointer points back to node before current node
    
    //null or empty list
    if(list == null)
      throw new IllegalArgumentException("Null list");
        
    if(list.isEmpty())
      throw new IllegalArgumentException("Empty List");
    

    Node pastNode = new Node(0); //dummy initial value that will be overwritten
    for(int i = 0; i < list.size(); i++)
    {
      if(i == 0)
      {
        //pastNode.next = this;
        this.value = list.get(i);
        pastNode = this;
        pastNode.prev = null;

      }
      else if (i == list.size()-1)
      {
        Node node = new Node(list.get(i));
        pastNode.next = node;
        node.prev = pastNode;
        node.next = null;
      }
      else
      {
        Node node = new Node(list.get(i));
        node.prev = pastNode;
        pastNode.next = node;
        pastNode = node;
      }      
    }
  }

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    // TODO: Implement this
    
    //reverse of what was just done but perhaps simpler
    //while loop to walk theough linked list using next to move forward
    //add each value from nodes to list
    //return list

    List <Integer> list = new ArrayList<>();

    Node node = this;

    while(node != null)
    {
      list.add(node.value);
      node = node.next;
    }

    return list;
  }
}
