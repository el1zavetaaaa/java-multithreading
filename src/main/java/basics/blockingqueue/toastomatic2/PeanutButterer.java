package basics.blockingqueue.toastomatic2;

import basics.blockingqueue.Toast;
import basics.blockingqueue.ToastQueue;

public class PeanutButterer implements Runnable {
    private ToastQueue dryQueue, peanutButteredQueue;

    public PeanutButterer(ToastQueue dry, ToastQueue peanutButtered) {
        dryQueue = dry;
        peanutButteredQueue = peanutButtered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = dryQueue.take();
                t.peanutButter();
                System.out.println(t);
                peanutButteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("PeanutButterer interrupted");
        }
        System.out.println("PeanutButterer off");
    }
}
