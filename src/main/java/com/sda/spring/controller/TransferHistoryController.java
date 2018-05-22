package com.sda.spring.controller;

import com.sda.spring.entity.TransferHistory;
import com.sda.spring.service.TransferHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer/history")
public class TransferHistoryController {

    private final TransferHistoryServiceImpl transferHistoryService;

    @Autowired
    public TransferHistoryController(TransferHistoryServiceImpl transferHistoryService) {
        this.transferHistoryService = transferHistoryService;
    }

    @GetMapping("/list" + "{accountNumber}")
    public List<TransferHistory> getAllTransferHistoryForSpecificAccount(@PathVariable("accountNumber")String accountNumber) {
        return transferHistoryService.getTransferHistoryForSpecificAccount(accountNumber);
    }
}
