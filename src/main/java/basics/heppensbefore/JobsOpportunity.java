package basics.heppensbefore;

public class JobsOpportunity {

    private long jobsTakenCount = 0;
    private long suitableToYouJobsOpportunitiesCount = 0;
    private volatile boolean isNewJobAvailable = false;

    private Job job = null;

    public void storeJobsInfo(Job job) {
        this.job = job;
        this.suitableToYouJobsOpportunitiesCount++;
        this.isNewJobAvailable = true;
    }

    public Job takeJob() {
        while (!isNewJobAvailable) {
            System.out.println("waiting for new available job");
        }
        Job newJob = this.job;
        this.jobsTakenCount++;
        this.isNewJobAvailable = false;

        System.out.println("New job was taken " + this);
        return newJob;
    }

    public long getJobsTakenCount() {
        return jobsTakenCount;
    }

    public long getSuitableToYouJobsOpportunitiesCount() {
        return suitableToYouJobsOpportunitiesCount;
    }

    @Override
    public String toString() {
        return "JobsOpportunity{" +
                "jobsTakenCount=" + jobsTakenCount +
                ", suitableToYouJobsOpportunitiesCount=" + suitableToYouJobsOpportunitiesCount +
                ", isNewJobAvailable=" + isNewJobAvailable +
                ", job=" + job +
                '}';
    }
}
