package com.openpayd.foreignexchange.api.dto;

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
    private Currency base;
    private List<Currency> symbols;
}
