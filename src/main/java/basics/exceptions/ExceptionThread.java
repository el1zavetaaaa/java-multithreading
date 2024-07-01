package basics.exceptions;

public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException("Something");
    }
}
