package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InquiryDTO {
    private int InquiryId;
    private String customerName;
    private String  customerAddress;
    private String nic;
}
