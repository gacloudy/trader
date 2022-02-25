package com.trader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.IndustryTypeMst;


@Repository
public interface IndustryTypeMstRepository extends JpaRepository<IndustryTypeMst, Double> {

}