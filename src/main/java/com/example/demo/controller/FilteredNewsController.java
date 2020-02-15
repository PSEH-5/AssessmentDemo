package com.example.demo.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FilterService;

@RestController
public class FilteredNewsController {
	
	@Autowired
	FilterService service;

	@GetMapping("/keyword/{value}")
	public Object getAll(@PathVariable @NotNull String value) {
		return service.filterResult(value);
	}
}
