package com.openpayd.foreignexchange.api.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Getter
@Setter
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;
    private LocalDate exchangeRateDate;
    private Currency base;
    private Currency target;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal calculated;
    private LocalDateTime transactionDate;

}
