package com.openpayd.foreignexchange.api.controller;

import com.openpayd.foreignexchange.api.controller.request.CreateExchangeRequest;
import com.openpayd.foreignexchange.api.controller.response.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.model.dto.ExchangeDto;
import com.openpayd.foreignexchange.api.service.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<CreateExchangeResponse> createExchange(@RequestBody CreateExchangeRequest createExchangeRequest) {
        return ResponseEntity.ok(exchangeService.createExchange(createExchangeRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<ExchangeDto> getExchangeById(@PathVariable String id) {
        return ResponseEntity.ok(exchangeService.getExchangeById(id));
    }

    @GetMapping("begin-date/{begin-date}/end-date/{end-date}")
    public ResponseEntity<Page<ExchangeDto>> getExchangesBetweenDates(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @PathVariable("begin-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
            @PathVariable("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(exchangeService.getAllExchangeByTransactionDateBetween(beginDate, endDate, page, size));
    }
}
