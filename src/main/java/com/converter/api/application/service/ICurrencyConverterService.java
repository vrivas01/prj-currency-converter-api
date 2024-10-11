package com.converter.api.application.service;

import com.converter.api.application.dto.response.CalculatedExchangeRateResponse;
import com.converter.api.application.dto.response.ExchangeRateHistoryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

public interface ICurrencyConverterService {
    Mono<CalculatedExchangeRateResponse> getExchangeRate(BigDecimal amount, String currencyOrigin, String currencyDestination);
    Flux<ExchangeRateHistoryResponse> getExchangeRateHistory();
}
