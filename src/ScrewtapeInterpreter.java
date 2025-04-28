import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * A Screwtape interpreter that executes programs written in the Screwtape esoteric programming language.
 * 
 * Screwtape is a minimalistic language with the following commands:
 * 
 * - `>`: Move the tape pointer to the next memory node.
 * - `<`: Move the tape pointer to the previous memory node.
 * - `+`: Increment the value in the current memory node.
 * - `-`: Decrement the value in the current memory node.
 * - `.`: Output the character represented by the value in the current memory node.
 * - `[`: Do nothing
 * - `]`: If the value in the current memory node is not 0, jump back to the matching `[`.
 * 
 * This interpreter provides methods to manipulate the memory tape, execute programs, and handle loops efficiently.
 */
public class ScrewtapeInterpreter {

  /** The head of the doubly linked list representing the tape. */
  private Node tapeHead;

  /** The pointer to the current node in the tape. */
  private Node tapePointer;

  /**
   * Constructs a new Screwtape interpreter with an initialized memory tape of a single node set to 0.
   */
  public ScrewtapeInterpreter() {
    tapeHead = new Node(0);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the current state of the memory tape as a list of integers.
   * 
   * @return A list of integers representing the values in the memory tape, starting from the head.
   */
  public List<Integer> getTapeData() {
    return tapeHead.toList();
  }

  /**
   * Replaces the current memory tape with a new set of values.
   * 
   * @param data A list of integers to initialize the memory tape. Each integer will correspond to a memory node.
   * @throws IllegalArgumentException If the list is null or empty.
   */
  public void setTape(List<Integer> data) {
    tapeHead = new Node(data);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the value in the memory node currently pointed to by the tape pointer.
   * 
   * @return The integer value of the current memory node.
   */
  public int getTapePointerValue() {
    return tapePointer.value;
  }

  /**
   * Moves the tape pointer to the head of the memory tape.
   */
  public void moveTapePointerToHead() {
    tapePointer = tapeHead;
  }

  /**
   * Moves the tape pointer to the tail of the memory tape.
   */
  public void moveTapePointerToTail() {
    while (tapePointer.next != null) {
      tapePointer = tapePointer.next;
    }
  }

  /**
   * Creates a map of matching bracket pairs for the given Screwtape program.
   * 
   * The Screwtape language uses brackets `[` and `]` to define loops. This method identifies 
   * matching bracket pairs and returns a map that associates the index of each closing bracket 
   * (`]`) with its corresponding opening bracket (`[`).
   * 
   * For example, in the program `[>+<-]`, the opening bracket at index 0 matches the closing 
   * bracket at index 5. The returned map would contain a single entry: 
   * `{5: 0}`.
   * 
   * A few more examples:
   * 
   * input: `[+++][---]<<[+]`
   * output:`{4: 0, 9: 5, 14: 12}`
   * 
   * input: `[]`
   * output: `{1: 0}`
   * 
   * input: `>[+>[+-]<]`
   * output: `{9: 1, 7: 4}`
   * 
   * 
   * @param program The Screwtape program as a string.
   * @return A map where each key-value pair represents a matching bracket pair.
   * @throws IllegalArgumentException If the program contains unmatched brackets.
   */
  public Map<Integer, Integer> bracketMap(String program) {
    // TODO: Implement this

    //location(index) of closing bracket as key and opening as value
    //index comes from program passed as string (string is array)
    //use stack to track match up for open and closing brackets 

    // Hint: use a stack

    Map<Integer, Integer> brackets = new HashMap<>();
    Stack<Integer> openBracketsIndex = new Stack<>();

    for(int i = 0; i < program.length(); i++)
    {
      if(program.charAt(i) == '[')//opening
      {
        openBracketsIndex.push(i);
      }

      if(program.charAt(i) == ']') //closing
      {
        brackets.put(i, openBracketsIndex.pop());
      }
    }
    if(!openBracketsIndex.isEmpty())
    {
      throw new IllegalArgumentException("Contains unmatched brackets");
    }
    return brackets;
  }

  /**
   * Executes a Screwtape program and returns the output as a string.
   * 
   * The Screwtape program is executed by interpreting its commands sequentially. The memory tape is dynamically 
   * extended as needed, and the tape pointer starts at the head of the tape. Loops defined by brackets 
   * `[` and `]` are executed as long as the current memory node's value is non-zero.
   * 
   * Output is generated using the `.` command, which converts the value in the current memory node 
   * to its corresponding ASCII character. The resulting output is returned as a string.
   * 
   * Example:
   * Program: >++++++++[<+++++++++>-]<.>>++++++++[<+++++++++>-]<+.
   * Output: "HI"
   * 
   * @param program The Screwtape program as a string.
   * @return The output generated by the program as a string.
   * @throws IllegalArgumentException If the program contains unmatched brackets.
   */
  public String execute(String program) {
    // TODO: Implement this

    //create +, - functionality 
    //< = move to prev(left), > = move to next (right)
    //. = print value (cast int to char)
    //loop functionality will be while or do while loop with conditions (until node value = 0)
    
    String result = "";
    
    Node currentNode = new Node(0);
    tapeHead = currentNode;
    tapePointer = currentNode;
    Map<Integer, Integer> bMap = bracketMap(program);

    for(int i = 0; i < program.length(); i++)
    {
      char currentSymbol = program.charAt(i);

      //+ , - functionality
      if(currentSymbol == '+')
        tapePointer.value++;
      else if(currentSymbol == '-')
        tapePointer.value--;

      //< (prev), > (next) functionality
      else if(currentSymbol == '<')
      {
        if(tapePointer.prev == null)
        {
          Node newPrevNode = new Node(0);
          currentNode.prev = newPrevNode;
          newPrevNode.next = currentNode;
          tapePointer = newPrevNode;
          tapeHead = newPrevNode;
          currentNode = newPrevNode;
        }
        else
        {
          tapePointer = tapePointer.prev;
        }
        
      }
      else if(currentSymbol == '>')
      {
        if(tapePointer.next == null)
        {
          Node newNextNode = new Node(0);
          currentNode.next = newNextNode;
          newNextNode.prev = currentNode;
          tapePointer = newNextNode;
          currentNode = newNextNode;
        }
        else
        {
          tapePointer = tapePointer.next;
        }        
      }

      //outputting functionality
      else if(currentSymbol == '.')
      {
        int charValue = tapePointer.value;
        char casted = (char)charValue;
        result += casted;
      }

      //loop functionality
      if(currentSymbol == '[')
      {
        continue;
      }
      else if(currentSymbol == ']')
      {
        if(tapePointer.value != 0)
        {
          i = bMap.get(i);
          // System.out.println(tapePointer.value);
          // System.out.println(i);
          // System.out.println(result);
        }
        else
          continue;
      }












      // if(bMap.values().contains(i))
      // {
      //   int closingIndex = 0;
      //   for(int closing : bMap.keySet())
      //   {
      //     if(i == bMap.get(closing))
      //     {
      //       closingIndex = closing;
      //     }
      //   }

      //   String loop = program.substring(i+1, closingIndex);        
      //   //loop
      //   int loopIndex = 0;
    }
    return result;      
  }
    // If you get stuck, you can look at hint.md for a hint
}
