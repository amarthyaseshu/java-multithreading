package com.amarthya.virtualthreadscomp.service;


import com.amarthya.virtualthreadscomp.entity.Customer;
import com.amarthya.virtualthreadscomp.repository.CustomerRepository;
import com.amarthya.virtualthreadscomp.util.CsvReportUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReportService {

    @Autowired
    private CustomerRepository repository;

     //300
    //tomcat default thread 200
    //200 request processing
    //100 request waiting in queue
    public void generateReportForRegion(String region) {

        log.info("generating report for region: {} | {}", region, Thread.currentThread());

        List<Customer> customers = repository.findByRegion(region);//1
        try {
            CsvReportUtil.writeCustomersToCsv("simple_" + region, customers);//2
        } catch (Exception e) {
            System.out.println("❌ Error writing report for region: " + region);
        }

    }
}

