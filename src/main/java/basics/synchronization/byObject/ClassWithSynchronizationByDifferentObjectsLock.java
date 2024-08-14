package basics.synchronization.byObject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClassWithSynchronizationByDifferentObjectsLock {

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private final Lock lock3 = new ReentrantLock();

    public void method1() throws InterruptedException {
        lock1.lock();
        System.out.println("method-1");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
        lock1.unlock();
    }

    public void method2() throws InterruptedException {
        lock2.lock();
        System.out.println("method-2");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
        lock2.unlock();
    }

    public void method3() throws InterruptedException {
        lock3.lock();
        System.out.println("method-3");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
        lock3.unlock();
    }
}
