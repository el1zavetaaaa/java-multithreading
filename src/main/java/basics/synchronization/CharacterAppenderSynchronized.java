package basics.synchronization;

public class CharacterAppenderSynchronized {
    private StringBuilder stringBuilder;
    private boolean canceled = false;

    public CharacterAppenderSynchronized(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public synchronized StringBuilder appendNewChars() {
        stringBuilder.append("1");
        Thread.yield();
        stringBuilder.append("2");
        return stringBuilder;
    }

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
