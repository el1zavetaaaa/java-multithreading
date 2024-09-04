package basics.blockingqueue.toastomatic2;

import basics.blockingqueue.ToastQueue;
import basics.blockingqueue.Toaster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ToastOMatic2 {
    public static void main(String[] args) throws Exception {
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
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
