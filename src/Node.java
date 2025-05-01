import java.util.LinkedList;
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
  public Node(List<Integer> list) 
  {
    if (list == null || list.isEmpty())
      {
        throw new IllegalArgumentException("Error: The list is null or empty");
      }
    
    //Initialize the head node
    this.value = list.get(0);
    Node head = this;

    // Use the list to add the next node and a previous connection for each item
    for (int i = 1; i < list.size(); i++)
    {
      head.next = new Node(list.get(i));
      head.next.prev = head;
      head = head.next;
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
    // Initialize List
    List<Integer> nodeList = new LinkedList<>();

    // Add the first value or zero if empty
    nodeList.add(this.value);

    // If there are no more nodes return list as is
    if (this.next == null)
    {
      return nodeList;
    }

    // Create marker from the second node
    Node check = this.next;

    // Add the current value
    nodeList.add(check.value);
    
    // If there are more nodes go through adding from value and going to the next node
    while (check.next != null)
    {
      check = check.next;
      nodeList.add(check.value);
    }

    // Return list
    return nodeList;

  }
}
