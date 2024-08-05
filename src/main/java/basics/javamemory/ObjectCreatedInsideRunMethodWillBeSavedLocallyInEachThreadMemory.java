package basics.javamemory;

public class ObjectCreatedInsideRunMethodWillBeSavedLocallyInEachThreadMemory implements Runnable {
    @Override
    public void run() {
        // this variable will be different for each thread
        MyObject myObject = new MyObject();
        System.out.println(myObject);
    }
}
