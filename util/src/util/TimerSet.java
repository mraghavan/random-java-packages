/* TimerSet.java */

package util;

public class TimerSet {
  
  private Stopwatch[] watches;

  public TimerSet(int numWatches) {
    watches = new Stopwatch[numWatches];
    for(int i = 0; i < watches.length; i++) {
      watches[i] = new Stopwatch();
    }
  }

  public int numWatches() {
    return watches.length;
  }
  
  public void start(int watch) {
    try {
      watches[watch].start();
    } catch(IndexOutOfBoundsException e) {
      return;
    }
  }

  public void stop(int watch) {
    try {
      watches[watch].stop();
    } catch(IndexOutOfBoundsException e) {
      return;
    }
  }

  public void reset(int watch) {
    try {
      watches[watch].reset();
    } catch(IndexOutOfBoundsException e) {
      return;
    }
  }

  public double elapsedTime(int watch) {
    try {
      return watches[watch].elapsedTime();
    } catch(IndexOutOfBoundsException e) {
      return -1;
    }
  }

  public boolean isRunning(int watch) {
    try {
      return watches[watch].isRunning();
    } catch(IndexOutOfBoundsException e) {
      return false;
    }
  }

  public void startAll() {
    for(int i = 0; i < watches.length; i++) {
      watches[i].start();
    }
  }

  public void stopAll() {
    for(int i = 0; i < watches.length; i++) {
      watches[i].stop();
    }
  }

  
  public void resetAll() {
    for(int i = 0; i < watches.length; i++) {
      watches[i].reset();
    }
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for(int i = 0; i < watches.length; i++) {
      Stopwatch watch = watches[i];
      str.append("Timer ");
      str.append(i);
      str.append(":");
      str.append(" elapsed time: ");
      str.append(watch.elapsedTime());
      if(watch.isRunning()) {
        str.append(" (running)\n");
      } else {
        str.append(" (stopped)\n");
      }
    }
    return str.toString();
  }

  public static void main(String[] args) {
    TimerSet timers = new TimerSet(5);
    timers.startAll();
    try {
      Thread.sleep(500);
    } catch(InterruptedException e) {
    }
    System.out.println(timers);
    timers.stopAll();
    System.out.println(timers);
    timers.reset(1);
    try {
      Thread.sleep(600);
    } catch(InterruptedException e) {
    }
    timers.start(1);
    System.out.println(timers);
  }
}
