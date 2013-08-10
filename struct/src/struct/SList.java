/* SList.java */

package struct;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Linked list class.
 *
 * @param <E> the type of the elements held in this list.
 */
public class SList<E> implements Iterable<E>, List<E> {
  DListNode<E> head;
  int size;

  /**
   * Constructs an empty list.
   */
  public SList() {
    head = null;
    size = 0;
  }

  /**
   * Returns an iterator over the elements in this list.
   *
   * @return an iterator over the elements in this list.
   */
  public Iterator<E> iterator() {
    return new SListIterator<E>(this);
  }

  /**
   * Appends the specified element to the end of this list.
   *
   * @param o element to be appended to this list.
   * @return true if this list changed as a result of this call.
   */
  public boolean add(E o) {
    add(size, o);
    return true;
  }

  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @param index index at which the element is to be inserted.
   * @param o element to be inserted.
   * @throws IndexOutOfBoundsException if the specified index is out of range.
   */
  public void add(int index, E o) {
    if(index > size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    if(size == 0) {
      head = new DListNode<E>(o);
      size++;
      return;
    }
    DListNode<E> current = head;
    for(int i = 0; i < index - 1; i++) {
      current = current.next;
    }
    current.next = new DListNode<E>(o, current.next);
    size++;
  }

  /**
   * Appends all of the elements of the specified collection to the end of this list.
   *
   * @param c collection containing the items to be appended.
   * @return true if this list changed as a result of this call.
   * @throws NullPointerException if the specified collection is null.
   */
  public boolean addAll(Collection<? extends E> c) {
    return addAll(size, c);
  }

  /**
   * Inserts all of the elements of the specified collection at the specified index of this list.
   *
   * @param index index at which the elements are to be inserted.
   * @param c collection containing the items to be inserted.
   * @return true if the list changed as a result of this call.
   * @throws IndexOutOfBoundsException if the specified index is out of range.
   * @throws NullPointerException if the specified collection is null.
   */
  public boolean addAll(int index, Collection<? extends E> c) {
    if(c == null) {
      throw new NullPointerException();
    }
    DListNode<E> current = head;
    if(index > size || size < 0) {
      throw new IndexOutOfBoundsException();
    }
    for(E elem : c) {
      add(index, elem);
      index++;
    }
    return true;
  }

  /**
   * Removes all elements from this list.
   */
  public void clear() {
    head = null;
    size = 0;
  }

  /**
   * Returns true if this list contains the specified element.
   *
   * @param o the element to be looked for in this list.
   * @return true if this list contains the specified element.
   */
  public boolean contains(Object o) {
    DListNode<E> current = head;
    while(current != null) {
      if(current.item.equals(o)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  /**
   * Returns true if this list contains all the elements of the specified collection.
   *
   * @param c collection containing the elements to be looked for in this list.
   * @return true if this list contains all the elements of the specified collection.
   * @throws NullPointerException if the specified collection is null.
   */
  public boolean containsAll(Collection<?> c) {
    if(c == null) {
      throw new NullPointerException();
    }
    for(Object obj : c) {
      if(! contains(obj)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Unsupported.
   *
   * @throws UnsupportedOperationException
   */
  public boolean equals(Object o) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of element to return.
   * @return the element at the specified position in this list.
   * @throws IndexOutOfBoundsException if the specified index is out of range.
   */
  public E get(int index) {
    if(index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    DListNode<E> current = head;
    for(int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.item;
  }

  /**
   * Returns the index of the first occurence of the specified element, or -1
   * if this list does not contain the element.
   *
   * @param o element to search for.
   * @return the index of the specified element or -1 if this list does not contain the element.
   */
  public int indexOf(Object o) {
    int index = 0;
    for(E elem : this) {
      if(elem.equals(o)) {
        return index;
      }
      index++;
    }
    return -1;
  }

  /**
   * Returns true if this list contains no elements.
   *
   * @return true if this list contains no elements.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the index of the last occurence of the specified element, or -1
   * if this list does not contain the element.
   *
   * @param o element to search for.
   * @return the last index of the specified element or -1 if this list does not contain the element.
   */
  public int lastIndexOf(Object o) {
    int index = 0;
    int lastIndex = -1;
    for(E elem : this) {
      if(elem.equals(o)) {
        lastIndex = index;
      }
      index++;
    }
    return lastIndex;
  }

  /**
   * Unsupported.
   *
   * @throws UnsupportedOperationException
   */
  public ListIterator<E> listIterator() {
    throw new UnsupportedOperationException();
  }

  /**
   * Unsupported.
   *
   * @throws UnsupportedOperationException
   */
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException();
  }

  /**
   * Removes the element at the specified index.
   *
   * @param index index of the element to be removed.
   * @return the element previously contained at the specified index.
   */
  public E remove(int index) {
    if(index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    E elem;
    if(index == 0) {
      elem = head.item;
      head = head.next;
    } else {
      DListNode<E> current = head;
      for(int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      elem = current.next.item;
      current.next = current.next.next;
    }
    size--;
    return elem;
  }

  /**
   * Removes the first occurence of the specified element from this list.
   *
   * @param o element to be removed.
   * @return true if the list changed as a result of this call.
   */
  public boolean remove(Object o) {
    if(head.item.equals(o)) {
      head = head.next;
      size--;
      return true;
    }
    DListNode<E> current = head.next;
    DListNode<E> prev = head;
    while(current != null) {
      if(current.item.equals(o)) {
        prev.next = prev.next.next;
        size--;
        return true;
      }
      prev = current;
      current = current.next;
    }
    return false;
  }

  /**
   * Removes the first occurence of each element of the specified collection from this list.
   *
   * @param c collection containing the elements to be removed.
   * @return true if the list changed as a result of this call.
   */
  public boolean removeAll(Collection<?> c) {
    boolean flag = false;
    for(Object elem : c) {
      if(remove(elem)) {
        flag = true;
      }
    }
    return flag;
  }

  /**
   * Removes all elements of this list that are not contained in the specified collection.
   *
   * @param c collection specifying which elements to be retained.
   * @return true if the list changed as a result of this call.
   */
  public boolean retainAll(Collection<?> c) {
    boolean flag = false;
    DListNode<E> current = head;
    while(current != null) {
      if(! c.contains(current.item)) {
        remove(current.item);
        flag = true;
      }
      current = current.next;
    }
    return flag;
  }

  /**
   * Replace the element at the specified position with this element.
   *
   * @param index index of the element to replace.
   * @param o element to be stored at the specified position.
   * @return the element previously stored at the specified index.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  public E set(int index, E o) {
    if(index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    DListNode<E> current = head;
    for(int i = 0; i < index; i++) {
      current = current.next;
    }
    E elem = current.item;
    current.item = o;
    return elem;
  }

  /**
   * Returns the size of this list.
   *
   * @return the size of this list.
   */
  public int size() {
    return size;
  }

  /**
   * Unsupported.
   *
   * @throws UnsupportedOperationException
   */
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns an array containing all of the elements of this list.
   *
   * @return an array containing the elements of this list.
   */
  public Object[] toArray() {
    if(size == 0) {
      return null;
    }
    Object[] values = new Object[size];
    DListNode<E> current = head;
    for(int i = 0; i < size; i++) {
      values[i] = current.item;
      current = current.next;
    }
    return values;
  }

  /**
   * Returns an array containing all of the elements in this list
   * in the correct order; the runtime type of the returned array
   * is that of the specified array. If the list fits in the
   * specified array, it is returned therein. Otherwise, a new
   * array is allocated with the runtime type of the specified
   * array and the size of this list.
   *
   * If the list fits in the specified array with room to spare
   * (i.e., the array has more elements than the list), the
   * element in the array immediately following the end of the
   * collection is set to null. This is useful in determining the
   * length of the list only if the caller knows that the list
   * does not contain any null elements.
   *
   * @param a the array in which elements are to be stored if big enough.
   * @return an array containing the elements of the list.
   * @throws ArrayStoreException if the runtime type is not a supertype
   * of every element of this list.
   * @throws NullPointerException if the specified array is null.
   */
  public <T> T[] toArray(T[] a) {
    if(size > a.length) {
      a = (T[]) new Object[size];
    }
    DListNode<E> current = head;
    int i;
    for(i = 0; i < size; i++) {
      a[i] = (T) current.item;
      current = current.next;
    }
    try {
      a[i] = null;
    } catch(IndexOutOfBoundsException e) {
    }
    return a;
  }

  /**
   * Return a String representation of this list.
   *
   * @return a String represenation of this list.
   */
  public String toString() {
    String s = "[";
    DListNode<E> current = head;
    while(current != null) {
      s += current.item + ", ";
      current = current.next;
    }
    if(s.length() > 1) {
      s = s.substring(0, s.length() - 2);
    }
    s += "]";
    return s;
  }







  /**
   * Iterator for the SList class.
   */
  public class SListIterator<E> implements Iterator<E> {

    private SList<E> sl;
    private DListNode<E> current;
    /**
     * Constructor.
     *
     * @param s the SList to be iterated over.
     */
    public SListIterator(SList<E> s) {
      sl = s;
      current = sl.head;
    }

    /**
     * Returns true if the iteration has more elements.
     *
     * @return true if the iterator has more elements.
     */
    public boolean hasNext() {
      return current != null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element (of type E).
     * @throws NoSuchElementException iteration has no more elements.
     */
    public E next() {
      if(current == null) {
        throw new NoSuchElementException();
      }
      E value = current.item;
      current = current.next;
      return value;
    }

    /**
     * Unsupported.
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }





  public static void main(String[] args) {
    SList<Integer> s = new SList<Integer>();
    for(int i = 0; i < 10; i++) {
      s.add(new Integer(i));
    }
    System.out.println(s);
    for(Integer in : s) {
      System.out.println(in);
    }
    s.add(5);
    s.add(3, 50);
    System.out.println(s);
    System.out.println(s.contains(5));
    java.util.LinkedList<Integer> l = new java.util.LinkedList<Integer>();
    l.add(100);
    l.add(101);
    l.add(102);
    System.out.println(l);
    s.addAll(5, l);
    System.out.println(s);
    System.out.println(s.size());
    l.add(50);
    System.out.println(s.containsAll(l));
    l.add(5);
    System.out.println("retaining all");
    s.retainAll(l);
    System.out.println(s);
    System.out.println(s.size());
    s.clear();
    for(int i = 0; i < 10; i++) {
      s.add(i);
    }
    System.out.println(s);
    System.out.println(s.containsAll(l));
    System.out.println(s.get(5));
    s.add(7);
    System.out.println(s.indexOf(7));
    System.out.println(s.lastIndexOf(7));
    System.out.println(s);
    System.out.println(s.remove(10));
    System.out.println(s);
    System.out.println(s.remove(new Integer(5)));
    System.out.println(s);
    s.set(0, 10);
    s.set(1, 11);
    System.out.println(s);
  }
}
