/* WDGraph.java */

package struct.graph;

import struct.*;

public class WDGraph extends AbstractGraph<WDEdge> {

  public WDGraph(int V) {
    super(V);
  }

  public void addEdge(int u, int v, int weight) {
    WDEdge e = new WDEdge(u, v, weight);
    if(edges[u].add(e)) {
      E++;
    }
  }

  public static void main(String[] args) {
    WDGraph g = new WDGraph(5);
    g.addEdge(3, 4, 5);
    g.addEdge(4, 3, 3);
    g.addEdge(4, 3, 2);
    g.addEdge(1, 3, 2);
    System.out.println(g);
  }
}

