package com.openpayd.foreignexchange.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExchangeRequest {
    private Currency base;
    private Currency target;
    private BigDecimal amount;

}
