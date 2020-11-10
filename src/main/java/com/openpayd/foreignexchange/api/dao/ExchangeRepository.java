package com.openpayd.foreignexchange.api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Page<Exchange> findAllByTransactionDateBetween(LocalDateTime beginDate, LocalDateTime endDate, Pageable pageable);

}
