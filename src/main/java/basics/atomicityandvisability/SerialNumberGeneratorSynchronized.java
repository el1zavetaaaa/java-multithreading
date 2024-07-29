package basics.atomicityandvisability;

public class SerialNumberGeneratorSynchronized {
    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber() {
        return serialNumber++;
    }
}
