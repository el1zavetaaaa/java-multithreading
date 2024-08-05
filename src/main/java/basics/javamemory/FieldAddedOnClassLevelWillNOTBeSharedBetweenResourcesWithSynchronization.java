package basics.javamemory;

public class FieldAddedOnClassLevelWillNOTBeSharedBetweenResourcesWithSynchronization implements Runnable{
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            synchronized (this) {
                this.count++;
            }
        }

        System.out.println(Thread.currentThread().getName() + " - " + count);
    }
}
