package com.openpayd.foreignexchange.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {
    @Id
    private String exchangeTransactionId;
    private LocalDate date;
    private Currency base;
    private Currency target;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal calculated;
    private LocalDateTime transactionDate;
    private String id;

}
