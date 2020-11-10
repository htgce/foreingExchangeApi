package com.openpayd.foreignexchange.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto {
    private String transactionId;
    private Currency base;
    private Currency target;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal calculated;
    private LocalDateTime transactionDate;
}
