package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.dao.Exchange;
import com.openpayd.foreignexchange.api.dao.ExchangeRepository;
import com.openpayd.foreignexchange.api.dto.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.dto.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dto.ExchangeDto;
import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.mapper.ExchangeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository repository;
    private final ExchangeRateService exchangeRateService;
    private final ExchangeMapper mapper;

    @Override
    public CreateExchangeResponse createExchange(CreateExchangeRequest createExchangeRequest) {
        GetExchangeRateResponse exchangeRateResponse = exchangeRateService.getRates(createExchangeRequest.getBase(), Collections.singletonList(createExchangeRequest.getTarget()));

        LocalDate currentExchangeRateDate = exchangeRateResponse.getDate();
        BigDecimal currentExchangeRate = exchangeRateResponse.getRates().get(createExchangeRequest.getTarget());

        Exchange currentExchange = getCalculatedExchange(createExchangeRequest, currentExchangeRate, currentExchangeRateDate);
        return mapper.mapEntityToCreateExchangeResponse(repository.save(currentExchange));
    }

    @Override
    public ExchangeDto getExchangeById(String id) {
        return mapper.mapEntityToDto(repository.getOne(Long.parseLong(id)));
    }

    @Override
    public Page<ExchangeDto> getAllExchangeByTransactionDateBetween(LocalDateTime beginDate, LocalDateTime endDate, int page, int size) {
        Page<Exchange> exchangesByTransDateBetween = repository.findAllByTransactionDateBetween(beginDate, endDate, PageRequest.of(page, size));
        return exchangesByTransDateBetween.map(mapper::mapEntityToDto);
    }

    private Exchange getCalculatedExchange(CreateExchangeRequest request, BigDecimal rate, LocalDate currentExchangeRateDate) {
        return Exchange.builder().rate(rate).base(request.getBase())
                .amount(request.getAmount()).calculated(request.getAmount().multiply(rate))
                .exchangeRateDate(currentExchangeRateDate).target(request.getTarget())
                .transactionDate(LocalDateTime.now()).build();
    }
}
