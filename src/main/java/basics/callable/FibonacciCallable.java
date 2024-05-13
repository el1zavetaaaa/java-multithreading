package basics.callable;

import basics.utility.Fibonacci;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class FibonacciCallable implements Callable<ArrayList<Integer>> {
    private int quantity;
    private ArrayList<Integer> fibonacciSequence = new ArrayList<>();

    public FibonacciCallable(int quantity) {
        this.quantity = quantity;
    }

    public void clean() {
        fibonacciSequence = new ArrayList<>();
    }

    @Override
    public ArrayList<Integer> call() throws Exception {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i < quantity; i++) {
            Integer current = gen.next();
            fibonacciSequence.add(current);
        }
        return fibonacciSequence;
    }
}
