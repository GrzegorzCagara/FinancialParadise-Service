package com.sda.spring.service;

import com.sda.spring.exceptions.InsufficientFundsException;
import com.sda.spring.utils.TransferChecker;
import com.sda.spring.entity.Customer;
import com.sda.spring.entity.SuspiciousTransferHistory;
import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousTransferHistoryRepository;
import com.sda.spring.repository.TransferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MoneyTransferServiceImpl {

    private final static BigDecimal BALANCE_LIMIT = BigDecimal.ZERO;

    private final TransferChecker transferChecker;
    private final TransferHistoryRepository transferHistoryRepository;
    private final SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository;
    private final AccountServiceImpl accountService;

    @Autowired
    public MoneyTransferServiceImpl(SuspiciousTransferHistoryRepository suspiciousTransferHistoryRepository,
                                    TransferChecker transferChecker,
                                    TransferHistoryRepository transferHistoryRepository,
                                    AccountServiceImpl accountService) {
        this.suspiciousTransferHistoryRepository = suspiciousTransferHistoryRepository;
        this.transferChecker = transferChecker;
        this.transferHistoryRepository = transferHistoryRepository;
        this.accountService = accountService;
    }

    public void transferMoney(Customer customerSender, Customer customerReceiver, BigDecimal moneyToTransfer, String title) {

        TransferHistory transferHistory;
        SuspiciousTransferHistory suspiciousTransferHistory;
        Boolean transferIsSuspicious = transferChecker.isTransferSuspicious(customerSender, customerReceiver, moneyToTransfer);

        if (transferIsSuspicious) {
            suspiciousTransferHistory = new SuspiciousTransferHistory.Builder()
                    .withAmount(moneyToTransfer)
                    .withBankAccountNumberFrom(customerSender.getAccount().getBankAccountNumber())
                    .withBankAccountNumberTo(customerReceiver.getAccount().getBankAccountNumber())
                    .withTitle(title)
                    .withDate(LocalDateTime.now())
                    .build();
            suspiciousTransferHistoryRepository.save(suspiciousTransferHistory);
            accountService.save(customerSender.getAccount());
            accountService.save(customerReceiver.getAccount());
        }
        if (moneyToTransfer.compareTo(customerSender.getAccount().getBalance()) < 1 && customerSender.getAccount()
                .getBalance().compareTo(BALANCE_LIMIT) >= 0) {
            customerSender.getAccount().setBalance(customerSender.getAccount().getBalance().subtract(moneyToTransfer));
            customerReceiver.getAccount().setBalance(customerReceiver.getAccount().getBalance().add(moneyToTransfer));
            transferHistory = new TransferHistory.Builder()
                    .withAmount(moneyToTransfer)
                    .withBankAccountNumberFrom(customerSender.getAccount().getBankAccountNumber())
                    .withBankAccountNumberTo(customerReceiver.getAccount().getBankAccountNumber())
                    .withTitle(title)
                    .withDate(LocalDateTime.now())
                    .build();
            transferHistoryRepository.save(transferHistory);
            accountService.save(customerSender.getAccount());
            accountService.save(customerReceiver.getAccount());

        } else {
            throw new InsufficientFundsException();
        }
    }
}

