package com.trader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockDateHistory;
import com.trader.entity.db.StockDateHistoryPK;


@Repository
public interface StockDateHistoryRepository extends JpaRepository<StockDateHistory, StockDateHistoryPK> {

}