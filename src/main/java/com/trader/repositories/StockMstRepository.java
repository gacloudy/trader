package com.trader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.StockMst;


@Repository
public interface StockMstRepository extends JpaRepository<StockMst, Integer> {

	StockMst findByCode(@Param("code") int code);

}