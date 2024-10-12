package com.example.demo.controller;

import com.example.demo.DTO.InquiryDTO;
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

    //add Inquiry
    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveInquiry(@RequestBody InquiryDTO inquiryDTO){
        String message = inquiryService.saveInquiry(inquiryDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );
    }

}
