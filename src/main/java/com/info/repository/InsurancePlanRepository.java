package com.info.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.info.entity.InsurancePlanEntity;

public interface InsurancePlanRepository extends JpaRepository<InsurancePlanEntity, Serializable> {
	
	@Query("select distinct planName from InsurancePlanEntity")
	public List<String> getPlanNames();
	
	@Query("select distinct planStatus from InsurancePlanEntity")
	public List<String> getPlanStatus();

}
