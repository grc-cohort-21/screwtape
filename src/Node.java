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
    if (list == null || list.isEmpty())
    {
      throw new IllegalArgumentException("List is null.");
    }

    this.value = list.get(0);
    this.prev = null;
    this.next = null;
    Node current = this;
    int index = 1; // because 0 already used by head

    while (index < list.size())
    {
        Node newNode = new Node(list.get(index)); // get 7
        if (this.next == null) // will only happen once; at start of traverse
        {
          newNode.prev = this; // 5 <- 7
          newNode.next = null; // 5 <- 7 -> null
          this.next = newNode; // 5 <-> 7 -> null
          current = this.next; // current: 7
        } else 
        { // newNode: 3
          newNode.prev = current; // 7 <- 3
          newNode.next = null;    // 7 <- 3 -> null;
          current.next = newNode; // 7 <-> 3 -> null
        }
        index++;
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
    Node current = this;
    List<Integer> list = new ArrayList<>();

    while (current != null)
    {
      list.add(current.value);
      current = current.next;
    }

    return list;
  }
}
