package com.openpayd.foreignexchange.api.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetExchangeRateRequest {
    @NotNull
    private Currency base;
    @NotNull
    private List<Currency> symbols;
}
