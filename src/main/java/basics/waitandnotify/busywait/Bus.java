package basics.waitandnotify.busywait;

import java.util.concurrent.TimeUnit;

public class Bus {
    private boolean isArrived;

    //Commented code is implementation of busy wait where flag isArrived become true
//        public void busArrived() throws InterruptedException {
//            TimeUnit.MILLISECONDS.sleep(500);
//            isArrived = true;
//
//        }

    // Realization of wait()/notifyAll()
    public synchronized void busArrived() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        isArrived = true;
        this.notifyAll();
    }

    // Commented code is implementation of busy wait, when the processor did not release the object while waiting for the flag isArrived to become true
//        public void waitingForBus() throws InterruptedException {
//            while (!isArrived){
//                System.out.println("Waiting for bus: " + isArrived);
//            }
    //The actions, that will happen after the flag become true
//            System.out.println("Bus arrived: " + isArrived);
//            TimeUnit.MILLISECONDS.sleep(500);
//            isArrived = false;
//            System.out.println("Miss the bus: " + isArrived);
//        }

    //Realization of an optimization of busy wait -> using instead wait() & notifyAll(). When you'll run 2 programs you'll see the difference, that
    // in the case of wait() release the object till the time the condition changes.
    public synchronized void waitingForBus() throws InterruptedException {
        while (!isArrived) {
            System.out.println("Waiting for bus: " + isArrived);
            this.wait();
        }
        System.out.println("Bus arrived: " + isArrived);
        TimeUnit.MILLISECONDS.sleep(500);
        isArrived = false;
        System.out.println("Miss the bus: " + isArrived);
    }
}
