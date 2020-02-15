package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.AppConfig;

@Service
public class FilterService {
	
	private Logger logger = LoggerFactory.getLogger(FilterService.class);
	private RestTemplate restTemplate;

	@Autowired
	private AppConfig appConfig;
	
	 public FilterService(RestTemplate restTemplate, AppConfig appConfig) {
		    this.restTemplate = restTemplate;
		    this.appConfig = appConfig;
	 }


	public Object filterResult(String value) {
		ResponseEntity<Object> response;
		String apiKey = appConfig.getAppKey();
		String newsUrl = String.format(appConfig.getNewsUrl(), value, apiKey);

		logger.info("Calling News api to fetch response for: {}", newsUrl);
		response = restTemplate.getForEntity(newsUrl, Object.class);

		if (response.getStatusCode().is2xxSuccessful() && !ObjectUtils.isEmpty(response)) {
			logger.trace("Received response from News api: {}", response.toString());
			return response.getBody();
		}
		return null;
	}	

}
