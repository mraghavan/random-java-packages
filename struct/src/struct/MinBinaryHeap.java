/* MinBinaryHeap.java */

package struct;

public class MinBinaryHeap<E extends Comparable<E>> extends BinaryHeap<E> {

  public MinBinaryHeap() {
    super();
  }

  public MinBinaryHeap(int size) {
    super(size);
  }

  public MinBinaryHeap(E[] items) {
    super(items);
  }

  protected boolean higher(E elem1, E elem2) {
    return elem1.compareTo(elem2) < 0;
  }

  public E min() {
    return top();
  }

  public E removeMin() {
    return removeTop();
  }

  public static void main(String[] args) {
    MinBinaryHeap<Integer> h = new MinBinaryHeap<Integer>(11);
    for(int i = 20; i > 0; i--) {
      h.insert(i);
    }
    System.out.println(h);
    System.out.println(h.size());
  }
}
