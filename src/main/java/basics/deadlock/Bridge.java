package basics.deadlock;

public class Bridge {
    private boolean crossingTheBridge = false;

    public synchronized void crossTheBridge() throws InterruptedException {
        while (crossingTheBridge)
            wait();
        crossingTheBridge = true;
    }

    public synchronized void moveOutFromTheBridge() {
        crossingTheBridge = false;
        notifyAll();
    }
}
