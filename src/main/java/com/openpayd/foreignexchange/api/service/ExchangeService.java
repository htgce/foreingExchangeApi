package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.dto.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.dto.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dto.ExchangeDto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface ExchangeService {
    CreateExchangeResponse createExchange(CreateExchangeRequest createExchangeRequest);

    ExchangeDto getExchangeById(String id);

    Page<ExchangeDto> getAllExchangeByTransactionDateBetween(LocalDateTime beginDate, LocalDateTime endDate, int page, int size);
}