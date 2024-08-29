package basics.waitandnotify;

import java.util.concurrent.TimeUnit;

public class Writer implements Runnable{
    private final Editor editor;

    public Writer(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("An article is ready!");
            synchronized (editor) {
                this.editor.notify();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
