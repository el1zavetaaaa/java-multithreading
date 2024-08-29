package basics.waitandnotify;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Journal {
    public static void main(String[] args) throws InterruptedException {
        Editor editor = new Editor();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(editor);
        executor.execute(new Writer(editor));
        TimeUnit.MILLISECONDS.sleep(1000);

        executor.shutdownNow();
    }
}
