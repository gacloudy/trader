package com.trader.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockDateCross;
import com.trader.entity.db.StockDateCrossPK;


@Repository
public interface StockDateCrossRepository extends JpaRepository<StockDateCross, StockDateCrossPK> {

	@Query(value="SELECT * FROM stock_date_cross WHERE code = :code AND price_date = :price_date ", nativeQuery = true)
	List<StockDateCross> findByCodeAndPriceDate(@Param("code") int code, @Param("price_date")  String priceDate);

	@Query(value="SELECT * FROM stock_date_cross WHERE code = :code AND price_date = :price_date AND long_span = :long_span AND short_span = :short_span ", nativeQuery = true)
	StockDateCross findOneByCodeAndPriceDateAndLongSpanAndShortSpan(@Param("code") int code, @Param("price_date")  String priceDate, @Param("long_span")  int longSpan, @Param("short_span")  int shortSpan);

	@Transactional
	@Modifying
	@Query(value="DELETE FROM stock_date_cross as c where c.price_date = :price_date", nativeQuery = true)
	void deleteByPriceDate(@Param("price_date") String priceDate);
}