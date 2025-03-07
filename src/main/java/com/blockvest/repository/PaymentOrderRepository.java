package com.blockvest.repository;

import com.blockvest.model.PaymentOrder;
import com.blockvest.service.PaymentService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
}
