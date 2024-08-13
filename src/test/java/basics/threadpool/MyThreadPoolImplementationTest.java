package basics.threadpool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyThreadPoolImplementationTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testSingleThreadRealization() throws InterruptedException {
        MyThreadPool singleThread = new MyThreadPool(1, 1);
        try {
            singleThread.execute(() -> {
                String message = "Task was successfully executed";
                System.out.println(message);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("Task was successfully executed\n", outputStreamCaptor.toString());
    }

    @Test
    public void testSeveralTaskSuccessfullyExecuted() throws Exception {
        MyThreadPool myThreadPool = new MyThreadPool(1, 2);
        myThreadPool.execute(() -> {
            String message = "Task was successfully executed - 1";
            System.out.println(message);
        });

        myThreadPool.execute(() -> {
            String message = "Task was successfully executed - 2";
            System.out.println(message);
        });

        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("Task was successfully executed - 1\n" +
                "Task was successfully executed - 2\n", outputStreamCaptor.toString());
    }

    @Test
    public void assertThatThreadsWillBeReusedForTasks() throws Exception {
        MyThreadPool myThreadPool = new MyThreadPool(3, 4);

        for (int i = 0; i < 4; i++) {
            int taskNo = i;
            myThreadPool.execute(() -> {
                String message =
                        Thread.currentThread().getName()
                                + ": Task " + taskNo;
                System.out.println(message);
            });
        }

        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString()).contains("MyThread - 1", "MyThread - 2", "MyThread - 3");
        assertThat(outputStreamCaptor.toString()).doesNotContain("MyThread - 4");

    }
}
