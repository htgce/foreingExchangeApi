package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.controller.request.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.controller.response.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.dao.ExchangeRepository;
import com.openpayd.foreignexchange.api.mapper.ExchangeMapper;
import com.openpayd.foreignexchange.api.model.entity.Exchange;
import lombok.AllArgsConstructor;
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

    private Exchange getCalculatedExchange(CreateExchangeRequest request, BigDecimal rate, LocalDate currentExchangeRateDate) {
        return Exchange.builder().rate(rate).base(request.getBase())
                .amount(request.getAmount()).calculated(request.getAmount().multiply(rate))
                .exchangeRateDate(currentExchangeRateDate).target(request.getTarget())
                .transactionDate(LocalDateTime.now()).build();
    }
}
