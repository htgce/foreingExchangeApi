package com.openpayd.foreignexchange.api.controller;

import com.openpayd.foreignexchange.api.dto.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.dto.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dto.ExchangeDto;
import com.openpayd.foreignexchange.api.service.ExchangeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExchangeControllerTest {

    private static final String mockId = "1";
    @Mock
    ExchangeServiceImpl exchangeService;
    @InjectMocks
    ExchangeController exchangeController;

    @Test
    public void given1ExchangeExistsInDbWhenExchangeRequestedByIdThenGet1Exchange() {
        ExchangeDto mockExchangeDto = getMockExchangeDto();
        mockExchangeDto.setTransactionId(mockId);
        when(exchangeService.getExchangeById(any())).thenReturn(mockExchangeDto);
        ResponseEntity<ExchangeDto> exchangeDtoResponseEntity = exchangeController.getExchangeById(mockId);
        List<ExchangeDto> mockResponseList = Collections.singletonList(exchangeDtoResponseEntity.getBody());
        assertEquals(1, mockResponseList.size());
        assertEquals(HttpStatus.OK, exchangeDtoResponseEntity.getStatusCode());
    }

    @Test
    public void givenCreateExchangeRequestDtoWhenExchangeIsRequestedToSaveThenResultHasAnId() {
        CreateExchangeRequest mockCreateExchangeRequest = CreateExchangeRequest.builder().build();
        CreateExchangeResponse mockCreateExchangeResponse = CreateExchangeResponse.builder().build();
        mockCreateExchangeResponse.setTransactionId(mockId);
        when(exchangeService.createExchange(any())).thenReturn(mockCreateExchangeResponse);
        ResponseEntity<CreateExchangeResponse> createExchangeResponseEntity = exchangeController.createExchange(mockCreateExchangeRequest);
        assertEquals(mockId, Objects.requireNonNull(createExchangeResponseEntity.getBody()).getTransactionId());
        assertEquals(HttpStatus.OK, createExchangeResponseEntity.getStatusCode());
    }

    @Test
    public void given1ExchangeExistsWithinADateRangeWhenExchangesRequestedByThisTransactionDateRangeGet1Exchange() {
        Page<ExchangeDto> mockExchangePage = new PageImpl<>(Collections.singletonList(ExchangeDto.builder().build()));
        when(exchangeService.getAllExchangeByTransactionDateBetween(LocalDateTime.MIN, LocalDateTime.MAX, 0, 0)).thenReturn(mockExchangePage);
        ResponseEntity<Page<ExchangeDto>> exchangesBetweenDates = exchangeController.getExchangesBetweenDates(0, 0, LocalDateTime.MIN, LocalDateTime.MAX);
        Assert.assertEquals(1, Objects.requireNonNull(exchangesBetweenDates.getBody()).getSize());
        assertEquals(HttpStatus.OK, exchangesBetweenDates.getStatusCode());
    }

    private ExchangeDto getMockExchangeDto() {
        return ExchangeDto.builder().build();
    }


}
