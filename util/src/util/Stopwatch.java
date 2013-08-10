/* Stopwatch.java */

package util;

public class Stopwatch {

  public static final double NANO = 1000000000;

  private long lastStart;
  private long total;
  private boolean running;

  public Stopwatch() {
    lastStart = 0;
    total = 0;
    running = false;
  }

  public void start() {
    if(running) {
      return;
    }
    running = true;
    lastStart = System.nanoTime();
  }

  public void stop() {
    long current = System.nanoTime();
    if(!running) {
      return;
    }
    total += current - lastStart;
    running = false;
  }

  public double elapsedTime() {
    long current = System.nanoTime();
    if(running) {
      return (current - lastStart + total) / NANO;
    } else {
      return total / NANO;
    }
  }

  public boolean isRunning() {
    return running;
  }

  public void reset() {
    total = 0;
    running = false;
  }

  public static void main(String[] args) {
    Stopwatch watch = new Stopwatch();
    watch.start();
    try {
      Thread.sleep(4000);
    } catch(InterruptedException e) {
    }
    watch.stop();
    System.out.println(watch.elapsedTime());
  }
}
