import java.util.List;
import java.util.Map;

public class ScrewtapeInterpreter {

  private Node tapeHead;
  private Node tapePointer;

  public ScrewtapeInterpreter() {
    tapeHead = new Node(0);
    tapePointer = tapeHead;
  }

  public List<Integer> getTapeData() {
    return tapeHead.toList();
  }

  public void setTape(List<Integer> data) {
    tapeHead = new Node(data);
    tapePointer = tapeHead;
  }

  public int getTapePointerValue() {
    return tapePointer.value;
  }

  public void moveTapePointerToHead() {
    tapePointer = tapeHead;
  }

  public void moveTapePointerToTail() {
    while (tapePointer.next != null) {
      tapePointer = tapePointer.next;
    }
  }

  public Map<Integer, Integer> bracketMap(String program) {
    // TODO: Implement this
    // Hint: use a stack
    return null;
  }

  public String execute(String program) {
    // TODO: Implement this
    // If you get stuck, you can look at hint.md for a hint
    return null;
  }
}
