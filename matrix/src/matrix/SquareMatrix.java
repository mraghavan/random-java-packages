/* SquareMatrix.java */

package matrix;

import java.util.*;

/**
 * Square matrix class.
 *
 * @see Matrix
 */
public abstract class SquareMatrix extends Matrix {

  /**
   * Construct an empty square matrix of size d.
   *
   * @param d dimension of the matrix
   */
  public SquareMatrix(int d) {
    super(d, d);
  }

  /**
   * Construct an empty matrix corresponding to the given array.
   *
   * @param mat input array to be made into a matrix.
   * @throws BadMatrixException if the given array isn't square.
   */
  public SquareMatrix(Object[][] mat) {
    super(mat);
    if(rows != columns) {
      throw new BadMatrixException(String.format("Not a square matrix: %d != %d", rows, columns));
    }
  }


  /**
   * Return the determinant of this matrix.
   *
   * @return the determinant of this matrix.
   */
  public Object determinant() {
    if(rows == 1) {
      return m[0][0];
    }
    boolean pos = true;
    Object total = newObj();
    for(int i = 0; i < rows; i++) {
      if(pos) {
        total = add(total, mul(m[0][i], ((SquareMatrix) subMatrix(0, i)).determinant()));
      } else {
        total = sub(total, mul(m[0][i], ((SquareMatrix) subMatrix(0, i)).determinant()));
      }
      pos = !pos;
    }
    return total;
  }

}
