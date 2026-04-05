public class Chopstick {
	private int ID;
	private boolean free;

	Chopstick(int ID) {
    this.ID = ID;
    this.free = true;
	}
	
	synchronized void take() {
    while (!free) {
      try {
        System.out.println("Chopstick " + ID + " is in use. Waiting.");
        wait();
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
