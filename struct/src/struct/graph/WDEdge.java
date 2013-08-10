/* WDEdge.java */

package struct.graph;

import struct.*;

public class WDEdge extends AbstractEdge implements Comparable<WDEdge> {

  protected int weight;

  public WDEdge(int u, int v, int weight) {
    super(u, v);
    this.weight = weight;
  }

  public int weight() {
    return weight;
  }

  public String toString() {
    return u + " --" + weight + "-> " + v;
  }

  public boolean equals(Object other) {
    WDEdge e = (WDEdge) other;
    return (u == e.u) && (v == e.v);
  }

  public int compareTo(WDEdge e) {
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
