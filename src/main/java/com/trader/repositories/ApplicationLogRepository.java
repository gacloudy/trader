package com.trader.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.entity.db.ApplicationLog;


@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Integer> {

	List<ApplicationLog> findAllByOrderByDateDesc();
}