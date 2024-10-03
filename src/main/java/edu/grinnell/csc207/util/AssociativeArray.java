package edu.grinnell.csc207.util;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @param <K> the key type
 * @param <V> the value type
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V>[] pairs;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   *
   * @return a new copy of the array
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> cloneArray = new AssociativeArray<K, V>();
    try {
      for (int i =  0; i < size; i++) {
        cloneArray.set(pairs[i].key, pairs[i].val);
      } // for
    } catch (NullKeyException e) {
      System.err.println("NullKeyException from 'clone()'");
    } // try / catch
    return cloneArray;
  } // clone()

  /**
   * Convert the array to a string.
   *
   * @return a string of the form "{Key0:Value0, Key1:Value1, ... KeyN:ValueN}"
   */
  public String toString() {
    String output = "{";
    for (int i = 0; i < size; i++) {
      output += pairs[i].toString();
      if (i != (size - 1)) {
        output += ", ";
      } // if
    } // for [i]
    output += "}";
    return output;
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   *
   * @param key
   *   The key whose value we are seeting.
   * @param value
   *   The value of that key.
   *
   * @throws NullKeyException
   *   If the client provides a null key.
   */
  public void set(K key, V value) throws NullKeyException {
    boolean extendArray = true;
    if (key == null) {
      throw new NullKeyException();
    } // if
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        pairs[i] = new KVPair<K, V>(key, value);
        i = size;
        extendArray = false;
      } // if
    } // for [i]
    if ((size < pairs.length) && (extendArray)) {
      pairs[size] = new KVPair<K, V>(key, value);
      extendArray = false;
      size++;
    } // if
    if (extendArray) {
      this.expand();
      pairs[size] = new KVPair<K, V>(key, value);
      size++;
    } // if
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @param key
   *   A key
   * @return
   *   The value associated with key.
   * @throws KeyNotFoundException
   *   when the key is null or does not appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    return pairs[find(key)].val;
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   *
   * @param key
   *   The key we want to know whether it is in the array or not.
   * @return
   *   If the key is present in the array.
   */
  public boolean hasKey(K key) {
    try {
      find(key);
      return true;
    } catch (KeyNotFoundException e) {
      return false;
    } // try / catch
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   *
   * @param key
   *   The key we seek to remove from the array.
   */
  public void remove(K key) {
    try {
      for (int j = find(key); j < size - 1; j++) {
        pairs[j] = pairs[j + 1];
      } // for [j]
      pairs[size - 1] = null;
      size--;
    } catch (KeyNotFoundException e) {
      // Do nothing
    } // try / catch
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   *
   * @return
   *   The length of the array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   *
   * @param key
   *   The key of the entry.
   *
   * @return
   *   The index of the first occurance of key.
   * @throws KeyNotFoundException
   *   If the key does not appear in the associative array.
   */
  int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < size; i++) {
      if (pairs[i].key.equals(key)) {
        return i;
      } // if
    } // for [i]
    throw new KeyNotFoundException();
  } // find(K)

} // class AssociativeArray
