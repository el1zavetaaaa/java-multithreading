package basics.exceptions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionsRealizationTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    private final String RESULT = "caught java.lang.RuntimeException: Something\n";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testThatRuntimeExceptionWasHandled() throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals(RESULT, outputStreamCaptor.toString());
    }

    @Test
    public void testThatRuntimeExceptionWasNotHandledBecauseHandlerWasNotSetUp() throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("", outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
