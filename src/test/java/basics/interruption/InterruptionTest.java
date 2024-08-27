package basics.interruption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class InterruptionTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testInterruptSleepRunnableClass() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SleepRunnable sleepRunnable = new SleepRunnable();

        Future<?> f = executorService.submit(sleepRunnable);

        System.out.println("Interrupting " + sleepRunnable.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt Sent to " + sleepRunnable.getClass().getName());

        TimeUnit.MILLISECONDS.sleep(500);

        assertThat(outputStreamCaptor.toString())
                .contains("Interrupting basics.interruption.SleepRunnable\n",
                        "InterruptedException\n",
                        "Exiting basics.interruption.SleepRunnable\n",
                        "Interrupt Sent to basics.interruption.SleepRunnable\n");
    }
}
