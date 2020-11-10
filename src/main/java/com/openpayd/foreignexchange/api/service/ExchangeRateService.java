package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;

import java.util.Currency;
import java.util.List;

public interface ExchangeRateService {
    GetExchangeRateResponse getRates(Currency base, List<Currency> symbols);
}
