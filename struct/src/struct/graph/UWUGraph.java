/* UWUGraph.java */

package struct.graph;

import struct.*;

public class UWUGraph extends AbstractGraph<UWUEdge> {

  public UWUGraph(int V) {
    super(V);
  }

  public void addEdge(int u, int v) {
    UWUEdge e = new UWUEdge(u, v);
    edges[u].add(e);
    if(edges[v].add(e)) {
      E++;
    }
  }

  public static void main(String[] args) {
    UWUGraph g = new UWUGraph(5);
    g.addEdge(3, 4);
    g.addEdge(4, 3);
    g.addEdge(1, 3);
    System.out.println(g);
  }
}
