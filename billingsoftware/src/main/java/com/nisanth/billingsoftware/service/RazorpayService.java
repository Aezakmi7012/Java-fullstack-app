package com.nisanth.billingsoftware.service;

import com.nisanth.billingsoftware.io.RazorPayOrderResponse;

public interface RazorpayService {

    RazorPayOrderResponse createOrder(Double amount, String currency);

}
