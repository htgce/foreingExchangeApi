package com.openpayd.foreignexchange.api.integration;

import com.openpayd.foreignexchange.api.controller.ExchangeController;
import com.openpayd.foreignexchange.api.dto.CreateExchangeResponse;
import com.openpayd.foreignexchange.api.dto.ExchangeDto;
import com.openpayd.foreignexchange.api.service.ExchangeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = ExchangeController.class)
public class ExchangeControllerIntegrationTest {
    private static final String URL = "/exchange";
    private static final String mockId = "1";
    @MockBean
    ExchangeServiceImpl exchangeService;
    @InjectMocks
    ExchangeController exchangeController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void given1ExchangeExistsWithAnIdWhenGetExchangeIsCalledWithThisIdThenGetHttpOk() throws Exception {
        given(this.exchangeService.getExchangeById(any())).willReturn(ExchangeDto.builder().build());
        String urlToCall = URL + '/' + mockId;
        this.mockMvc.perform(get(urlToCall)).andExpect(status().isOk());
    }

    @Test
    public void given1ExchangeExistsWithinADateRangeWhenGetExchangeIsCalledWithinThisDateRangeThenGetHttpOk() throws Exception {
        LocalDateTime beginDate = LocalDateTime.MIN;
        LocalDateTime endDate = LocalDateTime.MAX;
        Page<ExchangeDto> mockExchangePage = new PageImpl<>(Collections.singletonList(ExchangeDto.builder().build()));
        given(this.exchangeService.getAllExchangeByTransactionDateBetween(beginDate, endDate, 0, 0)).willReturn(mockExchangePage);
        String urlToCall = URL + '/' + beginDate + '/' + endDate;
        this.mockMvc.perform(get(urlToCall)).andExpect(status().isOk());
    }

    @Test
    public void givenCreateExchangeResponseWhenExchangeIsRequestedToSaveThenGetHttpOk() throws Exception {
        given(this.exchangeService.createExchange(any())).willReturn(CreateExchangeResponse.builder().build());
        this.mockMvc.perform(post(URL)).andExpect(status().isOk());
    }

}
