package com.multiple.datasource.repository.orderDetails;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.datasource.entity.orderDetails.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository <OrderDetails,Long>{

}
