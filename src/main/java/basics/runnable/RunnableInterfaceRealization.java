package basics.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableInterfaceRealization {

    public static void main(String[] args) {
        run1Thread();
        runFibonacci();
        runMultipleThreadRealizationOfExecutor(5);
    }

    private static void run1Thread() {
        LiftOff launch = new LiftOff();
        launch.run();
    }

    private static void runFibonacci() {
        FibonacciRunnable fibonacciConcurrent = new FibonacciRunnable(10);
        fibonacciConcurrent.run();

    }

    private static void runMultipleThreadRealizationOfExecutor(final int quantity) {
        ExecutorService exec = Executors.newFixedThreadPool(6);
        for (int i = 0; i < quantity; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
