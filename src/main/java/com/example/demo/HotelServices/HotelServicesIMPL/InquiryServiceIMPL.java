package com.example.demo.HotelServices.HotelServicesIMPL;

import com.example.demo.DTO.InquiryDTO;
import com.example.demo.HotelServices.InquiryService;
import com.example.demo.model.Inquiry;
import com.example.demo.repository.InquiryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceIMPL implements InquiryService
{
    @Autowired
    private InquiryRepo inquiryRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String saveInquiry(InquiryDTO inquiryDTO) {
        Inquiry inquiry=  modelMapper.map(inquiryDTO,Inquiry.class);
        if (!inquiryRepo.existsById(inquiry.getInquiryId())) {
            inquiryRepo.save(inquiry);
            return inquiry.getInquiryId() + "inquiry saved successfully";
        } else {
            throw new DuplicateKeyException("inquiry Already Added");
        }
    }

    @Override
    public List<InquiryDTO> getallInquiries() {
        List<Inquiry> inquiries = inquiryRepo.findAll();
        return inquiries.stream()
                .map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class))
                .toList();
    }

}
