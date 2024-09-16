package basics.heppensbefore;

public class JobsOpportunityMain {
    public static void main(String[] args) throws InterruptedException {
        Job job = new Job();
        JobsOpportunity jobsOpportunity = new JobsOpportunity();

        Thread thread1 = new Thread(new Runnable1(jobsOpportunity, job));
        Thread thread2 = new Thread(new Runnable2(jobsOpportunity));

        thread1.start();
        thread2.start();
    }
}
