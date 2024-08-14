package basics.synchronization.base;

public class CharacterAppender {
    private StringBuilder stringBuilder;
    private boolean canceled = false;

    public CharacterAppender(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public StringBuilder appendNewChars() {
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
