package com.sda.spring.repository;

import com.sda.spring.entity.SuspiciousTransferHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface SuspiciousTransferHistoryRepository extends CrudRepository<SuspiciousTransferHistory, Integer> {

    List<SuspiciousTransferHistory> findAll();
}
