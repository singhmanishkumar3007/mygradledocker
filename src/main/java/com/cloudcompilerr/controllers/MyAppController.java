package com.cloudcompilerr.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MyAppController {

	@GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getName() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString("Manish");
	}

}
