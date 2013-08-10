/* BinaryHeap.java */

package struct;

public abstract class BinaryHeap<E extends Comparable<E>> {

  public final static int DEFAULTSIZE = 100;

  private E[] heap;
  private int size;

  public BinaryHeap() {
    this(DEFAULTSIZE);
  }

  public BinaryHeap(int size) {
    heap = (E[]) new Comparable[size];
    this.size = 0;
  }

  public BinaryHeap(E[] items) {
    this(items.length * 2);
    for(int i = 1; i <= items.length && items[i] != null; i++) {
      heap[i] = items[i];
      size++;
    }
    bottomUp();
  }

  private void exchange(int pos1, int pos2) {
    E temp = heap[pos1];
    heap[pos1] = heap[pos2];
    heap[pos2] = temp;
  }

  protected abstract boolean higher(E elem1, E elem2);

  private void sink(int pos) {
    while((pos * 2 <= size && higher(heap[pos * 2], heap[pos])) || (pos * 2 + 1 <= size && higher(heap[pos * 2 + 1], heap[pos]))) {
      if(pos * 2 + 1 > size || higher(heap[pos * 2], heap[pos * 2 + 1])) {
        exchange(pos, pos * 2);
        pos *= 2;
      } else {
        exchange(pos, pos * 2 + 1);
        pos = pos * 2 + 1;
      }
    }
  }

  private void swim(int pos) {
    while(pos > 1 && higher(heap[pos], heap[pos / 2])) {
      E temp = heap[pos];
      exchange(pos, pos / 2);
      pos = pos / 2;
    }
  }

  private void resize() {
    E[] newHeap = (E[]) new Comparable[heap.length * 2];
    for(int i = 1; i <= heap.length - 1; i++) {
      newHeap[i] = heap[i];
    }
    heap = newHeap;
  }


  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size <= 0;
  }

  private void bottomUp() {
    for(int i = size; i >= 1; i--) {
      sink(i);
    }
  }

  protected E top() {
    return heap[1];
  }
  
  protected E removeTop() {
    E ret = heap[1];
    heap[1] = heap[size];
    size--;
    sink(1);
    return ret;
  }

  public void insert(E item) {
    size++;
    if(size == heap.length) {
      resize();
    }
    heap[size] = item;
    swim(size);
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for(int i = 1; i <= size; i++) {
      str.append(heap[i] + " ");
    }
    return str.toString();
  }
}
