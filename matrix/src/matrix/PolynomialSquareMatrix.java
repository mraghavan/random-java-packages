/* PolynomialSquareMatrix.java */

package matrix;

import java.util.*;

public class PolynomialSquareMatrix extends SquareMatrix {

  public PolynomialSquareMatrix(int d) {
    super(d);
  }

  public PolynomialSquareMatrix(Polynomial[][] mat) {
    super((Object[][]) mat);
  }

  protected Matrix newMatrix(int r, int c) {
    if(r != c) {
      throw new BadMatrixException(String.format("Not a square matrix: %d != %d", r, c));
    }
    return new PolynomialSquareMatrix(r);
  }

  protected Matrix newMatrix(Object[][] mat) {
    return new PolynomialSquareMatrix((Polynomial[][]) mat);
  }

  protected Object mul(Object a, Object b) {
    return ((Polynomial) a).mul((Polynomial) b);
  }

  protected Object add(Object a, Object b) {
    return ((Polynomial) a).add((Polynomial) b);
  }

  protected Object sub(Object a, Object b) {
    return ((Polynomial) a).sub((Polynomial) b);
  }

  protected Object newObj() {
    return new Polynomial(0);
  }

  protected Object[][] newObjArray(int r, int c) {
    return new Polynomial[r][c];
  }

  protected String repr(Object a) {
    return ((Polynomial) a).toString();
  }

  public static void main(String[] args) {
    Polynomial[][] newmat = new Polynomial[2][2];
    newmat[0][0] = new Polynomial(1);
    newmat[0][0].set(1, 1);
    newmat[0][1] = new Polynomial(1);
    newmat[1][0] = new Polynomial(1);
    newmat[1][1] = new Polynomial(1);
    newmat[1][1].set(1, 1);
    PolynomialSquareMatrix p = new PolynomialSquareMatrix(newmat);
    System.out.println(p);
    System.out.println(p.determinant());
  }
}
