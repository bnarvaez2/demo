package com.example.demo.discount;

import lombok.Data;

@Data
public class DiscountResponse {
    
    private boolean status;
    private String id;
    private String userId;
    private String houseId;
    private String discountCode;
}
