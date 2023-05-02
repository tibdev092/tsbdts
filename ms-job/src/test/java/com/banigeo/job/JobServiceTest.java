package com.banigeo.job;

import com.banigeo.job.dto.JobRequest;
import com.banigeo.job.dto.JobResponse;
import com.banigeo.job.exception.JobNotFoundException;
import com.banigeo.job.mapper.JobMapper;
import com.banigeo.job.model.Job;
import com.banigeo.job.repository.JobRepository;
import com.banigeo.job.service.JobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
    @Mock
    JobRepository jobRepository;
    @Mock
    JobMapper jobMapper;
    @InjectMocks
    JobService jobService;

    @Test
    public void testJobServiceSuccess() {
        Job mockedJob = Job.builder().jobId(1).jobTitle("Marketing_Manager").build();
        Answer<Optional<Job>> answer = invocationOnMock -> Optional.of(mockedJob);
        when(jobMapper.fromEntityToResponse(any())).thenCallRealMethod();
        when(jobRepository.findByJobTitle(any())).then(answer);
        JobResponse jobResponse = jobService.getJob("Marketing_Manager");

        assertEquals(jobMapper.fromEntityToResponse(mockedJob), jobResponse);
    }

    @Test
    public void testJobServiceFailure() {
        when(jobRepository.findById(any())).thenReturn(Optional.empty());
        assertThrowsExactly(JobNotFoundException.class, () -> jobService.getJob(1));
    }

    @Test
    public void testJobServiceCreate() {
        JobRequest request = new JobRequest();
        request.setTitle("Programmer");
        request.setMin(0D);
        request.setMax(1D);
        when(jobMapper.fromEntityToResponse(any())).thenCallRealMethod();
        when(jobMapper.fromRequestToEntity(any())).thenCallRealMethod();
        when(jobRepository.save(any())).thenAnswer(invocationOnMock -> {
            Job job = invocationOnMock.getArgument(0);
            job.setJobId(1);
            return job;
        });
        JobResponse job = jobService.createJob(request);

        assertEquals("Programmer", job.getTitle());
    }
}

