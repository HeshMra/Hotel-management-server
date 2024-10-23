package com.example.demo.controller;

import com.example.demo.DTO.InquiryDTO;
import com.example.demo.HotelServices.EmailServices.EmailService;
import com.example.demo.HotelServices.InquiryService;
import com.example.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inquiry")

@CrossOrigin(origins = "*")
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private EmailService emailService;

    //add Inquiry
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveInquiry(@RequestBody InquiryDTO inquiryDTO){
        String message = inquiryService.saveInquiry(inquiryDTO);

        // Prepare email details
        String adminEmail = "miradorcottage24@gmail.com";
        String subject = "New Inquiry Received";

        // Fetch inquiry details from InquiryDTO
        int inquiryId = inquiryDTO.getInquiryId(); // Assuming InquiryDTO has this field
        String customerName = inquiryDTO.getCustomerName(); // Get the customer name
        String customerAddress = inquiryDTO.getCustomerAddress(); // Get the customer address
        String nic = inquiryDTO.getNic(); // Get the NIC

        // Prepare the email message using the details fetched
        String emailMessage = "A new inquiry has been posted by " + customerName + " (Inquiry ID: " + inquiryId + ").\n" +
                "Customer Address: " + customerAddress + "\n" +
                "Customer NIC: " + nic + ".";

        // Send notification to admin
        emailService.sendInquiryNotification(adminEmail, subject, emailMessage);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

}
