package basics.daemons;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaemonsRealizationTest {

    @Test
    public void testDaemonsFactoryIsUsing() throws InterruptedException {
        DaemonsThreadFactory threadFactory = new DaemonsThreadFactory();
        ExecutorService exec = Executors.newCachedThreadPool(threadFactory);
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePrioritiesDaemonRealization(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePrioritiesDaemonRealization(Thread.MAX_PRIORITY));
        assertEquals("Daemons Factory is using", threadFactory.testDaemonsFactoryIsUsing());
        TimeUnit.MILLISECONDS.sleep(500);
        exec.shutdown();
    }
}
