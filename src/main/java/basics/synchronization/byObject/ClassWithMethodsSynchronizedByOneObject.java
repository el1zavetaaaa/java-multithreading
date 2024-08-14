package basics.synchronization.byObject;

import java.util.concurrent.TimeUnit;

public class ClassWithMethodsSynchronizedByOneObject {
    public synchronized void method1() throws InterruptedException {
        System.out.println("method-1");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
    }

    public synchronized void method2() throws InterruptedException {
        System.out.println("method-2");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
    }

    public synchronized void method3() throws InterruptedException {
        System.out.println("method-3");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
    }
}
