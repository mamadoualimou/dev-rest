package com.example.springBatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JobRestController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private BankTransactionItemAnalitycsProcessor analitycsProcessor;

    @GetMapping("/startJob")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> jobParameterMap = new HashMap<>();
        jobParameterMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(jobParameterMap);
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()){
            System.out.println(".........");
        }
        return jobExecution.getStatus();
    }
    @GetMapping("/analytics")
    public Map<String, Double> analytics(){
        Map<String, Double> mapAnalytics = new HashMap<>();
        mapAnalytics.put("totalCredit", analitycsProcessor.getTotalCredit());
        mapAnalytics.put("totalDebit", analitycsProcessor.getTotalDebit());
        return mapAnalytics;
    }
}
