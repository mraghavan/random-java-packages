/* AbstractEdge.java */

package struct.graph;

import struct.*;

public abstract class AbstractEdge {

  protected int u;
  protected int v;
  
  public AbstractEdge(int u, int v) {
    this.u = u;
    this.v = v;
  }

  public int one() {
    return u;
  }

  public int other(int w) {
    if(w == u) {
      return v;
    } else if(w == v) {
      return u;
    } else {
      return -1;
    }
  }

  public boolean equals(Object other) {
    AbstractEdge e = (AbstractEdge) other;
    if(u == e.u && v == e.v) {
      return true;
    } else if(u == e.v && v == e.u) {
      return true;
    } else {
      return false;
    }
  }
}
