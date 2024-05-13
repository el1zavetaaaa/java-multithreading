package basics.runnable;

public class LiftOff implements Runnable {

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    protected int countDown = 4;
    protected int interactionCount;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public String status() {
        interactionCount++;
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + "), ";
    }

    public int getInteractionCount(){
        return interactionCount;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }
}
