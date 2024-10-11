package com.converter.api.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CurrencyApiConfig {
    private String baseUrl;
    private String exchangeRate;
    private int connectTimeout;
    private int readTimeout;
}
