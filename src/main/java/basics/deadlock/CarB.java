package basics.deadlock;

import java.util.concurrent.TimeUnit;

public class CarB implements Runnable {
    private Bridge leftBridge;
    private Bridge rightBridge;

    public CarB(Bridge leftBridge, Bridge rightBridge) {
        this.leftBridge = leftBridge;
        this.rightBridge = rightBridge;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //deadlock doesn't exist
                System.out.println("CarB enter the LEFT side of the bridge.");
                leftBridge.crossTheBridge();
                System.out.println("CarB enter the RIGHT side of the bridge.");
                rightBridge.crossTheBridge();
                //deadlock exist
//                System.out.println("CarB enter the LEFT side of the bridge.");
//                leftBridge.crossTheBridge();

                System.out.println("CarB crossing the bridge.");
                TimeUnit.SECONDS.sleep(5);

                // deadlock doesn't exist
                rightBridge.moveOutFromTheBridge();
                leftBridge.moveOutFromTheBridge();
                // deadlock exist
//                rightBridge.moveOutFromTheBridge();

                System.out.println("CarB crossed the bridge.");
            }
        } catch (InterruptedException e) {
            System.out.println("CarB exited via interrupted");
        }
    }
}
