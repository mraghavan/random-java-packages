/* UWDGraph.java */

package struct.graph;

import struct.*;

public class UWDGraph extends AbstractGraph<UWDEdge> {

  public UWDGraph(int V) {
    super(V);
  }

  public void addEdge(int u, int v) {
    UWDEdge e = new UWDEdge(u, v);
    if(edges[u].add(e)) {
      E++;
    }
  }

  public static void main(String[] args) {
    UWDGraph g = new UWDGraph(5);
    g.addEdge(3, 4);
    g.addEdge(3, 4);
    g.addEdge(4, 3);
    g.addEdge(4, 1);
    g.addEdge(4, 0);
    g.addEdge(1, 3);
    System.out.println(g);
    System.out.println();
    for(AbstractEdge e : g.adj(4)) {
      System.out.println(e);
    }
  }
}
