package basics.javamemory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class JavaMemoryTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void objectCreatedInsideRunMethodWillDifferForDifferentThreads() throws InterruptedException {
        ObjectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory runnable = new ObjectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory();

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        TimeUnit.MILLISECONDS.sleep(500);
        final String myObject1 = outputStreamCaptor.toString();

        thread2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        final String myObject2 = outputStreamCaptor.toString();

        assertNotEquals(myObject1, myObject2);
    }

    @Test
    public void inCaseOfCreating2SeparateRunnablesAndPassThemTo2DifferentThreadsTheyWillNotBeShared() throws InterruptedException {
        FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization runnable1 = new FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization();
        FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization runnable2 = new FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization();

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        TimeUnit.MILLISECONDS.sleep(500);
        final int count1 = runnable1.getCount();

        thread2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        final int count2 = runnable2.getCount();

        assertEquals(count1, count2);
        assertEquals(1_000_000, count1);
        assertEquals(1_000_000, count2);
    }

    @Test
    public void ifWeShare1RunnableBetween2ThreadsItWillBeModifiedIncorrectly() throws InterruptedException {
        // In this case we will come across race condition, where 2 resources will share 1 variable
        // They will modify it simultaneously, their answers won't be synchronized and the wrong result will be added to the variable count
        FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization runnable = new FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization();

        Thread thread1 = new Thread(runnable, "thread-1");
        Thread thread2 = new Thread(runnable, "thread-2");

        thread1.start();
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        final String result = outputStreamCaptor.toString();

        Pattern pattern = Pattern.compile(" - (\\d+)");
        Matcher matcher = pattern.matcher(result);

        // Extract the first count for the thread-1
        // For example in this case we will get number 1_000_562
        matcher.find();
        int count1 = Integer.parseInt(matcher.group(1));

        // Extract the second count for the thread-2
        // Here, however, we will get something like 1_000_723
        matcher.find();
        int count2 = Integer.parseInt(matcher.group(1));

        assertNotEquals(count1, count2);
        // 2_000_000 should be equals to count, in case, 2 threads modify the variable in synchronized manner
        // However, we do not synchronize access to the resource here, so we expect that it will be modified incorrectly
        assertNotEquals(count1, 2_000_000);
        assertNotEquals(count2, 2_000_000);
    }

    @Test
    public void ifWeShare1SynchronizedRunnableBetween2ThreadsItWillBeModifiedCorrectly() throws InterruptedException {
        // In this case, synchronization was added around the incrementation of shared variable count.
        // This is mean, that while one of 2 threads will be working with runnable, another thread will not get access to its variables.
        FieldAddedOnClassLevelWillNOTBeSharedBetweenResourcesWithSynchronization runnable = new FieldAddedOnClassLevelWillNOTBeSharedBetweenResourcesWithSynchronization();
        Thread thread1 = new Thread(runnable, "thread-1");
        Thread thread2 = new Thread(runnable, "thread-2");

        thread1.start();
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        final String result = outputStreamCaptor.toString();

        Pattern pattern = Pattern.compile(" - (\\d+)");
        Matcher matcher = pattern.matcher(result);

        // Extract the first count for the thread-1
        // As a result, in one of the case we will get the correct answer 2_000_000. It could be here
        matcher.find();
        int count1 = Integer.parseInt(matcher.group(1));

        // Extract the second count for the thread-2
        // Or here.
        matcher.find();
        int count2 = Integer.parseInt(matcher.group(1));

        assertTrue(count1 == 2_000_000 || count2 == 2_000_000);
    }
}
