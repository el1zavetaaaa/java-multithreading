package basics.synchronization;

public class CharacterChecker implements Runnable {
    private CharacterAppender appender;

    public CharacterChecker(CharacterAppender appender) {
        this.appender = appender;
    }

    private int length = 0;

    @Override
    public void run() {
        while (!appender.isCanceled()) {
            StringBuilder string = appender.appendNewChars();
            length += 2;
            if (string.length() % 2 != 0) {
                System.out.println(string.length() + " not even");
                appender.cancel();
            }
        }
    }
}
