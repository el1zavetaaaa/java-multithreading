package basics.blockingqueue;

import basics.blockingqueue.toastomatic.Butterer;
import basics.blockingqueue.toastomatic.Eater;
import basics.blockingqueue.toastomatic.Jammer;
import basics.blockingqueue.toastomatic2.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class BlockingQueueTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    // it is expected that 1. It will make a Toast
    //                     2. It will butter it.
    //                     3. It will jammed eat.
    //                     4. Eater will eat the toast
    @Test
    public void testThatToastOMaticWorksCorrect() throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(butteredQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();

        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString())
                .contains("Toast 0: DRY\n" +
                        "Toast 0: BUTTERED\n" +
                        "Toast 0: JAMMED\n" +
                        "Chomp! Toast 0: JAMMED");
    }

    // it is expected that 1. It will make a Toast
    //                     Then in PARALLEL:
    //                     2. It will peanut butter it. OR It will put jelly on it
    //                     3. It will compound a sandwich from 2 toasts ( 1 - with peanut butter AND 2 - with jelly)
    //                     4. Eater will eat the sandwich

    @Test
    public void testThatToastOMatic2WorksCorrectly() throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                jelledQueue = new ToastQueue(),
                peanutButterQueue = new ToastQueue();
        SandwichQueue sandwichQueue = new SandwichQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new PeanutButterer(dryQueue, peanutButterQueue));
        exec.execute(new Jeller(dryQueue, jelledQueue));
        exec.execute(new SandwichMaker(peanutButterQueue, jelledQueue, sandwichQueue));
        exec.execute(new Eater2(sandwichQueue));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();

        TimeUnit.MILLISECONDS.sleep(500);
        assertThat(outputStreamCaptor.toString())
                .contains("Toast 0: DRY\n" +
                        "Toast 0: PEANUT_BUTTERED\n" +
                        "Toast 1: DRY\n" +
                        "Toast 1: JELLED\n" +
                        "Sandwich{top=Toast 0: PEANUT_BUTTERED, bottom=Toast 1: JELLED, id=0}\n" +
                        "Chomp! Sandwich{top=Toast 0: PEANUT_BUTTERED, bottom=Toast 1: JELLED, id=0}");
    }
}
