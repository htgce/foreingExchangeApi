package com.openpayd.foreignexchange.api.service;

import com.openpayd.foreignexchange.api.controller.request.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;

public interface ExchangeService {
    CreateExchangeResponse createExchange(CreateExchangeRequest createExchangeRequest);
}