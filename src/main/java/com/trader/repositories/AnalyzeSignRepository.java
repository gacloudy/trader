package com.trader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.AnalyzeSign;


@Repository
public interface AnalyzeSignRepository extends JpaRepository<AnalyzeSign, Integer> {

}