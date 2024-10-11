package com.converter.api.application.dto.response;

import lombok.*;
import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRateResponse {
    private String result;
    private String provider;
    private String documentation;
    private String termsOfUse;
    private long timeLastUpdateUnix;
    private String timeLastUpdateUtc;
    private long timeNextUpdateUnix;
    private String timeNextUpdateUtc;
    private long timeEolUnix;
    private String baseCode;
    private Map<String, BigDecimal> rates;
}
