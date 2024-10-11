package com.converter.api.application.dto.response;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateHistoryResponse {
    private Long id;
    private BigDecimal amount;
    private String currencyOrigin;
    private String currencyDestination;
    private BigDecimal amountExchangeRate;
    private BigDecimal exchangeRate;
    private String registeredAt;
}
