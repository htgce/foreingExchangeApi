package com.openpayd.foreignexchange.api.controller;

import com.openpayd.foreignexchange.api.controller.request.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.service.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<CreateExchangeResponse> performExchange(@RequestBody CreateExchangeRequest createExchangeRequest) {
        return ResponseEntity.ok(exchangeService.createExchange(createExchangeRequest));
    }
}
