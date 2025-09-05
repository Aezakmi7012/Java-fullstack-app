package com.nisanth.billingsoftware.service.impl;

import com.nisanth.billingsoftware.io.RazorPayOrderResponse;
import com.nisanth.billingsoftware.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MockPaymentServiceImpl implements PaymentService {

    @Value("${payment.mock.enabled:true}")
    private boolean mockEnabled;
    
    @Value("${payment.mock.success.rate:100}")
    private int successRate;

    @Override
    public RazorPayOrderResponse createOrder(Double amount, String currency) {
        // Generate mock payment order response
        String orderId = "mock_order_" + UUID.randomUUID().toString().replace("-", "").substring(0, 14);
        
        return RazorPayOrderResponse.builder()
                .id(orderId)
                .entity("order")
                .amount((int) Math.round(amount * 100)) // Convert to smallest currency unit
                .currency(currency)
                .status("created")
                .created_at(new Date())
                .receipt("receipt_" + System.currentTimeMillis())
                .build();
    }
}
