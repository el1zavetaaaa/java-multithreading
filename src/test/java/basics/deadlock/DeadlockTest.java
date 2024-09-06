package basics.deadlock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class DeadlockTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // check code comments to configure scenario, when deadlock exists and when deadlock doesn't exist
    @Test
    public void testThatDeadlockExists() throws InterruptedException {
        Bridge leftBridge = new Bridge();
        Bridge rightBridge = new Bridge();

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new CarA(leftBridge, rightBridge));
        exec.execute(new CarB(leftBridge, rightBridge));

        TimeUnit.SECONDS.sleep(15);

        exec.shutdownNow();

        TimeUnit.MILLISECONDS.sleep(500);

        assertThat(outputStreamCaptor.toString())
                .doesNotContain("CarB crossed the bridge.")
                .contains("CarA enter the LEFT side of the bridge.\n",
                        "CarA enter the RIGHT side of the bridge.\n",
                        "CarB enter the RIGHT side of the bridge.\n",
                        "CarB enter the LEFT side of the bridge.\n",
                        "CarA exited via interrupted\n",
                        "CarB exited via interrupted\n");
    }

    // check code comments to configure scenario, when deadlock exists and when deadlock doesn't exist
    @Test
    public void testDeadlockDoesNotExist() throws InterruptedException {
        Bridge leftBridge = new Bridge();
        Bridge rightBridge = new Bridge();

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new CarA(leftBridge, rightBridge));
        exec.execute(new CarB(leftBridge, rightBridge));

        TimeUnit.SECONDS.sleep(15);

        exec.shutdownNow();

        TimeUnit.MILLISECONDS.sleep(500);

        assertThat(outputStreamCaptor.toString())
                .contains("CarB crossed the bridge.")
                .contains("CarA enter the LEFT side of the bridge.\n",
                        "CarA enter the RIGHT side of the bridge.\n",
                        "CarB enter the RIGHT side of the bridge.\n",
                        "CarB enter the LEFT side of the bridge.\n",
                        "CarA exited via interrupted\n",
                        "CarB exited via interrupted\n");
    }
}
