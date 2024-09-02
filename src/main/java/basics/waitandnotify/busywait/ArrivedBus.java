package basics.waitandnotify.busywait;

public class ArrivedBus implements Runnable {
    private Bus bus;

    public ArrivedBus(final Bus bus) {
        this.bus = bus;
    }

    @Override
    public void run() {
        try {
            bus.busArrived();
        } catch (InterruptedException e) {
            System.out.println("Exiting from ArrivedBus via interrupting");
        }
    }
}
