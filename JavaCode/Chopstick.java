public class Chopstick {
	private int ID;
	private boolean free;
	private final int timeWait_max = 2000;


	Chopstick(int ID) {
    this.ID = ID;
    this.free = true;
	}
	
	
	synchronized boolean take() {
	long deadline = System.currentTimeMillis() + timeWait_max;
    while (!free) {
      long remaining = deadline - System.currentTimeMillis();
      if (remaining <= 0) {
          System.out.println("Chopstick " + ID + " timed out.");
          return false;
      }
      try {
        System.out.println("Chopstick " + ID + " is in use. Waiting.");
        wait(remaining);
      } 
      catch (InterruptedException e) {
        System.out.println(e);
        break;
      }
    }

    if (!free) {
      throw new IllegalStateException();
    }

    this.free = false;
    return true;
	}
	
	synchronized void release() {
    if (free) {
      throw new IllegalStateException();
    }

    this.free = true;
    notify();
	}
	    
	public int getID() {
	  return(ID);
	}
}
