package basics.interruption;

public class SleepRunnable implements Runnable{
    SleepClass sleepClass = new SleepClass();

    @Override
    public void run() {
        try {
            sleepClass.sleepForLongTime();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting " + this.getClass().getName());
    }
}
