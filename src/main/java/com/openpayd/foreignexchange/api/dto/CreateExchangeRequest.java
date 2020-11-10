package com.openpayd.foreignexchange.api.dto;

import com.sun.istack.NotNull;
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
    @NotNull
    private Currency base;
    @NotNull
    private Currency target;
    @NotNull
    private BigDecimal amount;

}
