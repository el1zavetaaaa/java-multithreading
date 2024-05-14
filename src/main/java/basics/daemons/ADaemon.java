package basics.daemons;

import java.util.concurrent.TimeUnit;

public class ADaemon implements Runnable {

    private static final String STARTING_A_DAEMON = "Starting ADaemon";
    private static final String THIS_SHOULD_ALWAYS_RUN = "This should always run?";

    @Override
    public void run() {
        try {
            System.out.println(STARTING_A_DAEMON);
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupted exception!");
        } finally {
            System.out.println(THIS_SHOULD_ALWAYS_RUN);
        }
    }
}
