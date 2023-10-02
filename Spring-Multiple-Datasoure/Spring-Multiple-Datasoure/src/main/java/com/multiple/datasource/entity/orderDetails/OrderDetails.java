package com.multiple.datasource.entity.orderDetails;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.multiple.datasource.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetails {

	@Id
	@Column(name = "order_id") 
    private Long orderId;
	
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name ="product_name")
	private String productName;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="payment_mode")
	private PaymentMode paymentMode;
	
	@Column(name ="product_price")
	private Long productPrice;
	
	@Column(name ="quantity")
	private Long quantity;
	
	@Column(name ="discount")
	private Long discount;
	
	@Column(name ="total")
	private Double total;
	
	@Column(name ="date")
	private LocalDate date;
	
	@Column(name ="bill_number")
	private String billNumber;
	
	@Column(name ="customer_id")
	private Long customerId;

}
