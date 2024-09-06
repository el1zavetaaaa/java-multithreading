package basics.deadlock;

import java.util.concurrent.TimeUnit;

public class CarA implements Runnable {
    private Bridge leftBridge;
    private Bridge rightBridge;

    public CarA(Bridge leftBridge, Bridge rightBridge) {
        this.leftBridge = leftBridge;
        this.rightBridge = rightBridge;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("CarA enter the LEFT side of the bridge.");
                leftBridge.crossTheBridge();
                System.out.println("CarA enter the RIGHT side of the bridge.");
                rightBridge.crossTheBridge();

                System.out.println("CarA crossing the bridge.");
                TimeUnit.SECONDS.sleep(5);
                rightBridge.moveOutFromTheBridge();
                leftBridge.moveOutFromTheBridge();
                System.out.println("CarA crossed the bridge.");
            }
        } catch (InterruptedException e) {
            System.out.println("CarA exited via interrupted");
        }
    }
}
