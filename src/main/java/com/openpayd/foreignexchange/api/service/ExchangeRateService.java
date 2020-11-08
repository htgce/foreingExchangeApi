package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.gateaway.response.RatesApiResponse;

import java.util.Currency;
import java.util.List;

public interface ExchangeRateService {

    RatesApiResponse getExchangeRates(Currency base, List<Currency> symbols);
}
