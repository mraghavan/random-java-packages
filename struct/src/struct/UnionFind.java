/* UnionFind.java */

package struct;

public class UnionFind {

  private int[] nodes;
  private int count;

  public UnionFind(int numNodes) {
    nodes = new int[numNodes];
    count = numNodes;
    for(int i = 0; i < nodes.length; i++) {
      nodes[i] = -1;
    }
  }

  public int count() {
    return count;
  }

  public boolean connected(int a, int b) {
    return find(a) == find(b);
  }

  public int find(int a) {
    if(nodes[a] < 0) {
      return a;
    }
    nodes[a] = find(nodes[a]);
    return nodes[a];
  }

  public void union(int a, int b) {
    int i = find(a);
    int j = find(b);
    if(i == j) {
      return;
    }
    if(i < j) {
      nodes[i] += nodes[j];
      nodes[j] = i;
    } else {
      nodes[j] += nodes[i];
      nodes[i] = j;
    }
    count--;
  }

  private void printNodes() {
    System.out.println("Nodes: ");
    for(int i = 0; i < nodes.length; i++) {
      System.out.print(nodes[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    UnionFind uf = new UnionFind(10);
    uf.union(1, 2);
    uf.union(3, 4);
    uf.union(5, 6);
    uf.union(1, 6);
    uf.printNodes();
    uf.union(4, 5);
    System.out.println(uf.connected(1, 3));
    System.out.println(uf.count());
    uf.printNodes();
  }
}
