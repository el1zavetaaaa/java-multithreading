package basics.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RoadWay {
    public static void main(String[] args) throws InterruptedException {
        Bridge leftBridge = new Bridge();
        Bridge rightBridge = new Bridge();

        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new CarA(leftBridge, rightBridge));
        exec.execute(new CarB(leftBridge, rightBridge));

        TimeUnit.SECONDS.sleep(15);

        exec.shutdownNow();
    }
}
