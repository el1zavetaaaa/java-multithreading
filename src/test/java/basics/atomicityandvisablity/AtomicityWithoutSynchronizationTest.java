package basics.atomicityandvisablity;

import basics.atomicityandvisability.AtomicityWithSynchronization;
import basics.atomicityandvisability.AtomicityWithoutSynchronization;
import basics.atomicityandvisability.SerialNumberChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AtomicityWithoutSynchronizationTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // Even though return is atomic, without synchronization it'll allow to read inconsistent value
    // So, method AtomicityWithoutSynchronization#getValue() will break the code
    @Test
    public void testThatAtomicityWithoutSynchronizationCanBreakACode() {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityWithoutSynchronization at = new AtomicityWithoutSynchronization();
        exec.execute(at);

        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                assertTrue(outputStreamCaptor.toString().contains(String.valueOf(val)));
                System.exit(0);
            }
        }
    }

    // Do not run the test, it will overflow your stack :)
    // After synchronization it impossible to get inconsistent value
    @Test
    public void testThatAtomicityWithSynchronizationWillNotBreakACode() {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityWithSynchronization at = new AtomicityWithSynchronization();
        exec.execute(at);

        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                assertFalse(outputStreamCaptor.toString().contains(String.valueOf(val)));
                System.exit(0);
            }
        }
    }
}
