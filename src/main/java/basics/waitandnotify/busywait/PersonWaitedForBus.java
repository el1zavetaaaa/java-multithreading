package basics.waitandnotify.busywait;

public class PersonWaitedForBus implements Runnable {
    private Bus bus;

    public PersonWaitedForBus(final Bus bus) {
        this.bus = bus;
    }

    @Override
    public void run() {
        try {
            bus.waitingForBus();
        } catch (InterruptedException e) {
            System.out.println("Exiting from PersonWaitedForBus via interrupting");
        }
    }
}
