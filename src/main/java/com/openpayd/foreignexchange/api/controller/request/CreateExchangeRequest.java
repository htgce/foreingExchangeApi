package com.openpayd.foreignexchange.api.controller.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateExchangeRequest {
    private Currency base;
    private Currency target;
    private BigDecimal amount;

}
