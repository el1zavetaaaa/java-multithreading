package basics.blockingqueue.toastomatic2;

import basics.blockingqueue.Toast;
import basics.blockingqueue.ToastQueue;

public class Sandwich {

    private Toast top;
    private Toast bottom;

    private final int id;

    public Sandwich(Toast top, Toast bottom, int id) {
        this.top = top;
        this.bottom = bottom;
        this.id = id;
    }

    public Toast getTop() {
        return top;
    }

    public Toast getBottom() {
        return bottom;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "top=" + top +
                ", bottom=" + bottom +
                ", id=" + id +
                '}';
    }
}
