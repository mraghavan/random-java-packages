package struct;

class DListNode<E> {
  public DListNode<E> next;
  public DListNode<E> prev;
  public E item;

  public DListNode(E i, DListNode<E> n, DListNode<E> p) {
    item = i;
    next = n;
    prev = p;
  }

  public DListNode(E i) {
    this(i, null, null);
  }

  public DListNode(E i, DListNode<E> n) {
    this(i, n, null);
  }

  public static void main(String[] args) {
    DListNode<Integer> node = new DListNode<Integer>(5);
    System.out.println(node.item);
    node.next = new DListNode<Integer>(6);
  }
}
