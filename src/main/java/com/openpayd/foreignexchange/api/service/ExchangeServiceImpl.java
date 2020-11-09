package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.controller.request.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dao.ExchangeRepository;
import com.openpayd.foreignexchange.api.mapper.ExchangeMapper;
import com.openpayd.foreignexchange.api.model.entity.Exchange;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository repository;
    private final ExchangeMapper mapper;

    @Override
    public CreateExchangeResponse createExchange(CreateExchangeRequest createExchangeRequest) {
        Exchange savedExchange = repository.save(mapper.mapCreateExchangeRequestToEntity(createExchangeRequest));
        return mapper.mapEntityToCreateExchangeResponse(savedExchange);
    }
}
