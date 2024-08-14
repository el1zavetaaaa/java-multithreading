package basics.synchronization.byObject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClassWithMethodsSynchronizedByOneObjectLock {

    private Lock lock = new ReentrantLock();

    public void method1() throws InterruptedException {
        lock.lock();
        System.out.println("method-1");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
        lock.unlock();
    }

    public void method2() throws InterruptedException {
        lock.lock();
        System.out.println("method-2");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
        lock.unlock();
    }

    public void method3() throws InterruptedException {
        lock.lock();
        System.out.println("method-3");
        TimeUnit.MILLISECONDS.sleep(500);
        Thread.yield();
        lock.unlock();
    }
}
