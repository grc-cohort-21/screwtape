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
    // Checks for null or empty list
    if(list == null || list.isEmpty()) {
      throw new IllegalArgumentException("List can't be null or empty");
    }

    // Sets the starting point and pointer
    this.value = list.get(0);
    Node current = this;
    // Goes through the list
    for (int i = 1; i < list.size(); i++) {
      // Makes the Node for next
      current.next = new Node(list.get(i));
      // Sets previous for the next Node to the current Node
      current.next.prev = current;
      // Sets the next to the pointer
      current = current.next;
    }
  }

  /**
   * Converts the linked list starting from this node into a list of integers.
   * Treats this node as the head, even if it has a previous value.
   *
   * @return A list of integers representing the values in the linked list.
   */
  public List<Integer> toList() {
    // Make empty list and pointer
    List<Integer> numList = new ArrayList<>();
    Node currNode = this;

    // Go through nodes until it's null
    while(currNode != null) {
      // Add the node value to the list
      numList.add(currNode.value);
      currNode = currNode.next;
    }
    // Return the now filled list
    return numList;
  }
}
