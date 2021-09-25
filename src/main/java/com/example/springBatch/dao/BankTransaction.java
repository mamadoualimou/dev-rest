package com.example.springBatch.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class BankTransaction {
    @Id
    private long id;
    private long accountId;
    private Date dateTransaction;
    @Transient
    private String strDateTransaction;
    private String type;
    private double montant;

}
