/* UndirectedEdge.java */

package struct;

public class UndirectedEdge implements Comparable<UndirectedEdge> {

  private final int u;
  private final int v;
  private final int weight;

  public UndirectedEdge(int u, int v, int w) {
    this.u = u;
    this.v = v;
    weight = w;
  }

  public int one() {
    return u;
  }

  public int other(int u) {
    if(u == this.u) {
      return this.v;
    } else if(u == this.v) {
      return this.u;
    } else {
      return -1;
    }
  }

  public int weight() {
    return weight;
  }

  public int compareTo(UndirectedEdge other) {
    return weight - other.weight;
  }

  public String toString() {
    return u + " <-" + weight + "-> " + v;
  }
}
