package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.gateaway.response.RatesApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Override
    public RatesApiResponse getExchangeRates(Currency base, List<Currency> symbols) {
        return null;
    }
}
