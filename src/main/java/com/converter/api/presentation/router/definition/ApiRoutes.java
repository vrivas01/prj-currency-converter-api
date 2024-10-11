package com.converter.api.presentation.router.definition;

public class ApiRoutes {
    public static final String BASE_ROUTE = "/api/prj-currency-converter-api/v1";

    /**
     * ROUTE SECTIONS
     */
    public static final String ROUTE_CONVERTER = BASE_ROUTE + "/converter";

    /**
     * ENDPOINTS
     */
    public static final String CURRENCY_CONVERTER = "/currency-converter";
    public static final String EXCHANGE_RATE_HISTORY = "/exchange-rate-history";
}