package basics.lockandcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitPerson implements Runnable {
    protected Lock lock = new ReentrantLock();
    protected Condition condition = lock.newCondition();
    private Restaurant restaurant;

    public WaitPerson(Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //case with Lock & Condition
                lock.lock();
                try {
                    while (restaurant.meal == null)
                        condition.await(); // ... for the chef to produce a meal
                } finally {
                    lock.unlock();
                }
                // case with wait() & notifyAll()
//                synchronized(this) {
//                    while(restaurant.meal == null)
//                        wait(); // ... for the chef to produce a meal
//                }
                System.out.println("Waitperson got " + restaurant.meal);
                //case with Lock & Condition
                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }
                // case with wait() & notifyAll()
//                synchronized(restaurant.chef) {
//                    restaurant.meal = null;
//                    restaurant.chef.notifyAll(); // Ready for another
//                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}
