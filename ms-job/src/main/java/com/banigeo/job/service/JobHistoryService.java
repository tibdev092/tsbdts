package com.banigeo.job.service;

import com.banigeo.job.dto.JobHistoryResponse;
import com.banigeo.job.model.JobHistory;
import com.banigeo.job.repository.JobHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobHistoryService {
    private JobHistoryRepository historyRepository;
    private JobService jobService;

    public List<JobHistory> getHistoryByEmployee(Integer employeeId) {
        return historyRepository.findAllByEmployeeId(employeeId);
    }

    public List<JobHistoryResponse> findEmploymentHistory(Integer employeeId) {
        List<JobHistory> historyByEmployee = getHistoryByEmployee(employeeId);
        return historyByEmployee.stream().map(
                t -> JobHistoryResponse.builder()
                        .startJob(t.getStart())
                        .endJob(t.getEnd())
                        .job(t.getJobTitle())
                        .salary(t.getSalary())
                        .build()
        ).collect(Collectors.toList());
    }

    public void saveHistoryForEmployee(Integer employeeId, String jobName, Double salary) {
        List<JobHistory> history = getHistoryByEmployee(employeeId);
        JobHistory newHistory = JobHistory.builder()
                .employeeId(employeeId)
                .jobTitle(jobName)
                .salary(salary)
                .start(LocalDateTime.now())
                .build();

        if(history.size() == 0) {
            historyRepository.save(newHistory);
        } else {
            Optional<JobHistory> lastJob = history.stream().filter(t -> Objects.isNull(t.getEnd())).findFirst();
            if (lastJob.isPresent()) {
                JobHistory oldHistory = lastJob.get();
                oldHistory.setEnd(LocalDateTime.now());
                historyRepository.save(oldHistory);
                historyRepository.save(newHistory);
            } else {
                historyRepository.save(newHistory);
            }
        }

    }

}

