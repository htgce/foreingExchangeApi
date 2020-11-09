package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.controller.request.GetExchangeRateRequest;
import com.openpayd.foreignexchange.api.controller.response.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.gateaway.RatesApiClient;
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
