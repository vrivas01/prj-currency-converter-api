package com.converter.api.presentation.handler;

import com.converter.api.application.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.converter.api.application.service.ICurrencyConverterService;
import com.converter.api.exception.CurrencyConversionException;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
public class ConverterHandler {

    private final ICurrencyConverterService currencyConverterService;

    public Mono<ServerResponse> currencyConverter(ServerRequest request) {

        Optional<BigDecimal> amount = request.queryParam("amount").map(BigDecimal::new);
        Optional<String> currencyOrigin = request.queryParam("currencyOrigin");
        Optional<String> currencyDestination = request.queryParam("currencyDestination");

        if (amount.isEmpty() || currencyOrigin.isEmpty() || currencyDestination.isEmpty()) {
            return ServerResponse.badRequest()
                    .bodyValue(ResponseEnum.BAD_REQUEST_001.getMessage());
        }

        return currencyConverterService.getExchangeRate(amount.get(), currencyOrigin.get(), currencyDestination.get())
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result))
                .onErrorResume(CurrencyConversionException.class, e -> {
                    log.error("Currency conversion error: ", e);
                    return ServerResponse.status(HttpStatus.BAD_REQUEST)
                            .bodyValue(e.getMessage());
                })
                .onErrorResume(e -> {
                    log.error("Error currencyConverter: ", e);
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .bodyValue("Error currencyConverter");
                });
    }

    public Mono<ServerResponse> exchangeRateHistory(ServerRequest request) {

        return currencyConverterService.getExchangeRateHistory()
                .collectList()
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result))
                .onErrorResume(e -> {
                    log.error("Error exchangeRateHistory: ", e);
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .bodyValue("Error exchangeRateHistory");
                });
    }

}
