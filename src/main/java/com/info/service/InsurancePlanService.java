package com.info.service;

import java.util.List;

import com.info.binding.request.SearchRequest;
import com.info.binding.response.PlanResponse;
import com.info.entity.InsurancePlanEntity;

public interface InsurancePlanService {
	
	public List<PlanResponse> searchPlans(SearchRequest request);
	
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
	
	
	public InsurancePlanEntity savePlan(InsurancePlanEntity planEntity);

}
