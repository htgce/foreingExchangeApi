package com.openpayd.foreignexchange.api.controller;

import com.openpayd.foreignexchange.api.dto.GetExchangeRateResponse;
import com.openpayd.foreignexchange.api.service.ExchangeRateService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("exchange-rate")
public class ExchangeRateController {

    ExchangeRateService exchangeRateService;

    @GetMapping
    @ApiOperation(value = "Retrieve exchange rate value from RatesApiClient")
    public ResponseEntity<GetExchangeRateResponse> getExchangeRate(@RequestParam Currency base,
                                                                   @RequestParam List<Currency> symbols) {
        return ResponseEntity.ok(exchangeRateService.getRates(base, symbols));
    }
}
