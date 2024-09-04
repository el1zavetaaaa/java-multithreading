package basics.blockingqueue.toastomatic2;

import basics.blockingqueue.Toast;
import basics.blockingqueue.ToastQueue;

public class SandwichMaker implements Runnable {
    private ToastQueue peanutButteredQueue;
    private ToastQueue jelledQueue;
    private SandwichQueue sandwichesQueue;

    private int count = 0;

    public SandwichMaker(ToastQueue peanutButteredQueue, ToastQueue jelledQueue, SandwichQueue sandwichesQueue) {
        this.peanutButteredQueue = peanutButteredQueue;
        this.jelledQueue = jelledQueue;
        this.sandwichesQueue = sandwichesQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Sandwich s = new Sandwich(peanutButteredQueue.take(),
                        jelledQueue.take(), count++);
                System.out.println(s);
                sandwichesQueue.put(s);
            }
        } catch (InterruptedException e) {
            System.out.println("SandwichMaker interrupted");
        }
        System.out.println("SandwichMaker off");
    }
}
