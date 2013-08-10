/* UWUEdge.java */

package struct.graph;

import struct.*;

public class UWUEdge extends AbstractEdge implements Comparable<UWUEdge> {

  public UWUEdge(int u, int v) {
    super(u, v);
  }

  public String toString() {
    return u + " <--> " + v;
  }

  public int compareTo(UWUEdge e) {
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
}
