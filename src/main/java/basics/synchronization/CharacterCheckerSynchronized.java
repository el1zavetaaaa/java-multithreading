package basics.synchronization;

public class CharacterCheckerSynchronized implements Runnable {
    private CharacterAppenderSynchronized appender;

    public CharacterCheckerSynchronized(CharacterAppenderSynchronized appender) {
        this.appender = appender;
    }

    @Override
    public void run() {
        while (!appender.isCanceled()) {
            StringBuilder string = appender.appendNewChars();
            System.out.println(string.length());
            if (string.length() % 2 != 0) {
                System.out.println(string.length() + " not even");
                appender.cancel();
            }
            if (string.length() > 10) {
                appender.cancel();
            }
        }
    }
}
