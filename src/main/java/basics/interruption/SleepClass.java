package basics.interruption;

import java.util.concurrent.TimeUnit;

public class SleepClass {
    public void sleepForLongTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);    }
}
