package com.amarthya.virtualthreadscomp.repository;



import com.amarthya.virtualthreadscomp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    List<Customer> findByRegion(String region);
}
