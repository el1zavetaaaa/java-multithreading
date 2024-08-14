package basics.synchronization.byObject;

import java.util.concurrent.TimeUnit;

public class ClassWithSynchronizationByDifferentObjects {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final Object lock3 = new Object();

    public void method1() throws InterruptedException {
        synchronized (lock1) {
            System.out.println("method-1");
            TimeUnit.MILLISECONDS.sleep(500);
            Thread.yield();
        }
    }

    public void method2() throws InterruptedException {
        synchronized (lock2) {
            System.out.println("method-2");
            TimeUnit.MILLISECONDS.sleep(500);
            Thread.yield();
        }
    }

    public void method3() throws InterruptedException {
        synchronized (lock3) {
            System.out.println("method-3");
            TimeUnit.MILLISECONDS.sleep(500);
            Thread.yield();
        }
    }
}
