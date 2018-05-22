package com.sda.spring.repository;

import com.sda.spring.entity.TransferHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface TransferHistoryRepository extends CrudRepository<TransferHistory, Integer> {

    List<TransferHistory> findTransferHistoriesByBankAccountNumberFromOrderByDateDesc(String bankAccountNumber);
}
