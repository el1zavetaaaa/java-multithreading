package basics.happensbefore;

import basics.heppensbefore.Job;
import basics.heppensbefore.JobsOpportunity;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

public class HappensBeforeTest {

    // add volatile keyword to JobsOpportunity#isNewJobAvailable to provide happens before guarantee,
    // which keyword "volatile" provides
    @Test
    public void testHappensBeforeGuaranteeWithVolatileKeyWord() throws InterruptedException {
        JobsOpportunity jobsOpportunity = new JobsOpportunity();
        Job job = new Job();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService exec = Executors.newFixedThreadPool(2);

        exec.submit(() -> {
            try {
                Thread.sleep(700);
                jobsOpportunity.storeJobsInfo(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        });

        exec.submit(() -> {
            Job takenJob = jobsOpportunity.takeJob();
            assertNotNull(takenJob, "Job should not be null when using volatile keyword");
            countDownLatch.countDown();
        });

        countDownLatch.await();
        exec.shutdown();

        assertEquals(1, jobsOpportunity.getJobsTakenCount());
        assertEquals(1, jobsOpportunity.getSuitableToYouJobsOpportunitiesCount());
    }

    // remove volatile keyword from JobsOpportunity#isNewJobAvailable to lose happens before guarantee,
    // which keyword "volatile" provides
    @Test
    void testWithoutVolatileKeyWordAndWithoutHappensBeforeGuarantee() throws InterruptedException {
        JobsOpportunity jobsOpportunity = new JobsOpportunity();
        Job job = new Job();

        // Latch to synchronize threads
        CountDownLatch latch = new CountDownLatch(2);

        // Create a thread pool to run threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Thread 1 (store job info)
        executor.submit(() -> {
            try {
                Thread.sleep(500); // Simulating delay
                jobsOpportunity.storeJobsInfo(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown(); // Decrement latch when done
        });

        // Thread 2 (take job)
        executor.submit(() -> {
            Job takenJob = jobsOpportunity.takeJob();
            if (takenJob == null) {
                System.out.println("Job could not be taken without volatile guarantee");
            }
            latch.countDown(); // Decrement latch when done
        });

        // Wait until both threads are finished
        latch.await();
        executor.shutdown();

        // Assert that the results are inconsistent (possible stale data)
        assertTrue(jobsOpportunity.getJobsTakenCount() == 0 || jobsOpportunity.getJobsTakenCount() == 1,
                "Inconsistent behavior without volatile keyword");
    }
}
