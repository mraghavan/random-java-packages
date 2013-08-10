package struct;

/**
 * Double linked list class.
 *
 * @param <E> the type of elements held in this list.
 * @see SList
 */
public class DList<E> extends SList<E> {
  protected DListNode<E> tail;

  /**
   * Constructs an empty list.
   */
  public DList() {
    super();
    tail = null;
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
      tail = head;
    } else if(index == 0) {
      head = new DListNode<E>(o, head, null);
      head.next.prev = head;
    } else if(index == size) {
      tail.next  = new DListNode<E>(o, null, tail);
      tail = tail.next;
    } else {
      DListNode<E> current = head;
      for(int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      current.next = new DListNode<E>(o, current.next, current);
      current.next.next.prev = current.next;
    }
    size++;
  }
  
  /**
   * Removes all elements from this list.
   */
  public void clear() {
    super.clear();
    tail = null;
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
    if(index <= size / 2) {
      return super.get(index);
    } else {
      DListNode<E> current = tail;
      for(int i = size - 1; i > index; i--) {
        current = current.prev;
      }
      return current.item;
    }
  }

  /**
   * Returns the index of the last occurence of the specified element, or -1
   * if this list does not contain the element.
   *
   * @param o element to search for.
   * @return the last index of the specified element or -1 if this list does not contain the element.
   */
  public int lastIndexOf(Object o) {
    DListNode<E> current = tail;
    for(int i = size - 1; i >= 0; i--) {
      if(current.item.equals(o)) {
        return i;
      }
      current = current.prev;
    }
    return -1;
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
      head.prev = null;
    } else if(index == size - 1) {
      elem = tail.item;
      tail = tail.prev;
      tail.next = null;
    } else if(index <= size / 2) {
      DListNode<E> current = head;
      for(int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      elem = current.next.item;
      current.next = current.next.next;
      current.next.prev = current;
    } else {
      DListNode<E> current = tail;
      for(int i = size - 1; i > index + 1; i--) {
        current = current.prev;
      }
      elem = current.prev.item;
      current.prev = current.prev.prev;
      current.prev.next = current;
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
      head.prev = null;
      size--;
      return true;
    }
    DListNode<E> current = head.next;
    while(current.next != null) {
      if(current.item.equals(o)) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return true;
      }
      current = current.next;
    }
    if(current.item.equals(o)) {
      tail = tail.prev;
      tail.next = null;
      size--;
      return true;
    }
    return false;
  }

  private void headCheck() {
    DListNode<E> current = head;
    int count = 0;
    while(current != null) {
      System.out.print(" " + current.item);
      current = current.next;
      count++;
    }
    System.out.println();
    if(count != size) {
      System.out.println("Error: " + count + " != " + size);
    } else {
      System.out.println("head check passed");
    }
    return;
  }

  private void tailCheck() {
    DListNode<E> current = tail;
    int count = 0;
    while(current != null) {
      System.out.print(" " + current.item);
      current = current.prev;
      count++;
    }
    System.out.println();
    if(count != size) {
      System.out.println("Error: " + count + " != " + size);
    } else {
      System.out.println("tail check passed");
    }
    return;
  }

  public static void main(String[] args) {
    DList<Integer> d = new DList<Integer>();
    System.out.println(d);
    for(int i = 0; i < 10; i++) {
      d.add(i);
    }
    System.out.println(d);
    java.util.LinkedList<Integer> l = new java.util.LinkedList<Integer>();
    l.add(100);
    l.add(101);
    l.add(102);
    d.addAll(0, l);
    System.out.println(d);
    System.out.println(d.contains(new Integer(100)));
    System.out.println(d.containsAll(l));
    l.add(50);
    System.out.println(d.containsAll(l));
    d.headCheck();
    d.tailCheck();
    System.out.println(d.size());
    d.clear();
    System.out.println(d);
    for(int i = 0; i < 10; i++) {
      d.add(i);
    }
    System.out.println(d.get(0));
    System.out.println(d.get(1));
    System.out.println(d.get(9));
    System.out.println(d.lastIndexOf(new Integer(7)));
    System.out.println(d.remove(new Integer(9)));
    System.out.println(d);
    d.headCheck();
    d.tailCheck();
    l.add(1);
    l.add(3);
    l.add(5);
    l.add(7);
    l.add(9);
    d.retainAll(l);
    System.out.println(d);
    d.headCheck();
    d.tailCheck();
    Integer[] values = new Integer[10];
    values = d.toArray(values);
    for(int i = 0; i < values.length; i++) {
      System.out.print(" " + values[i]);
    }
    System.out.println();
    for(Integer in : d) {
      System.out.println(in);
    }
  }
}

