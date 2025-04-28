import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ScrewtapeInterpreter 
{

  /** The head of the doubly linked list representing the tape. */
  private Node tapeHead;

  /** The pointer to the current node in the tape. */
  private Node tapePointer;

  /**
   * Constructs a new Screwtape interpreter with an initialized memory tape of a single node set to 0.
   */
  public ScrewtapeInterpreter() 
  {
    tapeHead = new Node(0);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the current state of the memory tape as a list of integers.
   * 
   * @return A list of integers representing the values in the memory tape, starting from the head.
   */
  public List<Integer> getTapeData() 
  {
    return tapeHead.toList();
  }

  /**
   * Replaces the current memory tape with a new set of values.
   * 
   * @param data A list of integers to initialize the memory tape. Each integer will correspond to a memory node.
   * @throws IllegalArgumentException If the list is null or empty.
   */
  public void setTape(List<Integer> data) 
  {
    tapeHead = new Node(data);
    tapePointer = tapeHead;
  }

  /**
   * Retrieves the value in the memory node currently pointed to by the tape pointer.
   * 
   * @return The integer value of the current memory node.
   */
  public int getTapePointerValue() 
  {
    return tapePointer.value;
  }

  /**
   * Moves the tape pointer to the head of the memory tape.
   */
  public void moveTapePointerToHead() 
  {
    tapePointer = tapeHead;
  }

  /**
   * Moves the tape pointer to the tail of the memory tape.
   */
  public void moveTapePointerToTail() 
  {
    while (tapePointer.next != null) 
    {
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
  public Map<Integer, Integer> bracketMap(String program) //class of the datatype, datatypes passed in the datastructure
  {
    // TODO: Implement this
    
    //key is where the loop will end, value is where the loop begins
    Map<Integer, Integer> mapper = new HashMap<>();
    Stack<Integer> stacker = new Stack<>(); 

    for(int i = 0; i < program.length(); i++)
    {
      if (program.charAt(i) == '[')
      {
        stacker.push(i);
      }
      if (program.charAt(i) == ']')
      {
        mapper.put(i, stacker.pop()); //nothing passed in pop method. FILO nothing needs to be in there
      }
    }
      if (!stacker.isEmpty()) //if stacker is not empty means theres not a match, [....no end.
        {
        throw new IllegalArgumentException();
        }
  
    // Hint: use a stack
    return mapper;
  };

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
  public String execute(String program) 
  {
    // TODO: Implement this
    // If you get stuck, you can look at hint.md for a hint
    Map<Integer, Integer> screws = bracketMap(program);
    String word = ""; // tracker variable
      
    for(int i = 0; i < program.length(); i++)
    {
      if (program.charAt(i) == '<') 
      {
          if (tapePointer.prev == null)
          {
            List<Integer> screwed = new ArrayList<>();  
            screwed.add(0); //adds an arraylist
            screwed.addAll(getTapeData()); //calls to list method made prevously
            tapeHead = new Node(screwed); //use the node constructor from wave 1 and takes a list of int values, changes to doublylink list
            moveTapePointerToHead(); //tapenode needs to move one to the left
          }
          else{
            tapePointer = tapePointer.prev; //     prev.prev.node,prevnode,currentnode
          }
      }
      if (program.charAt(i) == '>') 
      {
        if (tapePointer.next == null)
        {
          List<Integer> screwed = new ArrayList<>();  
          screwed.addAll(getTapeData()); //calls to list method made prevously
          screwed.add(0); //adds an arraylist
          tapeHead = new Node(screwed); //use the node constructor from wave 1 and takes a list of int values, changes to doublylink list
          moveTapePointerToHead(); //tapenode needs to move one to the left. sets node stored at tape head
          moveTapePointerToTail(); //moves current to point to the last node of the linked list, which was the one created previouly
        }
        else{
          tapePointer = tapePointer.next; //
        }
        
      }
      if (program.charAt(i) == '.') 
      {
          word += (char) getTapePointerValue(); 
      }
      if (program.charAt(i) == '+') 
      {
          tapePointer.value = getTapePointerValue() + 1; //add 1 to the node that the tapepointer is currently pointing to
      }
      if (program.charAt(i) == '-') 
      {
        tapePointer.value = getTapePointerValue() - 1; //sub 1 to the node that the tapepointer is currently pointing to
      }
      if(screws.containsKey(i)) //keys to map are ] ending braces 
      {
          if(getTapePointerValue() != 0) //if value isnt 0, change i to be index of the recorded [.
          {
            i = screws.get(i); //check if we are at the end of a ], value of current node must not be = 0.
          }
      }
    }
    return word;
  }
}
