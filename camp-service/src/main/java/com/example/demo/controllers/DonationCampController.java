package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DonationCamp;
import com.example.demo.services.DonationCampService;

@RestController
@RequestMapping(path = "/api/v1/camps")
public class DonationCampController {

	@Autowired
	private DonationCampService service;
	
	@GetMapping
	public List<DonationCamp> findAll(){
		return this.service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<DonationCamp> add(@RequestBody DonationCamp entity) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.addDonationCamp(entity));
	}
	@GetMapping(path = "/location/{location}")
	public List<DonationCamp> findByLocation(@PathVariable("location") String location){
		return this.service.findByLocation(location);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> removeCamp(@PathVariable("id") int id) {
		int count = this.service.remove(id);
		return count==1?ResponseEntity.ok().body(count+" rows deleted"):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
	}
	
}
