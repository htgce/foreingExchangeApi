package com.openpayd.foreignexchange.api.dao;

import com.openpayd.foreignexchange.api.model.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, String> {
}
