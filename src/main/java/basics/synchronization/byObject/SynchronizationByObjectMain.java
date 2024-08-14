package basics.synchronization.byObject;

public class SynchronizationByObjectMain {

    public static void main(String[] args) {
        final ClassWithMethodsSynchronizedByOneObject synchronizedByOneObject = new ClassWithMethodsSynchronizedByOneObject();
        final ClassWithSynchronizationByDifferentObjects synchronizationByDifferentObjects = new ClassWithSynchronizationByDifferentObjects();
        new Thread() {
            public void run() {
                try {
                    synchronizedByOneObject.method1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    synchronizedByOneObject.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    synchronizedByOneObject.method3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    synchronizationByDifferentObjects.method1();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    synchronizationByDifferentObjects.method2();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();

        new Thread() {
            public void run() {
                try {
                    synchronizationByDifferentObjects.method3();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }.start();
    }
}
