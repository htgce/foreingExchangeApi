package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.dto.GetExchangeRateRequest;
import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.gateway.RatesApi;
import com.openpayd.foreignexchange.api.gateway.RatesApiClient;
import com.openpayd.foreignexchange.api.mapper.ExchangeMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExchangeRateServiceTest {
    @Mock
    GetExchangeRateRequest.GetExchangeRateRequestBuilder mockBuilder;
    @Mock
    private RatesApiClient ratesApiClient;
    @Mock
    private RatesApi ratesApi;
    @Mock
    private ExchangeMapper exchangeMapper;
    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    public void given1ExchangeRateWithASourceAndATargetCurrencyWhenExchangeRateRequestedWithThisSourceAndThisTargetCurrency1ExchangeRate() {
        Currency mockCurrency = Currency.getInstance("EUR");
        GetExchangeRateResponse mockGetExchangeRateResponse = GetExchangeRateResponse.builder().build();
        Map<Currency, BigDecimal> mockRatesMap = new HashMap<>();
        mockRatesMap.put(mockCurrency, BigDecimal.ONE);
        mockGetExchangeRateResponse.setRates(mockRatesMap);
        when(ratesApiClient.getRates(any())).thenReturn(mockGetExchangeRateResponse);
        GetExchangeRateResponse rates = exchangeRateService.getRates(mockCurrency, Collections.singletonList(mockCurrency));
        Assert.assertEquals(1, rates.getRates().size());
    }
}
