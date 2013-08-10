/* Graph.java */

package struct;



public class Graph {

  private SList<Integer>[] edges;
  private int V;
  private int E;

  public Graph(int V) {
    edges = (SList<Integer>[]) new SList[V];
    for(int i = 0; i < edges.length; i++) {
      edges[i] = new SList<Integer>();
    }
    this.V = V;
    E = 0;
  }

  public void addEdge(int v, int w) {
    edges[v].add(0, w);
    edges[w].add(0, v);
    E++;
  }

  public Iterable<Integer> adj(int v) {
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
      for(Integer edge : edges[i]) {
        str.append(i + " -> " + edge + "\n");
      }
    }
    return str.toString();
  }

  public static void main(String[] args) {
    Graph g = new Graph(5);
    g.addEdge(3, 4);
    System.out.println(g);
  }
}
