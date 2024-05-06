package basics;

public class RunnableInterfaceRealization {

    public static void main(String[] args) {
        run1Thread();
        runFibonacci();
    }

    private static void run1Thread() {
        LiftOff launch = new LiftOff();
        launch.run();
    }

    private static void runFibonacci() {
        FibonacciConcurrent fibonacciConcurrent = new FibonacciConcurrent(10);
        fibonacciConcurrent.run();

    }
}
