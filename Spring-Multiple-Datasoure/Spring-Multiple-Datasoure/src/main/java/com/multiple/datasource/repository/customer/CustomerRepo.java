package com.multiple.datasource.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.datasource.entity.customer.CustomerDetails;

public interface CustomerRepo extends JpaRepository<CustomerDetails, Long>{

}
