/* EditDistance.java */

package algs;

/**
 * Class to find the edit distance between strings.
 */
public class EditDistance {

  private final double addCost;
  private final double delCost;
  private final double subCost;

  /**
   * Construct a new EditDistance object with default weights.
   */
  public EditDistance() {
    this(1.0, 1.0, 1.0);
  }

  /**
   * EditDistance constructor with custom weights.
   *
   * @param add cost of adding a character.
   * @param del cost of deleting a character.
   * @param sub cost of substituting a character.
   */
  public EditDistance(double add, double del, double sub) {
    addCost = add;
    delCost = del;
    subCost = sub;
  }

  /**
   * Compute the levenshtein distance from one string to another.
   *
   * @param from string from which to compute edit distance.
   * @param to string to which edit distance is computed.
   * @return the edit distance from {@code from} to {@code to}
   */
  public double levenshtein(String from, String to) {
    double[][] dist = new double[from.length() + 1][to.length() + 1];
    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist[i].length; j++) {
        if (i == 0) {
          dist[i][j] = j * addCost;
        } else if (j == 0) {
          dist[i][j] = i * delCost;
        } else {
          dist[i][j] = findDistance(from, to, dist, i, j);
        }
        System.out.println(i + " " + j + " " + dist[i][j]);
      }
    }
    return dist[dist.length - 1][dist[0].length - 1];
  }

  private double findDistance(
      String from,
      String to,
      double[][] dist,
      int i,
      int j) {
    double add = addCost;
    double del = delCost;
    double sub = 0;
    if (j > 0) {
      add += dist[i][j - 1];
    } else {
      add += (i + 1) * delCost;
      sub = j;
      System.out.println("set sub to j");
    }
    if (i > 0) {
      del += dist[i - 1][j];
    } else {
      del += (j + 1) * addCost;
      sub = sub == 0 ? i : sub;
    }
    if (i > 0 && j > 0) {
      sub = sub == 0 ? dist[i - 1][j - 1] : sub;
    }
    sub += (from.charAt(i - 1) == to.charAt(j - 1) ? 0 : subCost);
    System.out.println("  " + add + " " + del + " " + sub);
    return Math.min(add, Math.min(del, sub));
  }
}
