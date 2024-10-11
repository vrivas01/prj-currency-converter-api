package com.converter.api.presentation.router;

import com.converter.api.presentation.handler.ConverterHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.converter.api.presentation.router.definition.ApiRoutes;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ConverterRouter {
    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = ApiRoutes.ROUTE_CONVERTER + ApiRoutes.CURRENCY_CONVERTER,
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = ConverterHandler.class,
                            beanMethod = "currencyConverter",
                            operation = @Operation(
                                    operationId = "currencyConverter",
                                    tags = { "CURRENCY" },
                                    summary = "Convertir divisa",
                                    description = "Convierte una cantidad de una moneda de origen a otra moneda de destino, utilizando tasas de cambio actuales.",
                                    parameters = {
                                            @Parameter(name = "amount", description = "Monto a convertir", required = true, in = ParameterIn.QUERY, example = "100"),
                                            @Parameter(name = "currencyOrigin", description = "Código de la moneda de origen (ej. USD)", required = true, in = ParameterIn.QUERY, example = "USD"),
                                            @Parameter(name = "currencyDestination", description = "Código de la moneda de destino (ej. PEN)", required = true, in = ParameterIn.QUERY, example = "PEN")
                                    },
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "Conversión exitosa."),
                                            @ApiResponse(responseCode = "400", description = "Solicitud inválida debido a parámetros incorrectos o faltantes."),
                                            @ApiResponse(responseCode = "500", description = "Error interno del servidor al procesar la solicitud.")
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = ApiRoutes.ROUTE_CONVERTER + ApiRoutes.EXCHANGE_RATE_HISTORY,
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = ConverterHandler.class,
                            beanMethod = "exchangeRateHistory",
                            operation = @Operation(
                                    operationId = "exchangeRateHistory",
                                    tags = { "CURRENCY" },
                                    summary = "Obtener el historial de conversiones de divisas",
                                    description = "Recupera un listado con el historial de todas las conversiones de divisas realizadas anteriormente.",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "Historial de conversiones obtenido con éxito."),
                                            @ApiResponse(responseCode = "500", description = "Error interno del servidor al intentar procesar la solicitud.")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> ConverterRoutes(ConverterHandler handler) {
        return RouterFunctions
                .nest(path(ApiRoutes.ROUTE_CONVERTER),
                        RouterFunctions.route(GET(ApiRoutes.CURRENCY_CONVERTER), handler::currencyConverter)
                                .andRoute(GET(ApiRoutes.EXCHANGE_RATE_HISTORY), handler::exchangeRateHistory)
                );
    }
}
