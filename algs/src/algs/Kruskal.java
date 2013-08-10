/* Kruskal.java */

package algs;

import struct.*;
import struct.graph.*;

public class Kruskal {

  private WUGraph G;
  private UnionFind uf;
  private MinPQ<WUEdge> pq;
  private WUGraph MST;
  private int totalWeight;

  public Kruskal(WUGraph g) {
    G = g;
    uf = new UnionFind(G.V());
    pq = new MinPQ<WUEdge>(G.V());
    MST = new WUGraph(G.V());
    totalWeight = 0;
    findMST();
  }

  private void findMST() {
    for(int u = 0; u < G.V(); u++) {
      for(WUEdge e : G.adj(u)) {
        pq.insert(e);
      }
    }
    int numUsed = 0;
    while(!pq.isEmpty() && numUsed < G.V() - 1) {
      WUEdge e = pq.removeMin();
      int u = e.one();
      int v = e.other(u);
      if(uf.connected(u, v)) {
        continue;
      }
      MST.addEdge(u, v, e.weight());
      uf.union(u, v);
      totalWeight += e.weight();
    }
  }

  public WUGraph MST() {
    return MST;
  }

  public boolean hasMST() {
    return MST.E() == MST.V() - 1;
  }

  public Iterable<WUEdge> edges() {
    Queue<WUEdge> q = new Queue<WUEdge>();
    for(int i = 0; i < MST.V(); i++) {
      for(WUEdge e : MST.adj(i)) {
        if(e.one() == i) {
          q.enqueue(e);
        }
      }
    }
    return q;
  }

  public int weight() {
    return totalWeight;
  }

  public static void main(String[] args) {
    WUGraph wg = new WUGraph(6);
    wg.addEdge(0, 4, 1);
    wg.addEdge(0, 1, 7);
    wg.addEdge(1, 2, 9);
    wg.addEdge(1, 3, 5);
    wg.addEdge(1, 5, 6);
    wg.addEdge(2, 4, 3);
    wg.addEdge(2, 5, 4);
    wg.addEdge(3, 4, 8);
    wg.addEdge(3, 5, 4);
    System.out.println(wg);
    Kruskal k = new Kruskal(wg);
    for(WUEdge edge : k.edges()) {
      System.out.println(edge);
    }
    System.out.println(k.weight());
    System.out.println(k.hasMST());
  }
}
