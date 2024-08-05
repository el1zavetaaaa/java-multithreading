package basics.javamemory;

public class FieldAddedOnClassLevelWillBeSharedBetweenResourcesWithoutSynchronization implements Runnable {
    // this variable will be shared between different threads
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            this.count++;
        }

        System.out.println(Thread.currentThread().getName() + " - " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        // In this case we will come across race condition, where 2 resources will share 1 variable
        // They will modify it simultaneously, their answers won't be synchronized and the wrong result will be added to the variable count
        ObjectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory objectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory = new ObjectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory();

        Thread thread1 = new Thread(objectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory);
        Thread thread2 = new Thread(objectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory);

        // For example in this case we will get number 1_000_562
        thread1.start();
        // Here, however, we will get something like 1_000_723
        thread2.start();

        // 2_000_000 should be equals to count, in case, 2 threads modify the variable in synchronized manner
    }
}
