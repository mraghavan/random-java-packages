/* BitOperations.java */

package util;

public class BitOperations {

  public static void printBits(byte b) {
    printBits(b, 8);
  }

  public static void printBits(short s) {
    printBits(s, 16);
  }

  public static void printBits(int n) {
    printBits(n, 32);
  }

  private static void printBits(int n, int size) {
    for(int i = size - 1; i >= 0; i--) {
      System.out.print((n & (1 << i)) >>> i);
    }
    System.out.println();
  }

  public static void main(String[] args) {
    byte b1 = 127;
    byte b2 = -56;
    System.out.println("b1 = " + b1);
    System.out.println("b2 = " + b2);
    System.out.print("b1 is:       ");
    printBits(b1);
    System.out.print("b2 is:       ");
    printBits(b2);
    System.out.print("b1 & b2 is : ");
    printBits((byte) (b1 & b2));
    System.out.print("b1 | b2 is : ");
    printBits((byte) (b1 | b2));
    System.out.print("b1 ^ b2 is : ");
    printBits((byte) (b1 ^ b2));
    System.out.print("~b1 is:      ");
    printBits((byte) ~b1);
    System.out.print("~b2 is:      ");
    printBits((byte) ~b2);
  }
}
