/* SplayTree.java */

package struct;

public class SplayTree<E extends Comparable<E>> extends BinarySearchTree<E> {

  public SplayTree() {
    super();
  }

  private void splay(BinaryTreeNode<E> node) {
    if(node.parent == null) {
      return;
    } else if(node.parent.parent == null) {
      rotate(node);
    } else if(((node == node.parent.left) && (node.parent == node.parent.parent.left))
        || ((node == node.parent.right) && (node.parent == node.parent.right))) {
        rotate(node.parent);
        rotate(node);
        splay(node);
    } else {
      rotate(node);
      rotate(node);
      splay(node);
    }
  }

  private void rotate(BinaryTreeNode<E> node) {
    if(node.parent == null) {
      System.out.println("error: rotate");
      return;
    }
    if(node.parent == root) {
      root = node;
    }
    if(node.parent.parent != null) {
      if(node.parent == node.parent.parent.left) {
        node.parent.parent.left = node;
      } else {
        node.parent.parent.right = node;
      }
    }
    if(node == node.parent.left) {
      node.parent.left = node.right;
      if(node.right != null) {
        node.right.parent = node.parent;
      }
      node.right = node.parent;
      node.parent = node.right.parent;
      node.right.parent = node;
    } else {
      node.parent.right = node.left;
      if(node.left != null) {
        node.left.parent = node.parent;
      }
      node.left = node.parent;
      node.parent = node.left.parent;
      node.left.parent = node;
    }
  }

  private void rotateRight(BinaryTreeNode<E> node) {
    node.parent.left = node.right;
    if(node.right != null) {
      node.right.parent = node.parent;
    }
    node.right = node.parent;
    if(node.parent.parent != null) {
      if(node.parent == node.parent.parent.left) {
        node.parent.parent.left = node;
      } else {
        node.parent.parent.right = node;
      }
    }
    BinaryTreeNode<E> par = node.parent;
    node.parent = node.parent.parent;
    par.parent = node;
  }
  
  private void rotateLeft(BinaryTreeNode<E> node) {
    node.parent.right = node.left;
    if(node.left != null) {
      node.left.parent = node.parent;
    }
    node.left = node.parent;
    if(node.parent.parent != null) {
      if(node.parent == node.parent.parent.left) {
        node.parent.parent.left = node;
      } else {
        node.parent.parent.right = node;
      }
    }
    BinaryTreeNode<E> par = node.parent;
    node.parent = node.parent.parent;
    par.parent = node;
  }


  public boolean add(E o) {
    if(root == null) {
      root = new BinaryTreeNode<E>(o, null, null, null);
      size++;
      return true;
    }
    BinaryTreeNode<E> node = getNode(o);
    if(node.entry.equals(o)) {
      return false;
    }
    if(node.entry.compareTo(o) > 0) {
      node.left = new BinaryTreeNode<E>(o, null, null, node);
      splay(node.left);
    } else {
      node.right = new BinaryTreeNode<E>(o, null, null, node);
      splay(node.right);
    }
    size++;
    return true;
  }

  private boolean add(E o, BinaryTreeNode<E> node) {
    if(node.entry.equals(o)) {
      splay(node);
      return false;
    } else if(o.compareTo(node.entry) < 0) {
      if(node.left == null) {
        node.left = new BinaryTreeNode<E>(o, null, null, node);
        size++;
        splay(node.left);
        return true;
      } else {
        return add(o, node.left);
      }
    } else if(o.compareTo(node.entry) > 0) {
      if(node.right == null) {
        node.right = new BinaryTreeNode<E>(o, null, null, node);
        size++;
        splay(node.right);
        return true;
      } else {
        return add(o, node.right);
      }
    } else {
      return false;
    }
  }

  public boolean contains(E o) {
    BinaryTreeNode<E> node = getNode(o);
    splay(node);
    return node.entry.equals(o);
  }
  
  public static void main(String[] args) {
    SplayTree<Integer> st = new SplayTree<Integer>();
    for(int i = 0; i < 5; i++) {
      int k = (int) (100 * Math.random());
      System.out.println("Inserting " + i);
      st.add(i);
    }
    System.out.println(st);
    System.out.println();
    st.contains(0);
    System.out.println(st);
    System.out.println();
    st.contains(2);
    System.out.println(st);
    for(Integer j : st) {
      System.out.println(j);
    }
  }
}
