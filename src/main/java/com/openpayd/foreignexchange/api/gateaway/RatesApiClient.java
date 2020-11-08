package com.openpayd.foreignexchange.api.gateaway;

import com.openpayd.foreignexchange.api.gateaway.response.RatesApiResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Currency;
import java.util.List;

@FeignClient(value = "rates-service", url = "https://ticketservice.com/")
public interface RatesApiClient {
    @RequestLine("GET {path}?base={base}&symbols={symbols}")
    RatesApiResponse getRates(@Param("path") String path, @Param("base") Currency base,
                              @Param("symbols") List<Currency> symbols);
}
