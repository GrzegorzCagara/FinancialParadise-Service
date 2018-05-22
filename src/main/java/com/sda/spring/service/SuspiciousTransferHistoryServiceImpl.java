package com.sda.spring.service;

import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuspiciousTransferHistoryServiceImpl implements SuspiciousTransferHistoryService {

    private final SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository;

    @Autowired
    public SuspiciousTransferHistoryServiceImpl(SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository) {
        this.suspiciousTransferHistoryRepository = suspiciousTransferHistoryRepository;
    }

    @Override
    public List<SuspiciousTransferHistory> getAllSuspiciousTransferHistory() {
        return suspiciousTransferHistoryRepository.findAll();
    }
}
