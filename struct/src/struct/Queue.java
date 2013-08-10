/* Queue.java */

package struct;

import java.util.Iterator;

public class Queue<E> implements Iterable<E> {

  private DList<E> q;

  public Queue() {
    q = new DList<E>();
  }

  public int size() {
    return q.size();
  }

  public boolean isEmpty() {
    return q.isEmpty();
  }

  public void enqueue(E item) {
    q.add(item);
  }

  public E dequeue() {
    return q.remove(0);
  }

  public Iterator<E> iterator() {
    return q.iterator();
  }
}
