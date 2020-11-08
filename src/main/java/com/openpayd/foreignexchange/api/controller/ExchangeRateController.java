package com.openpayd.foreignexchange.api.controller;

import com.openpayd.foreignexchange.api.gateaway.response.RatesApiResponse;
import com.openpayd.foreignexchange.api.service.ExchangeRateService;
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

    private ExchangeRateService exchangeRateService;

    @GetMapping
    public ResponseEntity<RatesApiResponse> getExchangeRate(@RequestParam Currency base,
                                                            @RequestParam List<Currency> symbols) {
        return ResponseEntity.ok(exchangeRateService.getExchangeRates(base, symbols));
    }
}