package com.trader.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockDateAvg;
import com.trader.entity.db.StockDateAvgPK;


@Repository
public interface StockDateAvgRepository extends JpaRepository<StockDateAvg, StockDateAvgPK> {
	@Query(value="SELECT * FROM stock_date_avg WHERE code = :code AND span = :span AND price_date >= :date_from AND price_date <= :date_to ORDER BY price_date DESC", nativeQuery = true)
	List<StockDateAvg> findByCodeAndAndSpanPriceDateBetwee(@Param("code") int code, @Param("span") int span, @Param("date_from")  String dateFrom, @Param("date_to")  String dateTo);

}