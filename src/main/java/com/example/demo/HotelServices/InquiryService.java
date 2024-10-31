package com.example.demo.HotelServices;

import com.example.demo.DTO.InquiryDTO;

import java.util.List;

public interface InquiryService {
    String saveInquiry(InquiryDTO inquiryDTO);

    List<InquiryDTO> getallInquiries();
}
