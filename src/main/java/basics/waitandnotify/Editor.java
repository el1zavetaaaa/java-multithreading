package basics.waitandnotify;

public class Editor implements Runnable {
    @Override
    public void run() {
        System.out.println("Editor waiting for writer to provide an article");
        try {
            synchronized (this) {
                this.wait();
            }
            System.out.println("Editor start working on an article");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
