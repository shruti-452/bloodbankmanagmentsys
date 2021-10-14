package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Donor;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {

	List<Donor> findByLocation(String location);

	List<Donor> findByBloodGroup(String group);
	
	@Query(nativeQuery = true, value = "select id from donor")
	@Modifying
	@Transactional
	public List<Integer> getIds();

}
