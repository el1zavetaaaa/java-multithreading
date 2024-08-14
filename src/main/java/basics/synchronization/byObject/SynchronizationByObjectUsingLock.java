package basics.synchronization.byObject;

public class SynchronizationByObjectUsingLock {
    public static void main(String[] args) {
        final ClassWithMethodsSynchronizedByOneObjectLock byObjectUsingLock = new ClassWithMethodsSynchronizedByOneObjectLock();
        final ClassWithSynchronizationByDifferentObjectsLock byDifferentObjectsLock = new ClassWithSynchronizationByDifferentObjectsLock();
        new Thread() {
            public void run() {
                try {
                    byObjectUsingLock.method1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    byObjectUsingLock.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    byObjectUsingLock.method3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    byDifferentObjectsLock.method1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    byDifferentObjectsLock.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    byDifferentObjectsLock.method3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();
    }
}
