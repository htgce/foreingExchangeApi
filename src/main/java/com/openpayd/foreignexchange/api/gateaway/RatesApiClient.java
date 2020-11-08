package com.openpayd.foreignexchange.api.gateaway;

import com.openpayd.foreignexchange.api.gateaway.response.RatesApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Currency;
import java.util.List;

@FeignClient(value = "rates-service", url = "https://api.ratesapi.io/")
public interface RatesApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "api/latest", produces = "application/json")
    RatesApiResponse getRates(@RequestParam("base") Currency base,
                              @RequestParam("symbols") List<Currency> symbols);

}
