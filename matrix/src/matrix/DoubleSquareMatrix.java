/* DoubleSquareMatrix.java */

package matrix;

import java.util.Arrays;

public class DoubleSquareMatrix extends SquareMatrix {

  public DoubleSquareMatrix(int d) {
    super(d);
  }

  public DoubleSquareMatrix(Double[][] mat) {
    super(mat);
  }

  public Polynomial characteristicPolynomial() {
    Polynomial[][] pols = new Polynomial[rows][columns];
    for(int r = 0; r < rows; r++) {
      for(int c = 0; c < columns; c++) {
        double[] coeff = new double[2];
        coeff[0] = (Double) m[r][c];
        if(r == c) {
          coeff[1] = -1;
        }
        pols[r][c] = new Polynomial(coeff);
      }
    }
    PolynomialSquareMatrix p = new PolynomialSquareMatrix(pols);
    Polynomial polly = (Polynomial) p.determinant();
    polly.trim();
    return polly;
  }

  public double[] eigenvalues() {
    Polynomial polly = characteristicPolynomial();
    return polly.roots();
  }

  protected Matrix newMatrix(int r, int c) {
    if(r != c) {
      throw new BadMatrixException(String.format("Not a square matrix: %d != %d", r, c));
    }
    return new DoubleSquareMatrix(r);
  }

  protected Matrix newMatrix(Object[][] mat) {
    return new DoubleSquareMatrix((Double[][]) mat);
  }

  protected Object mul(Object a, Object b) {
    return ((Double) a) * ((Double) b);
  }

  protected Object add(Object a, Object b) {
    return ((Double) a) + ((Double) b);
  }

  protected Object sub(Object a, Object b) {
    return ((Double) a) - ((Double) b);
  }

  protected Object newObj() {
    return new Double(0.0);
  }

  protected Object[][] newObjArray(int r, int c) {
    return new Double[r][c];
  }

  protected String repr(Object a) {
    return String.format("%6.2f", ((Double) a).doubleValue()) + " ";
  }

  public static void main(String[] args) {
    Double[][] mat = new Double[3][3];
    mat[0][0] = 0.0;
    mat[0][1] = 1.0;
    mat[0][2] = 2.0;
    mat[1][0] = 1.0;
    mat[1][1] = 0.0;
    mat[1][2] = 1.0;
    mat[2][0] = 2.0;
    mat[2][1] = 1.0;
    mat[2][2] = 0.0;
    DoubleSquareMatrix s = new DoubleSquareMatrix(mat);
    System.out.println(s);
    System.out.println(s.characteristicPolynomial());
    for(double d : s.eigenvalues()) {
      System.out.println(d);
    }
  }
}
