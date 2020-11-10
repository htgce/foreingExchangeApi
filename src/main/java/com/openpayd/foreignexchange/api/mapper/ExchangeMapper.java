package com.openpayd.foreignexchange.api.mapper;

import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.model.dto.ExchangeDto;
import com.openpayd.foreignexchange.api.model.entity.Exchange;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeMapper {

    ExchangeDto mapEntityToDto(Exchange exchange);

    CreateExchangeResponse mapEntityToCreateExchangeResponse(Exchange exchange);
}
