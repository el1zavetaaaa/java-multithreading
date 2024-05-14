package basics.callable;

import basics.utility.Fibonacci;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableMethod {
    private Callable<ArrayList<Integer>> callable;
    private ArrayList<Integer> fibonacciSequence = new ArrayList<>();

    public Future<ArrayList<Integer>> callTask(final int quantity) {
        if (callable == null) {

            callable = () -> {
                Fibonacci gen = new Fibonacci();
                for (int i = 0; i < quantity; i++) {
                    Integer current = gen.next();
                    fibonacciSequence.add(current);
                }
                return fibonacciSequence;
            };
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        return exec.submit(callable);
    }
}
