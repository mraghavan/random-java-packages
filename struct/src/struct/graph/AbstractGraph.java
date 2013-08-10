/* AbstractGraph.java */

package struct.graph;

import struct.*;

public abstract class AbstractGraph<Edge extends AbstractEdge & Comparable<Edge>> {

  protected BinarySearchTree<Edge>[] edges;
  protected final int V;
  protected int E;

  public AbstractGraph(int V) {
    edges = (BinarySearchTree<Edge>[]) new BinarySearchTree[V];
    for(int i = 0; i < edges.length; i++) {
      edges[i] = new BinarySearchTree<Edge>();
    }
    this.V = V;
    E = 0;
  }

  public Iterable<Edge> adj(int v) {
    return edges[v];
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public int degree(int v) {
    return edges[v].size();
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for(int i = 0; i < edges.length; i++) {
      for(AbstractEdge edge : edges[i]) {
        if(edge.one() == i) {
          str.append(edge.toString());
          str.append("\n");
        }
      }
    }
    return str.toString();
  }
}

