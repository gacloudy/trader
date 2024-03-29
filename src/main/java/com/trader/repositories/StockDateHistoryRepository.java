package com.trader.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trader.entity.StockDateHistoryCount;
import com.trader.entity.db.StockDateHistory;
import com.trader.entity.db.StockDateHistoryPK;


@Repository
public interface StockDateHistoryRepository extends JpaRepository<StockDateHistory, StockDateHistoryPK> {

	@Query(value="SELECT * FROM stock_date_history WHERE code = :code AND price_date = :price_date ", nativeQuery = true)
	StockDateHistory findOneByCodeAndPriceDate(@Param("code") int code, @Param("price_date")  String priceDate);

	@Query(value="SELECT * FROM stock_date_history WHERE code = :code AND price_date <= :price_date ORDER BY price_date DESC", nativeQuery = true)
	List<StockDateHistory> findByCodeAndPriceDateLessThanEqual(@Param("code") int code, @Param("price_date")  String priceDate);

	@Query(value="SELECT * FROM stock_date_history WHERE code = :code AND  price_date >= :date_from AND price_date <= :date_to ORDER BY price_date DESC", nativeQuery = true)
	List<StockDateHistory> findByCodeAndPriceDateBetween(@Param("code") int code, @Param("date_from")  String dateFrom, @Param("date_to")  String dateTo);

	@Query(value="SELECT price_date, count(price_date) as count FROM stock_date_history GROUP BY price_date ORDER BY price_date DESC  LIMIT 100", nativeQuery = true)
	List<Object[]> countByPriceDateQuery();
	
	default List<StockDateHistoryCount> countByPriceDate() {
	    return countByPriceDateQuery().stream()  //*3
	      .map(StockDateHistoryCount::new)
	      .collect(Collectors.toList());
	  }
}