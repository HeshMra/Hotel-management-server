package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "inquiries")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inquiry {

    @Id
    @Column(name = "inquiry_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int InquiryId;

    @Column(name = "customer_name",length = 45)
    private String customerName;

    @Column(name = "customer_address",length = 45)
    private String  customerAddress;

    @Column(name = "nic")
    private String nic;
}
