/* WUGraph.java */

package struct.graph;

import struct.*;

public class WUGraph extends AbstractGraph<WUEdge> {

  public WUGraph(int V) {
    super(V);
  }

  public void addEdge(int u, int v, int weight) {
    WUEdge e = new WUEdge(u, v, weight);
    edges[u].add(e);
    if(edges[v].add(e)) {
      E++;
    }
  }

  public static void main(String[] args) {
    WUGraph g = new WUGraph(5);
    g.addEdge(3, 4, 5);
    g.addEdge(4, 3, 3);
    g.addEdge(1, 3, 2);
    System.out.println(g);
  }
}

