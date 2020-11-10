package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.dao.Exchange;
import com.openpayd.foreignexchange.api.dao.ExchangeRepository;
import com.openpayd.foreignexchange.api.dto.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.dto.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dto.ExchangeDto;
import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.mapper.ExchangeMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExchangeServiceTest {
    private static final String mockId = "1";
    @Mock
    private ExchangeRepository exchangeRepository;
    @Mock
    private ExchangeMapper exchangeMapper;
    @Mock
    private ExchangeRateServiceImpl exchangeRateService;
    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    @Test
    public void given1ExchangeExistsInDbWhenExchangeRequestedByIdThenGet1Exchange() {
        Currency mockCurrency = getMockCurrency();
        Exchange mockExchange = getMockExchange();
        ExchangeDto mockExchangeDto = getMockExchangeDto();
        mockExchangeDto.setBase(mockCurrency);
        when(exchangeRepository.getOne(any())).thenReturn(mockExchange);
        when(exchangeMapper.mapEntityToDto(mockExchange)).thenReturn(mockExchangeDto);
        ExchangeDto exchangeById = exchangeService.getExchangeById(mockId);
        assertEquals(mockCurrency, exchangeById.getBase());
    }

    @Test
    public void givenCreateExchangeRequestDtoWhenExchangeIsRequestedToSaveThenResultHasAnId() {
        CreateExchangeResponse mockCreateExchangeResponse = CreateExchangeResponse.builder().build();
        mockCreateExchangeResponse.setAmount(BigDecimal.ONE);

        CreateExchangeRequest mockCreateExchangeRequest = populateAndGetCreateExchangeRequest();

        GetExchangeRateResponse mockExchangeRateResponse = populateAndGetMockExchangeRateResponse();

        when(exchangeRateService.getRates(any(), any())).thenReturn(mockExchangeRateResponse);
        when(exchangeRepository.save(any(Exchange.class))).thenReturn(getMockExchange());
        when(exchangeMapper.mapEntityToCreateExchangeResponse(any())).thenReturn(mockCreateExchangeResponse);

        CreateExchangeResponse exchange = exchangeService.createExchange(mockCreateExchangeRequest);
        assertEquals(BigDecimal.ONE, exchange.getAmount());
    }

    @Test
    public void given1ExchangeExistsInSpecificDateRangeWhenExchangesRequestedByTransactionDateRangeGet1Exchange() {

        Page<Exchange> mockExchangePage = new PageImpl<>(Collections.singletonList(getMockExchange()));
        when(exchangeRepository.findAllByTransactionDateBetween(any(), any(), any())).thenReturn(mockExchangePage);
        when(exchangeMapper.mapEntityToDto(any())).thenReturn(getMockExchangeDto());
        Page<ExchangeDto> exchangePage = exchangeService.getAllExchangeByTransactionDateBetween(LocalDateTime.now(), LocalDateTime.now(), 1, 1);
        Assert.assertEquals(1, exchangePage.getSize());
    }

    private Currency getMockCurrency() {
        return Currency.getInstance("EUR");
    }

    private ExchangeDto getMockExchangeDto() {
        return ExchangeDto.builder().build();
    }

    private Exchange getMockExchange() {
        return Exchange.builder().build();
    }

    private CreateExchangeRequest populateAndGetCreateExchangeRequest() {
        CreateExchangeRequest mockCreateExchangeRequest = CreateExchangeRequest.builder().build();
        mockCreateExchangeRequest.setAmount(BigDecimal.ONE);
        mockCreateExchangeRequest.setBase(getMockCurrency());
        mockCreateExchangeRequest.setTarget(getMockCurrency());
        return mockCreateExchangeRequest;
    }

    private GetExchangeRateResponse populateAndGetMockExchangeRateResponse() {
        Map<Currency, BigDecimal> mockRatesMap = new HashMap<>();
        mockRatesMap.put(getMockCurrency(), BigDecimal.ONE);
        GetExchangeRateResponse mockExchangeRateResponse = GetExchangeRateResponse.builder().build();
        mockExchangeRateResponse.setRates(mockRatesMap);
        mockExchangeRateResponse.setDate(LocalDate.now());
        mockExchangeRateResponse.setBase(getMockCurrency());
        return mockExchangeRateResponse;
    }

}
