package com.converter.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "currency_conversion")
public class CurrencyConversion {
    @Id
    private Long id;
    private BigDecimal amount;
    private String currencyOrigin;
    private String currencyDestination;
    private BigDecimal amountExchangeRate;
    private BigDecimal exchangeRate;
    private LocalDateTime registeredAt;
}
