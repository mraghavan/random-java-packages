/* MinPQ.java */

package struct;

public class MinPQ<E extends Comparable <E>> {

  private MinBinaryHeap<E> h;

  public MinPQ() {
    h = new MinBinaryHeap<E>();
  }

  public MinPQ(int size) {
    h = new MinBinaryHeap<E>(size);
  }

  public int size() {
    return h.size();
  }

  public boolean isEmpty() {
    return h.isEmpty();
  }

  public void insert(E item) {
    h.insert(item);
  }

  public E min() {
    return h.min();
  }

  public E removeMin() {
    return h.removeMin();
  }
}
