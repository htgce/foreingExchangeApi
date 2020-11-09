package com.openpayd.foreignexchange.api.controller.request;

import lombok.*;

import java.util.Currency;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetExchangeRateRequest {
    private Currency base;
    private List<Currency> symbols;
}
