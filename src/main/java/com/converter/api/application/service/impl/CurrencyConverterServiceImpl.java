package com.converter.api.application.service.impl;

import com.converter.api.application.dto.response.CalculatedExchangeRateResponse;
import com.converter.api.application.dto.response.ExchangeRateResponse;
import com.converter.api.application.enums.ResponseEnum;
import com.converter.api.application.mapper.CurrencyConverterMapper;
import com.converter.api.application.service.ICurrencyConverterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.converter.api.application.dto.response.ExchangeRateHistoryResponse;
import com.converter.api.configuration.ServiceConfig;
import com.converter.api.domain.CurrencyConversion;
import com.converter.api.exception.CurrencyConversionException;
import com.converter.api.infraestructure.repository.CurrencyConversionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
@Service
public class CurrencyConverterServiceImpl implements ICurrencyConverterService {

    @Qualifier("ExchangeRateWebClient")
    private final WebClient exchangeRateWebClient;
    private final ServiceConfig properties;
    private final CurrencyConversionRepository currencyConversionRepository;

    @Override
    public Mono<CalculatedExchangeRateResponse> getExchangeRate(BigDecimal amount, String currencyOrigin, String currencyDestination) {
        return exchangeRateWebClient.get()
                .uri(properties.getCurrencyApi().getExchangeRate(), currencyOrigin)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .flatMap(exchangeRateResponse -> {
                    validateExchangeRateResponse(exchangeRateResponse, currencyDestination);

                    BigDecimal exchangeRate = exchangeRateResponse.getRates().get(currencyDestination);
                    BigDecimal amountExchangeRate = amount.multiply(exchangeRate);

                    CurrencyConversion currencyConversion = CurrencyConverterMapper.toEntityCurrencyConversion(
                            amount,
                            currencyOrigin,
                            currencyDestination,
                            amountExchangeRate,
                            exchangeRate
                    );

                    return currencyConversionRepository.save(currencyConversion)
                            .map(CurrencyConverterMapper::toCalculatedResponse);
                });
    }

    @Override
    public Flux<ExchangeRateHistoryResponse> getExchangeRateHistory() {
        return currencyConversionRepository.findAll()
                .map(CurrencyConverterMapper::toHistoryResponse);
    }

    private void validateExchangeRateResponse(ExchangeRateResponse response, String currencyDestination) {
        if ("error".equalsIgnoreCase(response.getResult()) ||
                response.getRates().get(currencyDestination) == null) {
            throw new CurrencyConversionException(ResponseEnum.BAD_REQUEST_002.getMessage());
        }
    }

}
