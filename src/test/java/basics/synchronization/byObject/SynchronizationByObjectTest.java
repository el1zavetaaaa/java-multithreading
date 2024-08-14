package basics.synchronization.byObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SynchronizationByObjectTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testThatSynchronizationByOneObjectBlockOtherMethodsUntilTCurrentOneIsFinished() throws InterruptedException {
        final ClassWithMethodsSynchronizedByOneObject byOneObject = new ClassWithMethodsSynchronizedByOneObject();

        new Thread() {
            public void run() {
                try {
                    byOneObject.method1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString()).isEqualTo("method-1\n");

        new Thread() {
            public void run() {
                try {
                    byOneObject.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString()).isEqualTo("method-1\nmethod-2\n");

        new Thread() {
            public void run() {
                try {
                    byOneObject.method3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString()).isEqualTo("method-1\nmethod-2\nmethod-3\n");
    }

    @Test
    public void testThatSynchronizationByDifferentObjectsDoesNotBlockOtherMethodsUntilCurretnOneIsFinished() throws InterruptedException {
        final ClassWithSynchronizationByDifferentObjectsLock byDifferentObjects = new ClassWithSynchronizationByDifferentObjectsLock();

        new Thread() {
            public void run() {
                try {
                    byDifferentObjects.method1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    byDifferentObjects.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();


        new Thread() {
            public void run() {
                try {
                    byDifferentObjects.method3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        assertThat(outputStreamCaptor.toString()).contains("method-1", "method-2");
        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString()).contains("method-1", "method-2", "method-3");
    }
}
