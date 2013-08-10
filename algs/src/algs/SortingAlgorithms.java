/* SortingAlgorithms.java */

package algs;

import java.util.Random;

/**
 * A class implementing several sorting algorithms.
 */
public class SortingAlgorithms {

  private final static Random generator = new Random();
  private final static util.Stopwatch watch = new util.Stopwatch();

  /**
   * Performs QuickSort on the given array.
   * Mutates the array.
   *
   * @param elems the array to be sorted.
   */
  public static void quickSort(Comparable[] elems) {
    shuffle(elems);
    quickSort(elems, 0, elems.length - 1);
  }

  /**
   * Performs Selection Sort on the given array.
   *
   * @param elems the array to be sorted.
   */
  public static void selectionSort(Comparable[] elems) {
    for(int i = 1; i < elems.length; i++) {
      int j = i;
      while(j > 0 && less(elems[j], elems[j - 1])) {
        exchange(elems, j, j - 1);
        j--;
      }
    }
  }

  /**
   * Performs mergesort on the given array.
   *
   * @param elems the array to be sorted.
   */
  public static void mergeSort(Comparable[] elems) {
    Comparable[] aux = new Comparable[elems.length];
    mergeSort(elems, aux, 0, elems.length - 1);
  }

  private static void mergeSort(Comparable[] elems, Comparable[] aux, int low, int high) {
    if(high <= low) {
      return;
    }
    int mid = low + (high - low) / 2;
    mergeSort(elems, aux, low, mid);
    mergeSort(elems, aux, mid + 1, high);
    merge(elems, aux, low, mid, high);
  }

  private static void merge(Comparable[] elems, Comparable[] aux, int low, int mid, int high) {
    for(int i = low; i <= high; i++) {
      aux[i] = elems[i];
    }
    int ind1 = low;
    int ind2 = mid + 1;
    for(int j = low; j <= high; j++) {
      if(ind1 > mid) {
        elems[j] = aux[ind2];
        ind2++;
      } else if(ind2 > high) {
        elems[j] = aux[ind1];
        ind1++;
      } else if(less(aux[ind1], aux[ind2])) {
        elems[j] = aux[ind1];
        ind1++;
      } else {
        elems[j] = aux[ind2];
        ind2++;
      }
    }
  }


  private static void quickSort(Comparable[] elems, int low, int high) {
    if(high <= low) {
      return;
    }
    int i = partition(elems, low, high);
    quickSort(elems, low, i - 1);
    quickSort(elems, i + 1, high);
  }

  private static int partition(Comparable[] elems, int low, int high) {
    Comparable pivot = elems[low];
    int i = low + 1;
    int j = high;
    while(i <= j) {
      while(less(elems[i], pivot)) {
        i++;
        if(i >= high) {
          break;
        }
      }
      while(less(pivot, elems[j])) {
        j--;
        if(j <= low) {
          break;
        }
      }
      if(i >= j) {
        break;
      }
      exchange(elems, i, j);
      i++;
      j--;
    }
    exchange(elems, low, j);
    return j;
  }

  private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  private static void exchange(Object[] elems, int i, int j) {
    Object temp = elems[i];
    elems[i] = elems[j];
    elems[j] = temp;
  }

  private static void shuffle(Object[] elems) {
    int l = elems.length;
    for(int i = 0; i < l; i++) {
      int rand = generator.nextInt(l - i);
      exchange(elems, i, i + rand);
    }
  }

  private static void printArray(Object[] elems) {
    System.out.print("[");
    for(Object o : elems) {
      System.out.print(String.format("%5s", o.toString()));
    }
    System.out.println(" ]");
  }

  private static void resetTest(Object[] elems) {
    watch.reset();
    shuffle(elems);
  }

  public static void main(String[] args) {
    final int SIZE = 100000;
    final int MAX = 100;

    Integer[] ints = new Integer[SIZE];
    for(int i = 0; i < SIZE; i++) {
      ints[i] = i;
    }

    resetTest(ints);
    watch.start();
    mergeSort(ints);
    watch.stop();
    System.out.println("Merge sort: " + watch.elapsedTime() + " seconds");

    resetTest(ints);
    watch.start();
    selectionSort(ints);
    watch.stop();
    System.out.println("Selection sort: " + watch.elapsedTime() + " seconds");

    resetTest(ints);
    watch.start();
    quickSort(ints);
    watch.stop();
    System.out.println("QuickSort: " + watch.elapsedTime() + " seconds");
  }
}
