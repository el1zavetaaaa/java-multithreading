package basics.runnable;

import basics.utility.Fibonacci;

import java.util.ArrayList;

public class FibonacciRunnable implements Runnable{
    private int quantity;
    private ArrayList<Integer> fibonacciSequence = new ArrayList<>();

    public FibonacciRunnable(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Integer> getFibonacciSequence(){
        return fibonacciSequence;
    }

    public void clean (){
        fibonacciSequence = new ArrayList<>();
    }
    @Override
    public void run() {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i < quantity; i++) {
            Integer current = gen.next();
            System.out.print(current + " ");
            fibonacciSequence.add(current);
        }
    }
}
