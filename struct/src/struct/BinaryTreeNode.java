/* BinaryTreeNode.java */

package struct;

class BinaryTreeNode<E extends Comparable<E>> {

  E entry;
  BinaryTreeNode left;
  BinaryTreeNode right;
  BinaryTreeNode parent;

  public BinaryTreeNode(E e, BinaryTreeNode l, BinaryTreeNode r, BinaryTreeNode p) {
    entry = e;
    left = l;
    right = r;
    parent = p;
  }
}
