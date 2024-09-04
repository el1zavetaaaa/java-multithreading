package basics.blockingqueue.toastomatic2;

import basics.blockingqueue.Toast;
import basics.blockingqueue.ToastQueue;

public class Eater2 implements Runnable {
    private SandwichQueue sandwichQueue;
    private int counter = 0;

    public Eater2(SandwichQueue sandwiches) {
        sandwichQueue = sandwiches;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Sandwich s = sandwichQueue.take();
                // Verify that the toast is coming in order,
                // and that all pieces are getting jammed:
                if (s.getId() != counter++ ||
                        s.getTop().getStatus() != Toast.Status.PEANUT_BUTTERED &&
                                s.getBottom().getStatus() != Toast.Status.JELLED) {
                    System.out.println(">>>> Error: " + s);
                    System.exit(1);
                } else
                    System.out.println("Chomp! " + s);
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }

}
