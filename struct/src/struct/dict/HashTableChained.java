/* HashTableChained.java */

package struct.dict;

import struct.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/

  private final int LARGEPRIME;
  private final static int A = 19;
  private final static int B = 23;

  private SList<Entry>[] table;
  private int size;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.
   **/

  public HashTableChained(int sizeEstimate) {
    int num = largestPrimeBelow(sizeEstimate * 2);
    if(num < 1) {
      num = 11;
    }
    LARGEPRIME = largestPrimeBelow(num * 10);
    table = (SList<Entry>[]) new SList[num];
    size = 0;
    for(int i = 0; i < table.length; i++) {
      table[i] = new SList<Entry>();
    }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    this(50);
  }

  private static int largestPrimeBelow(int limit) {
    boolean[] nums = new boolean[limit];
    int largest = 0;
    for(int i = 2; i < nums.length; i++) {
      nums[i] = true;
    }
    for(int i = 2; i < nums.length; i++) {
      if(nums[i]) {
        largest = i;
        for(int j = 2 * i; j < nums.length; j += i) {
          nums[j] = false;
        }
      }
    }
    return largest;
  }


  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    return Math.abs(((A * code + B) % LARGEPRIME)) % table.length;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    Entry e = new Entry();
    e.key = key;
    e.value = value;
    int index = compFunction(key.hashCode());
    table[index].add(0, e);
    size++;
    return e;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int index = compFunction(key.hashCode());
    SList<Entry> chain = table[index];
    for(Entry e : chain) {
      if(key.equals(e.key)) {
        return e;
      }
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    if(size == 0) {
      return null;
    }
    int index = compFunction(key.hashCode());
    SList<Entry> chain = table[index];
    int i = 0;
    int lastIndex = -1;
    for(Entry e : chain) {
      if(key.equals(e.key)) {
        lastIndex = i;
      }
      i++;
    }
    if(lastIndex >= 0) {
      size--;
      return chain.remove(lastIndex);
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for(int i = 0; i < table.length; i++) {
      table[i] = new SList<Entry>();
    }
    size = 0;
  }

  private double load() {
    return size / (double) table.length;
  }

  private int collisions() {
    int numCollisions = 0;
    for(int i = 0; i < table.length; i++) {
      if(table[i].size() > 1) {
        numCollisions += table[i].size() - 1;
      }
    }
    return numCollisions;
  }

  private int expectedCollisions() {
    double n = size;
    double N = table.length;
    return (int) (n - N + N * Math.pow(1 - 1 / N, n));
  }

  private int emptyBuckets() {
    return table.length - size + collisions();
  }

  public String diagnostics() {
    StringBuilder str = new StringBuilder();
    str.append("size: " + size() + "\n");
    str.append("spaces: " + table.length + "\n");
    str.append("load: " + load() + "\n");
    str.append("collisions: " + collisions() + "\n");
    str.append("expected collisions: " + expectedCollisions() + "\n");
    str.append("empty: " + emptyBuckets() + "\n");
    return str.toString();
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for(int i = 0; i < table.length; i++) {
      str.append(i + " ");
      for(Entry e : table[i]) {
        str.append("| ");
      }
      str.append('\n');
    }
    return str.toString();
  }

  public static void main(String[] args) {
    int num = 20;
    HashTableChained htc = new HashTableChained(num);
    for(int i = 0; i < num; i++) {
      htc.insert(i + "", new Integer(i));
    }
    System.out.println(htc);
    System.out.println(htc.remove("1").value());
    System.out.println(htc.remove("1"));
    System.out.println(htc.diagnostics());
  }
}
