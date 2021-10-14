package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DonationCamp;
@Repository
public interface DonationCampRepository extends JpaRepository<DonationCamp, Integer>{

	List<DonationCamp> findByLocation(String location);

}
