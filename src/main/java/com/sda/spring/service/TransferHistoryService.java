package com.sda.spring.service;

import com.sda.spring.entity.TransferHistory;

import java.util.List;

public interface TransferHistoryService {

    List<TransferHistory> getAllTransferHistories();
    void addTransferHistory(TransferHistory transferHistory);
    List<TransferHistory> getTransferHistoryForSpecificAccount(String bankAccountNumber);

}
