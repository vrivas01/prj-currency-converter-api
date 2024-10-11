package com.converter.api.application.mapper;

import com.converter.api.application.dto.response.CalculatedExchangeRateResponse;
import com.converter.api.application.dto.response.ExchangeRateHistoryResponse;
import com.converter.api.application.utils.DateUtils;
import com.converter.api.domain.CurrencyConversion;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CurrencyConverterMapper {

    static CalculatedExchangeRateResponse toCalculatedResponse(CurrencyConversion data) {
        return CalculatedExchangeRateResponse.builder()
                .amount(data.getAmount())
                .amountExchangeRate(data.getAmountExchangeRate())
                .currencyOrigin(data.getCurrencyOrigin())
                .currencyDestination(data.getCurrencyDestination())
                .exchangeRate(data.getExchangeRate())
                .build();
    }

    static CurrencyConversion toEntityCurrencyConversion(BigDecimal amount,
                                                         String currencyOrigin,
                                                         String currencyDestination,
                                                         BigDecimal amountExchangeRate,
                                                         BigDecimal exchangeRate) {
        return CurrencyConversion.builder()
                .amount(amount)
                .currencyOrigin(currencyOrigin)
                .currencyDestination(currencyDestination)
                .amountExchangeRate(amountExchangeRate)
                .exchangeRate(exchangeRate)
                .registeredAt(LocalDateTime.now())
                .build();
    }

    static ExchangeRateHistoryResponse toHistoryResponse(CurrencyConversion data) {
        return ExchangeRateHistoryResponse.builder()
                .id(data.getId())
                .amount(data.getAmount())
                .currencyOrigin(data.getCurrencyOrigin())
                .currencyDestination(data.getCurrencyDestination())
                .amountExchangeRate(data.getAmountExchangeRate())
                .exchangeRate(data.getExchangeRate())
                .registeredAt(DateUtils.formatLocalDateTime(data.getRegisteredAt(), "dd/MM/yyyy HH:mm:ss"))
                .build();
    }



}
