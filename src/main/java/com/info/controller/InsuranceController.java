package com.info.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.binding.request.SearchRequest;
import com.info.binding.response.PlanResponse;
import com.info.entity.InsurancePlanEntity;
import com.info.report.ExcelReportGenerator;
import com.info.service.InsurancePlanServiceImpl;

@RestController
public class InsuranceController {
	@Autowired
	private InsurancePlanServiceImpl service;

	@GetMapping("/excel")
	public void generateExcelReport(HttpServletResponse response) throws IOException {

		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchPlans(null);
		ExcelReportGenerator excelReportGenerator = new ExcelReportGenerator();
		excelReportGenerator.exportExcel(plans, response);

	}

	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlan(@RequestBody SearchRequest request) {
		List<PlanResponse> searchPlans = service.searchPlans(request);
		return new ResponseEntity<>(searchPlans, HttpStatus.OK);

	}

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> uniquePlanNames = service.getUniquePlanNames();
		return new ResponseEntity<>(uniquePlanNames, HttpStatus.OK);
	}

	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getPanStatus() {
		List<String> uniquePlanStatus = service.getUniquePlanStatus();
		return new ResponseEntity<>(uniquePlanStatus, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<InsurancePlanEntity> savePlan(@RequestBody InsurancePlanEntity planEntity) {
		InsurancePlanEntity savePlan = service.savePlan(planEntity);
		return new ResponseEntity<>(savePlan, HttpStatus.CREATED);

	}
}
