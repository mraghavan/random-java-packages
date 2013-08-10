/* UWDEdge.java */

package struct.graph;

import struct.*;

public class UWDEdge extends AbstractEdge implements Comparable<UWDEdge> {

  public UWDEdge(int u, int v) {
    super(u, v);
  }

  public int from() {
    return u;
  }

  public int to() {
    return v;
  }

  public boolean equals(Object other) {
    UWDEdge e = (UWDEdge) other;
    return (u == e.u) && (v == e.v);
  }

  public int compareTo(UWDEdge e) {
    if(this.equals(e)) {
      return 0;
    } else if(this.u < e.u) {
      return -1;
    } else if(this.u > e.u) {
      return 1;
    } else if(this.v < e.v) {
      return -1;
    } else {
      return 1;
    }
  }

  public String toString() {
    return u + " ---> " + v;
  }
}
