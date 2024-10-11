package com.converter.api.infraestructure.repository;

import com.converter.api.domain.CurrencyConversion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends ReactiveCrudRepository<CurrencyConversion, Long> {
}
