/* Matrix.java */

package matrix;

import java.util.*;

public abstract class Matrix {

  protected Object[][] m;
  protected int rows;
  protected int columns;

  public Matrix(int r, int c) {
    rows = r;
    columns = c;
    m = new Object[r][c];
  }

  public Matrix(Object[][] mat) {
    m = new Object[mat.length][];
    rows = mat.length;
    columns = mat[0].length;
    for(int i = 0; i < m.length; i++) {
      m[i] = Arrays.copyOf(mat[i], mat[i].length);
      if(m[i].length != columns) {
        throw new BadMatrixException("Not a rectangle");
      }
    }
  }

  protected abstract Matrix newMatrix(int r, int c);

  protected abstract Matrix newMatrix(Object[][] mat);

  protected abstract Object mul(Object a, Object b);

  protected abstract Object add(Object a, Object b);

  protected abstract Object sub(Object a, Object b);

  protected abstract Object newObj();

  protected abstract Object[][] newObjArray(int r, int c);

  protected abstract String repr(Object a);

  public Matrix subMatrix(int i, int j) {
    int rowflag = 0;
    int colflag = 0;
    Object[][] newmat = newObjArray(rows - 1, columns - 1);
    for(int r = 0; r < rows; r++) {
      if(r == i) {
        rowflag = 1;
      } else {
        for(int c = 0; c < columns; c++) {
          if(c == j) {
            colflag = 1;
          } else {
            newmat[r - rowflag][c - colflag] = m[r][c];
          }
        }
      }
      colflag = 0;
    }
    return newMatrix(newmat);
  }

  public String toString() {
    StringBuilder str = new StringBuilder("\n");
    for(int r = 0; r < rows; r++) {
      str.append('|');
      for(int c = 0; c < columns; c++) {
        str.append(repr(m[r][c]));
        str.append('|');
      }
      str.append("\n\n");
    }
    return str.toString();
  }
}
