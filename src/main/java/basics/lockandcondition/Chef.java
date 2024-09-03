package basics.lockandcondition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chef implements Runnable{
    protected Lock lock = new ReentrantLock();
    protected Condition condition = lock.newCondition();
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                //case with Lock & Condition
                lock.lock();
                try {
                    while(restaurant.meal != null)
                        condition.await(); // ... for the meal to be taken
                } finally {
                    lock.unlock();
                }
                // case with wait() & notifyAll()
//                synchronized(this) {
//                    while(restaurant.meal != null)
//                        wait(); // ... for the meal to be taken
//                }
                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.print("Order up! ");
                //case with Lock & Condition
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                // case with wait() & notifyAll()
//                synchronized(restaurant.waitPerson) {
//                    restaurant.meal = new Meal(count);
//                    restaurant.waitPerson.notifyAll();
//                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
