package com.openpayd.foreignexchange.api.gateaway;

import com.openpayd.foreignexchange.api.gateaway.response.RatesApiResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Currency;
import java.util.List;

@FeignClient(value = "rates-service", url = "https://api.ratesapi.io/api/latest")
public interface RatesApiClient {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    RatesApiResponse getRates(@Param("base") Currency base,
                              @Param("symbols") List<Currency> symbols);

}
