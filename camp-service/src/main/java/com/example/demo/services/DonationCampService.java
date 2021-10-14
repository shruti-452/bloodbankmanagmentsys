package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DonationCamp;
import com.example.demo.repositories.DonationCampRepository;

@Service
public class DonationCampService {

	@Autowired
	private DonationCampRepository repo;
	
	public List<DonationCamp> findAll(){
		return this.repo.findAll();
	}
	
	public DonationCamp addDonationCamp(DonationCamp entity) {
		return this.repo.save(entity);
	}
	public List<DonationCamp> findByLocation(String location){
		return this.repo.findByLocation(location);
	}
	public int remove(int id) {
		int rowDeleted=0;
		if(this.repo.existsById(id)) {
			rowDeleted=1;
			this.repo.deleteById(id);
		}
		return rowDeleted;
	}
}
