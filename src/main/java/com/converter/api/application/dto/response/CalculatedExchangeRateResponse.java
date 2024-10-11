package com.converter.api.application.dto.response;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculatedExchangeRateResponse {
    private BigDecimal amount;
    private BigDecimal amountExchangeRate;
    private String currencyOrigin;
    private String currencyDestination;
    private BigDecimal exchangeRate;
}
