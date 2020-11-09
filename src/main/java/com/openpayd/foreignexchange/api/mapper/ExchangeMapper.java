package com.openpayd.foreignexchange.api.mapper;

import com.openpayd.foreignexchange.api.controller.request.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.model.entity.Exchange;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeMapper {

    Exchange mapCreateExchangeRequestToEntity(CreateExchangeRequest request);

    CreateExchangeResponse mapEntityToCreateExchangeResponse(Exchange exchange);
}
