package com.example.demo.repository;

import com.example.demo.model.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepo extends JpaRepository<Inquiry,Integer>

{
}
