package com.openpayd.foreignexchange.api.gateaway;

import com.openpayd.foreignexchange.api.controller.request.GetExchangeRateRequest;
import com.openpayd.foreignexchange.api.controller.response.GetExchangeRateResponse;
import feign.Feign.Builder;

public class RatesApiClient {


    private static RatesApiClient instance;
    private final RatesApi ratesApi;

    private RatesApiClient(String ratesApiUrl, Builder builder) {
        ratesApi = builder.target(RatesApi.class, ratesApiUrl);
    }

    public static RatesApiClient getInstance(String ratesApiUrl, Builder builder) {
        if (instance == null) {
            instance = new RatesApiClient(ratesApiUrl, builder);
        }
        return instance;
    }

    public GetExchangeRateResponse getRates(GetExchangeRateRequest request) {
        return ratesApi.getRates(request.getBase(), request.getSymbols());
    }
}
