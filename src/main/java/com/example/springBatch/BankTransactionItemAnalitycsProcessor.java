package com.example.springBatch;

import com.example.springBatch.dao.BankTransaction;
import lombok.Getter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

//@Component
public class BankTransactionItemAnalitycsProcessor implements ItemProcessor<BankTransaction, BankTransaction> {

    @Getter private double totalDebit;
    @Getter private double totalCredit;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
    @Override
    public BankTransaction process(BankTransaction bankTransaction) throws Exception {
        if(bankTransaction.getType().equals("D")) totalDebit += bankTransaction.getMontant();
        else if(bankTransaction.getType().equals("C")) totalCredit += bankTransaction.getMontant();
        bankTransaction.setDateTransaction(dateFormat.parse(bankTransaction.getStrDateTransaction()));
        return bankTransaction;
    }
}
