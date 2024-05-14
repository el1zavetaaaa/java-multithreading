package basics.daemons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonsRealization {
    public static void main(String[] args) throws InterruptedException {
        testFinallyBlockForDaemonThreads(5);
        testDaemonFactory();
    }

    private static void testFinallyBlockForDaemonThreads(final int quantity) {
        for (int i = 0; i < quantity; i++) {
            Thread tr = new Thread(new ADaemon());
            tr.setDaemon(true);
            tr.start();
        }
    }

    private static void testDaemonFactory() throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonsThreadFactory());
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePrioritiesDaemonRealization(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePrioritiesDaemonRealization(Thread.MAX_PRIORITY));
        TimeUnit.MILLISECONDS.sleep(500);
        exec.shutdown();
    }
}
