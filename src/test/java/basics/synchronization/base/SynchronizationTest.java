package basics.synchronization.base;

import basics.synchronization.base.CharacterAppender;
import basics.synchronization.base.CharacterAppenderSynchronized;
import basics.synchronization.base.CharacterChecker;
import basics.synchronization.base.CharacterCheckerSynchronized;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SynchronizationTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testCharacterAppenderWillBeCanceledBecauseOfInvalidOperationWithoutSynchronization() throws InterruptedException {

        CharacterAppender characterAppender = new CharacterAppender(new StringBuilder());
        CharacterChecker characterChecker = new CharacterChecker(characterAppender);

        Thread thread1 = new Thread(characterChecker);
        Thread thread2 = new Thread(characterChecker);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertTrue(characterAppender.isCanceled());
        assertTrue(outputStreamCaptor.toString().contains("not even"));
    }

    @Test
    public void testCharacterAppenderWillBeCanceledBecauseOfInvalidOperationWithSynchronization() throws InterruptedException {

        CharacterAppenderSynchronized characterAppender = new CharacterAppenderSynchronized(new StringBuilder());
        CharacterCheckerSynchronized characterChecker = new CharacterCheckerSynchronized(characterAppender);

        Thread thread1 = new Thread(characterChecker);
        Thread thread2 = new Thread(characterChecker);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertTrue(characterAppender.isCanceled());
        assertFalse(outputStreamCaptor.toString().contains("not even"));
    }
}
