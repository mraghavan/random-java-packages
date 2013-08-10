/* BinarySearchTree.java */

package struct;

public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

  protected BinaryTreeNode<E> root;
  protected int size;

  public BinarySearchTree() {
    size = 0;
  }

  public int size() {
    return size;
  }

  public boolean add(E o) {
    if(root == null) {
      root = new BinaryTreeNode<E>(o, null, null, null);
      size++;
      return true;
    }
    return add(o, root);
  }

  private boolean add(E o, BinaryTreeNode<E> node) {
    if(node.entry.equals(o)) {
      return false;
    } else if(o.compareTo(node.entry) < 0) {
      if(node.left == null) {
        node.left = new BinaryTreeNode<E>(o, null, null, node);
        size++;
        return true;
      } else {
        return add(o, node.left);
      }
    } else if(o.compareTo(node.entry) > 0) {
      if(node.right == null) {
        node.right = new BinaryTreeNode<E>(o, null, null, node);
        size++;
        return true;
      } else {
        return add(o, node.right);
      }
    } else {
      return false;
    }
  }

  public boolean contains(E o) {
    if(root == null) {
      return false;
    }
    return contains(o, root);
  }

  private boolean contains(E o, BinaryTreeNode<E> node) {
    if(node.entry.equals(o)) {
      return true;
    } else if(node.left != null && o.compareTo(node.entry) < 0) {
      return contains(o, node.left);
    } else if(node.right != null && o.compareTo(node.entry) > 0) {
      return contains(o, node.right);
    } else {
      return false;
    }
  }

  private void addTo(Queue q, BinaryTreeNode node) {
    if(node == null) {
      return;
    }
    if(node.left != null) {
      addTo(q, node.left);
    }
    q.enqueue(node.entry);
    if(node.right != null) {
      addTo(q, node.right);
    }
  }

  public java.util.Iterator<E> iterator() {
    Queue<E> q = new Queue<E>();
    addTo(q, root);
    return q.iterator();
  }

  private void strVersion(BinaryTreeNode node, int depth, StringBuilder str) {
    if(node.right != null) {
      strVersion(node.right, depth + 1, str);
    } else {
      str.append("\n");
    }
    for(int i = 0; i < depth; i++) {
      str.append("  ");
    }
    str.append(node.entry.toString() + "\n");
    if(node.left != null) {
      strVersion(node.left, depth + 1, str);
    } else {
      str.append("\n");
    }
  }

  protected BinaryTreeNode<E> getNode(E o) {
    if(root == null) {
      return null;
    }
    BinaryTreeNode<E> last = root;
    BinaryTreeNode<E> node = root;
    while(node != null) {
      if(node.entry.compareTo(o) > 0) {
        node = node.left;
      } else if(node.entry.compareTo(o) < 0) {
        node = node.right;
      } else {
        return node;
      }
    }
    return last;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    strVersion(root, 0, str);
    return str.toString();
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    for(int i = 0; i < 10; i++) {
      int k = (int) (100 * Math.random());
      System.out.println("Inserting " + k);
      bst.add(k);
    }
    System.out.println(bst);
    for(Integer j : bst) {
      System.out.println(j);
    }
  }
}
