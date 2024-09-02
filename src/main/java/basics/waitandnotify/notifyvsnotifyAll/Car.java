package basics.waitandnotify.notifyvsnotifyAll;

public class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        //  as methods are synchronized by the same object,
        //  it will notify the correct task in both notify() | notifyAll() methods
        notify();
        //        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notify();
        //        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}
