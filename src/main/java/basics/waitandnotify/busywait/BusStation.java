package basics.waitandnotify.busywait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BusStation {
    //2nd realization of busy wait
//    private static boolean busArrived = false;

    public static void main(String[] args) throws InterruptedException {
        Bus bus = new Bus();
        ExecutorService exec = Executors.newCachedThreadPool();

        // Realization of wait()/notifyAll()
        exec.execute(new PersonWaitedForBus(bus));
        exec.execute(new ArrivedBus(bus));

        //Realization of a busy wait
//        exec.execute(new ArrivedBus(bus));
//        exec.execute(new PersonWaitedForBus(bus));


        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }}


        //2nd realization of busy wait
//            // Task 1: Simulates the bus arrival
//            Runnable arrivedBus = () -> {
//                try {
//                    TimeUnit.SECONDS.sleep(5); // Simulate bus taking time to arrive
//                    busArrived = true;
//                    // Bus has arrived
//                    System.out.println("Bus arrived!");
//                } catch (InterruptedException e) {
//                    System.out.println("Bus arrival interrupted");
//                }
//            };
//
//            // Task 2: Simulates the person waiting for the bus
//            Runnable personWaiting = () -> {
//                while (!busArrived) {           // Busy wait
//                    System.out.println("Waiting for the bus...");
//                }
//                System.out.println("Caught the bus!");
//                busArrived = false;              // Reset flag
//            };
//
//            // Start both tasks
//            new Thread(arrivedBus).start();
//            new Thread(personWaiting).start();
//        }}
