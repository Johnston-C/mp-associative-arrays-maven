package edu.grinnell.csc207;

import edu.grinnell.csc207.util.AssociativeArray;
import edu.grinnell.csc207.util.NullKeyException;
import edu.grinnell.csc207.util.KeyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * A place for you to put your own tests (beyond the shared repo).
 *
 * @author Cade Johnston
 */
public class TestsFromStudent {
  /**
   * Does using set with a key already in the array replace the value for that key?
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void johnstonCadeTest1() throws KeyNotFoundException, NullKeyException {
    AssociativeArray<Integer, String> miniProjects = new AssociativeArray<Integer, String>();
    String[] miniProjectNames = new String[]{"Classical encryption", "Fun with Fractions", "Exploring blocks of ASCII", "Associative arrays", "Augmentive and Alternative Communication Devices"};
    // Fill an AssociativeArray with unique elements.
    for (int i = 0; i < miniProjectNames.length; i++) {
      miniProjects.set(i+1, miniProjectNames[i]);
    } // for [i]
    // Ensure all of the elements were added to the array.
    for (int i = 0; i < miniProjectNames.length; i++) {
      assertEquals(miniProjectNames[i],miniProjects.get(i+1));
    } // for [i]
    String[] simplifiedNames = new String[]{"Encryption", "Calculator", "ASCII blocks", "Hash maps", "AAC"};
    // Replace the values of each of those elements.
    for (int i = 0; i < simplifiedNames.length; i++) {
      miniProjects.set(i+1, simplifiedNames[i]);
    } // for [i]
    // Ensure all of the values made it into the array.
    for (int i = 0; i < simplifiedNames.length; i++) {
      assertEquals(simplifiedNames[i],miniProjects.get(i+1));
    } // for [i]
    // Check that the length of the array is equal to the count of its original elements.
    assertEquals(miniProjectNames.length, miniProjects.size());
  } // johnstonCadeTest1()

  /**
   * Are the correct errors thrown in the correct circumstances?
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void johnstonCadeTest2() throws KeyNotFoundException, NullKeyException {
    AssociativeArray<String,String> abbottCostello = new AssociativeArray<String,String>();
    String[] positions = new String[]{"First Base","Second Base", "Third Base", null};
    String[] names = new String[]{"Who", "What", "I Don't Know", "Today"};
    // Test for 'NullKeyException' expected from set.
    try {
      for (int i = 0 ; i < positions.length; i++) {
        abbottCostello.set(positions[i],names[i]);
      } // for [i]
      // If the exception has not been thrown yet, this test fails.
      fail("No 'NullKeyException' thrown by set().");
    } catch (NullKeyException e) {
      // Assuming we got the 'NullKeyException', we will now test for the 'KeyNotFoundException'.
      try {
        abbottCostello.get(null);
/*
        for (int i = 0; i < positions.length; i++) {
          if (abbottCostello.get(positions[i]).equals(names[i])) {
            // Should we somehow get here, we have both added 'null' as a key and not thrown the exception.
            fail("Item with key 'null' added to array. No 'KeyNotFoundException' thrown by get().");
          } // if
        } // for [i]
 */
        // If the exception has not been thrown yet, this test fails.
        fail("No 'KeyNotFoundException' thrown by get().");
      } catch (KeyNotFoundException knf) {
        // If we get here, do nothing as all tests were passed.
      } // try / catch
    } // try / catch
  } // johnstonCadeTest2()

  /**
   * Can an AssociatedArray be reduced to zero elements?
   * @throws NullKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void johnstonCadeEdge1() throws KeyNotFoundException, NullKeyException {
    AssociativeArray<String, Integer> paramCount = new AssociativeArray<String, Integer>();
    String[] methods = new String[]{"set", "get", "hasKey", "remove", "size", "toString", "clone"};
    Integer[] count = new Integer[]{2, 1, 1, 1, 0, 0, 0};
    // Fill the AssociativeArray with values.
    for (int i = 0; i < methods.length; i++) {
      paramCount.set(methods[i],count[i]);
    } // for [i]
    // We will try to empty the AssociativeArray of values.
    for (int i = 0; i < methods.length; i++) {
      paramCount.remove(methods[i]);
    } // for [i]
    // We will now check the size of the Associative array.
    try {
      if(paramCount.size() > 0) {
        fail("AssociativeArray is not empty when it should be.");
      } // if
      // If the size is zero, this test passes.
    // Should a NullPointerException be thrown because 'pairs' is a null pointer...
    } catch (NullPointerException e) {
      // Do nothing, as an implementation that reduces the array to a null pointer has technically emptied the array.
    } // try / catch
  } // johnstonCadeEdge1()
} // class TestsFromStudent
