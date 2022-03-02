package com.trader.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockDateHistory;
import com.trader.entity.db.StockDateHistoryPK;


@Repository
public interface StockDateHistoryRepository extends JpaRepository<StockDateHistory, StockDateHistoryPK> {

	@Query(value="SELECT * FROM stock_date_history WHERE code = :code AND price_date <= :price_date ORDER BY price_date DESC", nativeQuery = true)
	List<StockDateHistory> findByCodeAndPriceDateLessThanEqual(@Param("code") int code, @Param("price_date")  String priceDate);


}