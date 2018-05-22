package com.sda.spring.service;

import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TransferHistoryServiceImpl implements TransferHistoryService {

    private final TransferHistoryRepository transferHistoryRepository;

    @Autowired
    public TransferHistoryServiceImpl(TransferHistoryRepository transferHistoryRepository) {
        this.transferHistoryRepository = transferHistoryRepository;
    }

    @Override
    public List<TransferHistory> getAllTransferHistories(){
        List<TransferHistory> transferHistories = new LinkedList<>();
        transferHistoryRepository.findAll().forEach(e -> transferHistories.add(e));
        return transferHistories;
    }

    @Override
    public void addTransferHistory(TransferHistory transferHistory){
        transferHistoryRepository.save(transferHistory);
    }

    @Override
    public List<TransferHistory> getTransferHistoryForSpecificAccount(String bankAccountNumber){
        return transferHistoryRepository.findTransferHistoriesByBankAccountNumberFromOrderByDateDesc(bankAccountNumber);
    }
}
