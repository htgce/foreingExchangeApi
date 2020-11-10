package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.dto.GetExchangeRateRequest;
import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.gateway.RatesApiClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final RatesApiClient ratesApiClient;

    @Override
    public GetExchangeRateResponse getRates(Currency base, List<Currency> symbols) {
        return ratesApiClient.getRates(GetExchangeRateRequest.builder()
                .base(base)
                .symbols(symbols)
                .build());
    }
}
