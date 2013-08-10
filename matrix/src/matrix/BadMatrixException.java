/* BadMatrixException.java */

package matrix;

/**
 * Exception thrown when bad inputs are given to a matrix.
 */
public class BadMatrixException extends RuntimeException {

  public BadMatrixException() {
    super();
  }

  public BadMatrixException(String message) {
    super(message);
  }
}
