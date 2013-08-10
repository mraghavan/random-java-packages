/* MaxBinaryHeap.java */

package struct;

public class MaxBinaryHeap<E extends Comparable<E>> extends BinaryHeap<E> {

  public MaxBinaryHeap() {
    super();
  }

  public MaxBinaryHeap(int size) {
    super(size);
  }

  public MaxBinaryHeap(E[] items) {
    super(items);
  }

  protected boolean higher(E elem1, E elem2) {
    return elem1.compareTo(elem2) > 0;
  }

  public E max() {
    return top();
  }

  public E removeMax() {
    return removeTop();
  }

  public static void main(String[] args) {
    MaxBinaryHeap<Integer> h = new MaxBinaryHeap<Integer>(11);
    for(int i = 0; i < 20; i++) {
      h.insert(i);
    }
    System.out.println(h);
    System.out.println("size: " + h.size());
    while(! h.isEmpty()) {
      System.out.println(h.removeMax());
    }
  }
}
