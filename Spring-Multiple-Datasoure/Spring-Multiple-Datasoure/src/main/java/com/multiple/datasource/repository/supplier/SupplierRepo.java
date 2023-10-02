package com.multiple.datasource.repository.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.datasource.entity.supplier.SupplierDetails;

public interface SupplierRepo extends JpaRepository<SupplierDetails, Long>{

}
