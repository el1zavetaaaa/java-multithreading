package basics.daemons;

import java.util.concurrent.ThreadFactory;

public class DaemonsThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread tr = new Thread(r);
        tr.setDaemon(true);
        return tr;
    }

    public String testDaemonsFactoryIsUsing() {
        return "Daemons Factory is using";
    }
}
