package com.trader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.AnalyzeResult;
import com.trader.entity.db.AnalyzeResultPK;


@Repository
public interface AnalyzeResultRepository extends JpaRepository<AnalyzeResult, AnalyzeResultPK> {

}