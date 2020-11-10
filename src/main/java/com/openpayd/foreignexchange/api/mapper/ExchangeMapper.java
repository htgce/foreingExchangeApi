package com.openpayd.foreignexchange.api.mapper;

import com.openpayd.foreignexchange.api.dao.Exchange;
import com.openpayd.foreignexchange.api.dto.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dto.ExchangeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeMapper {

    ExchangeDto mapEntityToDto(Exchange exchange);

    CreateExchangeResponse mapEntityToCreateExchangeResponse(Exchange exchange);
}
