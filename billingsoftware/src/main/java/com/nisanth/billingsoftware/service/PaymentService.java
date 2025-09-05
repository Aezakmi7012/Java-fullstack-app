package com.nisanth.billingsoftware.service;

import com.nisanth.billingsoftware.io.RazorPayOrderResponse;

public interface PaymentService {

    RazorPayOrderResponse createOrder(Double amount, String currency);

}
