package com.openpayd.foreignexchange.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExchangeResponse {
    private BigDecimal amount;
    private String transactionId;
}
