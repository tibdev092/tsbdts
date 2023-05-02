package com.banigeo.job;

import com.banigeo.job.dto.JobRequest;
import com.banigeo.job.dto.JobResponse;
import com.banigeo.job.mapper.JobMapper;
import com.banigeo.job.model.Job;
import com.banigeo.job.repository.JobRepository;
import com.banigeo.job.service.JobService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class JobApplicationTests {

    @Autowired
    private JobRepository jobRepository;

    @Mock
    private JobMapper jobMapper;

    private JobService jobService;

    @BeforeEach
    public  void init() {
        jobService = new JobService(jobRepository, jobMapper);
        Mockito.when(jobMapper.fromEntityToResponse(Mockito.any())).thenCallRealMethod();
        Mockito.when(jobMapper.fromRequestToEntity(Mockito.any())).thenCallRealMethod();
    }

    @Test
    public void test_create_job() {
        JobRequest request = new JobRequest();
        request.setMax(200d);
        request.setMin(100d);
        request.setTitle("TEST-1");

        JobResponse job = jobService.createJob(request);

        Assert.assertEquals("TEST-1", job.getTitle());
        Assert.assertEquals(200 , job.getMax().intValue());
        Assert.assertEquals(100 , job.getMin().intValue());
    }

    @Test
    public void test_find_job() {
        JobRequest request = new JobRequest();
        request.setMax(200d);
        request.setMin(100d);
        request.setTitle("TEST-1");

        JobResponse job = jobService.createJob(request);
        JobResponse foundJob = jobService.getJob(job.getId());

        Assert.assertEquals("TEST-1", foundJob.getTitle());
        Assert.assertEquals(foundJob, jobService.getJob("TEST-1"));
    }

    @Test
    public void test_checkSalaryRange() {
        JobRequest request = new JobRequest();
        request.setMax(200d);
        request.setMin(100d);
        request.setTitle("TEST-1");

        jobService.createJob(request);

        assertThrows(RuntimeException.class, () -> jobService.checkSalaryRange(300d, "TEST-1"));
    }

    @Test
    public void test_getJobsBySalaryRange() {
        JobRequest request = new JobRequest();
        request.setMax(200d);
        request.setMin(100d);
        request.setTitle("TEST-1");

        jobService.createJob(request);
        List<Job> jobs = jobService.getJobsBySalaryRange(105d);

        Assert.assertEquals(new String[] {"TEST-1"}, jobs.stream().map(Job::getJobTitle).toArray(String[]::new));
    }

    @Test
    public void test_getAllJobs() {
        JobRequest request1 = new JobRequest();
        request1.setMax(200d);
        request1.setMin(100d);
        request1.setTitle("TEST-1");

        JobRequest request2 = new JobRequest();
        request2.setMax(200d);
        request2.setMin(100d);
        request2.setTitle("TEST-2");

        jobService.createJob(request1);
        jobService.createJob(request2);

        List<JobResponse> allJobs = jobService.getAllJobs();

        Assert.assertEquals(new String[] {"TEST-1", "TEST-2"}, allJobs.stream().map(JobResponse::getTitle).toArray(String[]::new));
    }


        /**
         * getAllJobs
         * getJobsBySalaryRange
         */


}
