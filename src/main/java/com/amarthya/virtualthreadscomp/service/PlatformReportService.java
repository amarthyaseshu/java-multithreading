package com.amarthya.virtualthreadscomp.service;


import com.amarthya.virtualthreadscomp.entity.Customer;
import com.amarthya.virtualthreadscomp.repository.CustomerRepository;
import com.amarthya.virtualthreadscomp.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class PlatformReportService {

    @Autowired
    private CustomerRepository repository;


    Executor executor= Executors.newFixedThreadPool(5);

    public void generateReportForRegion(String region) {

        executor.execute(()->{
            log.info("Platform generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);//1
            try {
                CsvReportUtil.writeCustomersToCsv("platform_" + region, customers);//2
            } catch (Exception e) {
                System.out.println("❌ Platform Error writing report for region: " + region);
            }
        });

    }
}

