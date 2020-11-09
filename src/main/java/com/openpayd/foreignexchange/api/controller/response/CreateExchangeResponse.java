package com.openpayd.foreignexchange.api.controller.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateExchangeResponse {
    private BigDecimal amount;
    private BigDecimal calculated;
    private String transactionId;
}
