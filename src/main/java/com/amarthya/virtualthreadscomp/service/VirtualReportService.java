package com.amarthya.virtualthreadscomp.service;


import com.amarthya.virtualthreadscomp.entity.Customer;
import com.amarthya.virtualthreadscomp.repository.CustomerRepository;
import com.amarthya.virtualthreadscomp.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class VirtualReportService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private Executor virtualThreadExecutor ;



    public void generateReportForRegion(String region) {

        virtualThreadExecutor.execute(()->{
            log.info("Virtual generating report for region: {} | {}", region, Thread.currentThread());

            List<Customer> customers = repository.findByRegion(region);//1
            try {
                CsvReportUtil.writeCustomersToCsv("virtual_" + region, customers);//2
            } catch (Exception e) {
                System.out.println("❌ Virtual Error writing report for region: " + region);
            }
        });

    }
}

