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

    if(list.size() == 0)
    {
      throw new IllegalArgumentException("Cannot have null values");
    }
    if(list.size() > 0)
    {
      this.value = list.get(0);
    }
    Node current = this;

    for(int i = 1; i < list.size(); i++)
    {
      Node newNode = new Node(list.get(i));
      current.next = newNode;
      newNode.prev = current;
      current = newNode;
    }
    
    
    // TODO: implement this
  }

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    List<Integer> list = new ArrayList<Integer>();

    Node current = this;

     list.add(current.value);
     while(current != null)
     {
      list.add(current.value);
      current = current.next;
     }
     list.remove(0);

    
    // TODO: Implement this
    return list;
  }
}
