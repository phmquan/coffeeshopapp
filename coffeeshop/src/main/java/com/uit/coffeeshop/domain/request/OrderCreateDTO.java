package com.uit.coffeeshop.domain.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {
    private String orderNote;
    private String phoneNumber;
    private String receivedAddress;
    private String receivedName;
    private List<OrderDetailsDTO> orderDetails;
}
