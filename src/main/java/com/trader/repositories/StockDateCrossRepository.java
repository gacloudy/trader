package com.trader.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockDateCross;


@Repository
public interface StockDateCrossRepository extends JpaRepository<StockDateCross, StockDateCross> {

	@Transactional
	@Modifying
	@Query(value="DELETE FROM stock_date_cross as c where c.price_date = :price_date", nativeQuery = true)
	void deleteByPriceDate(@Param("price_date") String priceDate);
}