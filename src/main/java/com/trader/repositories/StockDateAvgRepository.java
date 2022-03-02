package com.trader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockDateAvg;
import com.trader.entity.db.StockDateAvgPK;


@Repository
public interface StockDateAvgRepository extends JpaRepository<StockDateAvg, StockDateAvgPK> {

}