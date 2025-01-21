# screwtape

In this assignment you will implement an interpreter for a simple programming language.

## Wave 1
You will implement the constructor for the `Node` class that accepts a list of integers. It should have the `Node` under construction take the first value in the list. It should have its `next` point to a new `Node` that holds the second value in the list. That second node should point back to the first one, and so on for the 3rd, 4th, 5th, etc. nodes. In this way, the constructed node will be the head of a doubly linked list. You will NOT use the LinkedList java class. See the Javadoc for details.

As part of Wave 1, you will also implement additional unit tests. See `NodeTest` for details on what to implement.

## Wave 2
You will implement to `toList` method of the `Node` class. This will take the given node to be the head of the list, and use its values to populate a new List of integers that it will return. You may use whichever type of Collections List you like (e.g. ArrayList, LinkedList, etc.)

As part of Wave 2 you will also implement additional unit tests. See `NodeTest` for details on what to implement.

## Wave 3
You will implement the `bracketMap` method of `ScrewtapeInterpreter`. The input will be a Screwtape program, for example:
```
>[+>[+-]<]
```

Your map should have the location of the CLOSING bracket as the key and the corresponding OPENING bracket as the value. In this case, the map would be:
```
{
    9: 1,
    7: 4
}
```
> Explanation: The closing bracket at index 9 is matched with the opening bracket at index 1. The closing brack at index 7 is matched with the opening bracket at index 4.

You will also implement additional tests for this wave, see ScrewtapeInterpreterTest for details.

## Wave 4
You will implement the `execute` method of ScrewtapeInterpreter. I strongly reccommend taking this bit by bit!

Reccommended order:
1. Add functionality for addition and subtraction
1. Add functionality for left and right movement
1. Add functionality for outputting a value (hint: you can cast an int to a char)
1. Add functionality for loops

The different unit tests independently test these. It is possible to get some tests passing even if you have not completed the full functionality.

I strongly recommend looking at the comment at the top of the ScrewTapeInterpreter file for an explanation of how the language works.

Also remember when you're moving your tapePointer to add new nodes to the beginning or end of your tape if you run out of space.

## Submitting
When finished, please make a PR and submit the link to the PR on Canvas. Nice job, this is a tough project!