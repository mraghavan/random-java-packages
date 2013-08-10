/* Polynomial.java */

package matrix;

import java.util.*;

/**
 * Class to represent polynomials with integer exponents.
 */
public class Polynomial {

  private double[] coefficients;
  private int degree;

  /**
   * Create an empty polynomial of the specified degree.
   *
   * @param deg degree of the polynomial.
   */
  public Polynomial(int deg) {
    coefficients = new double[deg + 1];
    degree = deg;
    for(int i = 0; i < coefficients.length; i++) {
      coefficients[i] = 0;
    }
  }

  /**
   * Create a polynomial based on the given array.
   * Coefficients must be given in the form [ a0, a1, ..., an ]
   * representing the polynomial anx^n + ... + a1x + a0.
   *
   * @param coeff the coefficient array.
   */
  public Polynomial(double[] coeff) {
    coefficients = Arrays.copyOf(coeff, coeff.length);
    degree = coefficients.length - 1;
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the degree of this polynomial.
   */
  public int degree() {
    return degree;
  }

  /**
   * Returns a new polynomial whose coefficents are -1 * this polynomial.
   *
   * @return A new polynomial which is -1 * this polynomial.
   */
  public Polynomial neg() {
    double[] coeff = Arrays.copyOf(coefficients, coefficients.length);
    for(int i = 0; i < coeff.length; i++) {
      coeff[i] = -coeff[i];
    }
    return new Polynomial(coeff);
  }

  /**
   * Returns a new polynomial representing this polynomial + the specified polynomial.
   *
   * @param other the polynomial to be added to this.
   * @return the result of this polynomial + other.
   */
  public Polynomial add(Polynomial other) {
    int newDegree = Math.max(this.degree, other.degree);
    Polynomial newPoly = new Polynomial(newDegree);
    for(int i = 0; i < newPoly.coefficients.length; i++) {
      if(i >= coefficients.length) {
        newPoly.coefficients[i] = other.coefficients[i];
      } else if(i >= other.coefficients.length) {
        newPoly.coefficients[i] = coefficients[i];
      } else {
        newPoly.coefficients[i] = coefficients[i] + other.coefficients[i];
      }
    }
    return newPoly;
  }

  /**
   * Returns a new polynomial representing this polynomial - the specified polynomial.
   *
   * @param other the polynomial to be subtracte from this.
   * @return the result of this polynomial - other.
   */
  public Polynomial sub(Polynomial other) {
    return this.add(other.neg());
  }

  /**
   * Returns a new polynomial representing this polynomial * the specified polynomial.
   *
   * @param other the polynomial to be multiplied by this.
   * @return the result of this polynomial * other.
   */
  public Polynomial mul(Polynomial other) {
    Polynomial result = new Polynomial(degree + other.degree);
    for(int i = 0; i < coefficients.length; i++) {
      for(int j = 0; j < other.coefficients.length; j++) {
        result.coefficients[i + j] += coefficients[i] * other.coefficients[j];
      }
    }
    return result;
  }

  /**
   * Sets the specified coefficient to the specified value.
   *
   * @param deg the degree at which to set the coefficient.
   * @param c the coefficient to be set.
   */
  public void set(int deg, double c) {
    coefficients[deg] = c;
  }

  /**
   * Returns the value of this polynomial at the given x-value.
   *
   * @param x the value at which to evaluate this polynomial.
   * @return the value of this polynomial at the given value.
   */
  public double f(double x) {
    double total = 0;
    for(int i = 0; i < coefficients.length; i++) {
      total += coefficients[i] * Math.pow(x, i);
    }
    return total;
  }

  /**
   * Returns a polynomial representing the derivative of this polynomial.
   *
   * @return the derivative of this polynomial.
   */
  public Polynomial derivative() {
    Polynomial newPoly = new Polynomial(degree - 1);
    for(int i = 1; i < coefficients.length; i++) {
      newPoly.coefficients[i - 1] = i * coefficients[i];
    }
    return newPoly;
  }

  /**
   * Takes this derivative of this polynomial at the specificed value.
   *
   * @param x the value at which to take the derivative.
   * @return the derivative at the specified value.
   */
  public double derivativeAt(double x) {
    return derivative().f(x);
  }

  /**
   * Returns a polynomial representing the integral of this polynomial.
   * The coefficient for x^0 is always set to 0.
   *
   * @return a plynomial representing the integral of this polynomial.
   */
  public Polynomial integral() {
    Polynomial newPoly = new Polynomial(degree + 1);
    for(int i = 1; i < newPoly.coefficients.length; i++) {
      newPoly.coefficients[i] = coefficients[i - 1] / i;
    }
    return newPoly;
  }

  /**
   * Takes this integral of this polynomial over the specificed interval.
   *
   * @param low lower bound for the integral.
   * @param high upper bound for the integral.
   * @return the integral over the specified interval.
   */
  public double integral(double low, double high) {
    Polynomial newPoly = integral();
    return newPoly.f(high) - newPoly.f(low);
  }

  private boolean done(double guess) {
    return done(guess, .0000000000000001);
  }
  
  private boolean done(double guess, double TOLERANCE) {
    return Math.abs(f(guess)) < TOLERANCE;
  }

  private double update(double x) {
    if(derivativeAt(x) == 0.0) {
      return x;
    }
    return x - f(x) / derivativeAt(x);
  }

  public double newtonRoot(double guess) {
    int k = 0;
    final int MAX = 50000;
    while((! done(guess)) && k < MAX) {
      guess = update(guess);
      k++;
    }
    return guess;
  }

  /**
   * Finds the roots of this polynomial.
   * May contain a single root multiple times.
   *
   * @return a sorted array of doubles containing the roots.
   */
  public double[] roots() {
    double[] r;
    switch(degree) {
      case 0:
        return null;
      case 1:
        r = new double[1];
        r[0] = -coefficients[0] / coefficients[1];
        return r;
      default:
        double[] derivRoots = derivative().roots();
        double[] guesses = new double[derivRoots.length + 1];
        r = new double[guesses.length];
        guesses[0] = derivRoots[0] - 1;
        for(int i = 1; i < derivRoots.length - 1; i++) {
          guesses[i] = (derivRoots[i] + derivRoots[i + 1]) / 2;
        }
        guesses[guesses.length - 1] = derivRoots[derivRoots.length - 1] + 1;
        for(int j = 0; j < guesses.length; j++) {
          r[j] = newtonRoot(guesses[j]);
        }
        return r;
    }
  }

  /**
   * Finds the mulitiplicity of the specified root.
   * The multiplicity of a root is a number greater than 0.
   * Does not work well for roots with multiplicity greater than 4.
   *
   * @param root root whose multiplicity is to be found.
   * @return the multiplicty of the root.
   */
  public int multplicity(double root) {
    final double TOLERANCE = .001;
    int count = 0;
    Polynomial p = this;
    while(p.done(root, TOLERANCE)) {
      count++;
      p = p.derivative();
    }
    return count;
  }

  /**
   * Removes leading 0 coefficients from this polynomial.
   */
  public void trim() {
    int newDeg = degree;
    int i = coefficients.length - 1;
    while(coefficients[i] == 0 && i > 0) {
      newDeg--;
      i--;
    }
    double[] newCoeff = Arrays.copyOf(coefficients, i + 1);
    degree = newDeg;
    coefficients = newCoeff;
  }
        

  /**
   * Returns a string representation of this polynomial.
   *
   * @return a string representation of this polynomial.
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    for(int i = coefficients.length - 1; i >= 0; i--) {
      switch(i) {
        case 0:
          if(coefficients[i] >= 0) {
            str.append(String.format(" +%5.2f", coefficients[i]));
          } else {
            str.append(String.format(" -%5.2f", -coefficients[i]));
          }
          break;
        case 1:
          if(coefficients[i] >= 0) {
            str.append(String.format(" +%5.2fx   ", coefficients[i]));
          } else {
            str.append(String.format(" -%5.2fx   ", -coefficients[i]));
          }
          break;
        default:
          if(coefficients[i] >= 0) {
            str.append(String.format(" +%5.2fx^%-2d", coefficients[i], i));
          } else {
            str.append(String.format(" -%5.2fx^%-2d", -coefficients[i], i));
          }
          break;
      }
    }
    str.deleteCharAt(0);
    if(str.charAt(0) == '+') {
      str.deleteCharAt(0);
      str.insert(0, ' ');
    } else {
      str.deleteCharAt(0);
      int ind = 0;
      while(str.charAt(ind) == ' ') {
        ind++;
      }
      str.insert(ind, '-');
    }
    return str.toString();
  }
  
  public static void main(String[] args) {
    Polynomial p3 = new Polynomial(3);
    for(int i = 0; i <= p3.degree(); i++) {
      p3.set(i, i + 1);
    }
    Polynomial p0 = new Polynomial(0);
    p0.set(0, 2);
    System.out.println(p3);
    System.out.println(p3.mul(p0));
    System.out.println(p0.mul(p3));
    System.out.println(p0.mul(p0));
    System.out.println(p3.mul(p3));
    System.out.println();
    System.out.println();
    System.out.println("testing roots:");
    System.out.println();
    System.out.println();
    Polynomial p2 = new Polynomial(2);
    p2.set(0, 1);
    p2.set(1, -2);
    p2.set(2, 1);
    System.out.println("p3 is: " + p3);
    System.out.println("roots are:");
    for(double root : p3.roots()) {
      System.out.println(root);
    }
    System.out.println();
    System.out.println("p2 is: " + p2);
    System.out.println("roots are:");
    for(double root : p2.roots()) {
      System.out.println(root);
    }
    System.out.println(p2.multplicity(p2.roots()[0]));
    Polynomial p6 = p2.mul(p2).mul(p2);
    System.out.println("p6 is: " + p6);
    System.out.println("roots are:");
    for(double root : p6.roots()) {
      System.out.println(root);
    }
    System.out.println(p6.multplicity(p6.roots()[0]));

    double[] p5c = {-8.5, 2, 5, 1, 0, 3};
    Polynomial p5 = new Polynomial(p5c);
    System.out.println("p5 is: " + p5);
    System.out.println("roots are:");
    for(double root : p5.roots()) {
      System.out.println(root);
    }
  }
}
