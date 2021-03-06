package com.openpayd.foreignexchange.api.gateway;

import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Currency;
import java.util.List;

@FeignClient(value = "rates-service")
public interface RatesApi {

    @RequestLine("GET ?base={base}&symbols={symbols}")
    GetExchangeRateResponse getRates(@Param("base") Currency base, @Param("symbols") List<Currency> symbols);
}
