package com.sda.spring.controller;

import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer/suspicious")
public class SuspiciousTransferHistoryController {

    private final SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository;

    @Autowired
    public SuspiciousTransferHistoryController(SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository) {
        this.suspiciousTransferHistoryRepository = suspiciousTransferHistoryRepository;
    }

    @GetMapping("/list")
    public List<SuspiciousTransferHistory> getAllsuspiciousTransferHistory(){
        return suspiciousTransferHistoryRepository.findAll();
    }
}
