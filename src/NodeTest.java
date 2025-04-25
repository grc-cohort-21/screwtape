import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

  // -------- WAVE 1 -------

  @Test
  void testListConstructorWithThreeValues() {
    // Arrange
    List<Integer> values = List.of(5, 7, 3);

    // Act
    Node head = new Node(values);

    // Assert
    assertEquals(5, head.value);
    assertNotNull(head.next);
    assertEquals(7, head.next.value);
    assertNotNull(head.next.next);
    assertEquals(3, head.next.next.value);
    assertNull(head.next.next.next);
    assertEquals(head, head.next.prev);
    assertEquals(head.next, head.next.next.prev);
  }

  @Test
  void testListConstructorWithEmptyList() {
    // Arrange
    List<Integer> emptyList = new ArrayList<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new Node(emptyList),
        "Expected constructor to throw IllegalArgumentException for an empty list."
    );
  }

  
  // TODO: Add test for list constructor when passed null list

  @Test
  void testConstructorWithNullList() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new Node((List<Integer>) null),
        "Error: list is null."
    );
  }


  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.

  @Test
  void testSingleElement() {
    List<Integer> single = List.of(42);
    Node head = new Node(single);
    assertEquals(42, head.value);
    assertNull(head.prev);
    assertNull(head.next);
    assertEquals(single, head.toList());
  }

  // -------- WAVE 2 -------

  @Test
  void testToListWithThreeValues() {
    // Arrange
    Node head = new Node(5);
    Node middle = new Node(7);
    Node tail = new Node(3);

    head.next = middle;
    middle.prev = head;
    middle.next = tail;
    tail.prev = middle;

    // Act
    List<Integer> values = head.toList();

    // Assert
    assertEquals(List.of(5, 7, 3), values);
  }

  // TODO: Add test for Node with no next or prev

  @Test
  void testNoNextNoPrevNode() {

    // Arrange
      Node single = new Node(42);

    // Act
      List<Integer> value = single.toList();

    // Assert
      assertEquals(List.of(42), value);
  }

  // TODO: Add at least one more test for list constructor that would be useful and cover new ground.

  @Test
  void testLongList() {
    // Arrange
      List<Integer> values = List.of(1, 2, 3, 4, 5, 6);
  
    // Act
      Node head = new Node(values);
      Node current = head;

        for (int i = 0; i < 3; i++) {
          current = current.next;
        }//end for
  
    // Assert
      assertEquals(4, current.value);
      assertEquals(3, current.prev.value);
      assertEquals(5, current.next.value);
  }
  
}//end nodeTest