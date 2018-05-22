package com.sda.spring.utils;

import com.sda.spring.entity.*;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.service.TransferHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class TransferChecker {

    private final SuspiciousPersonRepository suspiciousPersonRepository;
    private final TransferHistoryServiceImpl transferHistoryService;

    @Autowired
    public TransferChecker(SuspiciousPersonRepository suspiciousPersonRepository, TransferHistoryServiceImpl transferHistoryService) {
        this.suspiciousPersonRepository = suspiciousPersonRepository;
        this.transferHistoryService = transferHistoryService;
    }

    private final static Integer CHECKING_MULTIPLIER = 1000;

    public Boolean isTransferSuspicious(Customer customerSender, Customer customerReceiver, BigDecimal moneyToTransfer) {

        Boolean personIsSuspicious = isSenderOrReceiverSuspicious(customerSender, customerReceiver);
        Boolean amountIsSuspicious = isAmountSuspicious(customerSender, moneyToTransfer);

        return personIsSuspicious || amountIsSuspicious;
    }

    private BigDecimal getBiggestTransactionFromLast12Months(String bankAccountNumber) {

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime limitDate = LocalDateTime.of(currentDate.getYear() - 1, currentDate.getMonth(),
                currentDate.getDayOfMonth(), currentDate.getHour(), currentDate.getMinute(), currentDate.getSecond(),
                currentDate.getNano());

        List<TransferHistory> foundAccountHistories = transferHistoryService.
                getTransferHistoryForSpecificAccount(bankAccountNumber);

        List<BigDecimal> transferAmountsFromLast12Months = new LinkedList<>();

        for (TransferHistory history : foundAccountHistories) {
            if (history.getDate().compareTo(limitDate) >= 0) {
                transferAmountsFromLast12Months.add(history.getAmount());
            }
        }
        return transferAmountsFromLast12Months.stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

    }

    private Boolean isSenderOrReceiverSuspicious(Customer customerSender, Customer customerReceiver) {
        SuspiciousPerson suspiciousPersonFrom = suspiciousPersonRepository
                .findSuspiciousPersonByFirstNameAndLastNameAndPesel(customerSender.getFirstName(),
                        customerSender.getLastName(), customerSender.getPesel());
        SuspiciousPerson suspiciousPersonTo = suspiciousPersonRepository
                .findSuspiciousPersonByFirstNameAndLastNameAndPesel(customerReceiver.getFirstName(),
                        customerReceiver.getLastName(), customerReceiver.getPesel());
        return suspiciousPersonFrom != null || suspiciousPersonTo != null;
    }

    private Boolean isAmountSuspicious(Customer customerSender, BigDecimal amountToCheck) {
        Boolean amountSuspicious = null;

        BigDecimal biggestTransactionOfAccountFromInLast12Months = getBiggestTransactionFromLast12Months(
                customerSender.getAccount().getBankAccountNumber());
        if (biggestTransactionOfAccountFromInLast12Months.doubleValue() * CHECKING_MULTIPLIER <= amountToCheck.doubleValue()) {
            amountSuspicious = true;
        } else {
            amountSuspicious = false;
        }
        return amountSuspicious;
    }
}
