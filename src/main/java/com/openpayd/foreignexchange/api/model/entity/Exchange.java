package com.openpayd.foreignexchange.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {
    @Id
    private String exchangeTransactionId;
    private LocalDate date;
    private Currency base;
    private Currency symbol;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal calculated;
    private LocalDateTime transactionDate;
}
