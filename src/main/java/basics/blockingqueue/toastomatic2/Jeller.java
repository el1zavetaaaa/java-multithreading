package basics.blockingqueue.toastomatic2;

import basics.blockingqueue.Toast;
import basics.blockingqueue.ToastQueue;

public class Jeller implements Runnable {
    private ToastQueue dryQueue, jelleddQueue;

    public Jeller(ToastQueue dry, ToastQueue jelled) {
        dryQueue = dry;
        jelleddQueue = jelled;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.jeller();
                System.out.println(t);
                jelleddQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("PeanutButterer interrupted");
        }
        System.out.println("PeanutButterer off");
    }
}
