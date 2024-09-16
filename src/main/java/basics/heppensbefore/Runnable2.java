package basics.heppensbefore;

public class Runnable2 implements Runnable {
    JobsOpportunity jobsOpportunity;

    public Runnable2(JobsOpportunity jobsOpportunity) {
        this.jobsOpportunity = jobsOpportunity;
    }


    @Override
    public void run() {
        jobsOpportunity.takeJob();
    }
}
