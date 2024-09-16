package basics.heppensbefore;

public class Runnable1 implements Runnable {
    JobsOpportunity jobsOpportunity;
    Job job;

    public Runnable1(JobsOpportunity jobsOpportunity, Job job) {
        this.jobsOpportunity = jobsOpportunity;
        this.job = job;
    }


    @Override
    public void run() {
        jobsOpportunity.storeJobsInfo(job);
    }
}
