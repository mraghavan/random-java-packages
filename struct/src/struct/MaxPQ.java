/* MaxPQ.java */

package struct;

public class MaxPQ<E extends Comparable <E>> {

  private MaxBinaryHeap<E> h;

  public MaxPQ() {
    h = new MaxBinaryHeap<E>();
  }

  public MaxPQ(int size) {
    h = new MaxBinaryHeap<E>(size);
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

  public E max() {
    return h.max();
  }

  public E removeMax() {
    return h.removeMax();
  }
}
