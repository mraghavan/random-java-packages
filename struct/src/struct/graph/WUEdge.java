/* WUEdge.java */

package struct.graph;

import struct.*;

public class WUEdge extends AbstractEdge implements Comparable<WUEdge> {

  protected int weight;

  public WUEdge(int u, int v, int weight) {
    super(u, v);
    this.weight = weight;
  }

  public int weight() {
    return weight;
  }

  public String toString() {
    return u + " <-" + weight + "-> " + v;
  }

  public int compareTo(WUEdge e) {
    if(this.equals(e)) {
      return 0;
    } else if(this.weight == e.weight) {
      if(this.u < e.u) {
        return -1;
      } else if(this.u > e.u) {
        return 1;
      } else if(this.v < e.v) {
        return -1;
      } else {
        return 1;
      }
    } else {
      return this.weight - e.weight;
    }
  }
}
