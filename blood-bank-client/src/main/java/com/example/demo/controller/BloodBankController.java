package com.example.demo.controller;


import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.DonationCamp;
import com.example.demo.entity.Donor;

@Controller
public class BloodBankController {
	@Autowired
	private Donor object;
	@Autowired
	private DonationCamp campObj;
	@Autowired
	private RestTemplate template;
	
	private Donor data;
	private DonationCamp campData;

	@RequestMapping(value = "/" ,method = RequestMethod.GET)
	public String init(Model model) {
		return "index";
	}
	
	@GetMapping(path = "/donor")
	public String loadDonor(Model model) {
		model.addAttribute("donorObj",object);
		model.addAttribute("errorMsg", "");
		return "donorForm";
	}
	@PostMapping(path = "/donor")
	public String postDonor(@Valid Model model,Donor donor,BindingResult result) {
		data = donor;
		String nextStep = "success";
		try {
		if(result.hasErrors()) {
			nextStep="donorForm";
		}
		else {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Donor> requestBody = new HttpEntity<>(donor,headers);
		    Donor obj =  this.template.postForObject("http://localhost:2000/api/v1/donors/", requestBody, Donor.class);
		    model.addAttribute("object",obj);
		    }
			
		}
		catch(Exception e) {
			String errorMsg = "Error occured During Registration! Please check you Details";
			return "redirect:/registerError?errorMsg="+errorMsg;	
		}
		return nextStep;
	}
	
	@GetMapping("/registerError")
	public String registerError(@RequestParam("errorMsg") String errorMsg, Model model) {
		model.addAttribute("donorObj",data);
		model.addAttribute("errorMsg",errorMsg);
		return "donorForm";
	}
	
	@GetMapping(path = "/update")
	public String updateDonor(Model model) {
		model.addAttribute("donorObj",object);
		String[] resp =template.getForObject("http://localhost:2000/api/v1/donors/getids",String[].class);
		model.addAttribute("ids", resp);
		return "updateDonorId";
	}
	@GetMapping(path = "/updatedonorform")
	public String updateDonorById(@RequestParam int id, Model model) {
		Donor obj = template.getForObject("http://localhost:2000/api/v1/donors/"+id, Donor.class);
		model.addAttribute("errorMsg", "");
		model.addAttribute("donorObj", obj);
		return "updateDonorForm";
	}
	@PutMapping(path="/updatedonorform")
	public String putDonor(@Valid Model model,Donor donor,BindingResult result) {
		data = donor;
		String nextStep = "updateSuccess";
		try {
		if(result.hasErrors()) {
			nextStep="updateDonorForm";
		}
		else {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Donor> requestBody = new HttpEntity<>(donor,headers);
		    //Donor obj =  this.template.postForObject("http://localhost:2000/api/v1/donors/", requestBody, Donor.class);
		    //model.addAttribute("object",obj);
		    }
			
		}
		catch(Exception e) {
			String errorMsg = "Error occured During Registration! Please check you Details";
			return "redirect:/updateError?errorMsg="+errorMsg;	
		}
		return nextStep;
	}
	
	@GetMapping("/updateError")
	public String updateError(@RequestParam("errorMsg") String errorMsg, Model model) {
		model.addAttribute("donorObj",data);
		model.addAttribute("errorMsg",errorMsg);
		return "updateDonorForm";
	}
	
	
	@GetMapping(path = "/donorderegistration")
	public String loadDeregistration(Model model) {
		model.addAttribute("donorObj", object);
		return "donorDeRegistration";
	}
	@GetMapping(path = "/delete")
	public String deleteDonor(@RequestParam int id, Model model) {
		try {
		this.template.delete("http://localhost:2000/api/v1/donors/"+id);
		}
		catch(Exception e) {
			model.addAttribute("message", "Donor not exist");
			return "delete";
		}
		model.addAttribute("message", "Donor is un registered successfully");
		return "delete";
	}
	
	@GetMapping(path = "/donationcamp")
	public String loadCamp(Model model) {
		model.addAttribute("campObj", campObj);
		model.addAttribute("errorMsg", "");
		return "donationCamp";
	}
	@PostMapping(path = "/donationcamp")
	public String postCamp(@Valid Model model,DonationCamp camp,BindingResult result) {
		campData = camp;
		String nextStep = "success";
		try {
		if(result.hasErrors()) {
			nextStep="donationCamp";
		}
		else {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<DonationCamp> requestBody = new HttpEntity<>(camp,headers);
		    DonationCamp obj =  this.template.postForObject("http://localhost:2050/api/v1/camps/", requestBody, DonationCamp.class);
		    model.addAttribute("object",obj);
		    }
			
		}
		catch(Exception e) {
			String errorMsg = "Error occured During Registration! Please check you Details";
			return "redirect:/registerCampError?errorMsg="+errorMsg;	
			
		}
		return nextStep;
	}
	
	@GetMapping("/registerCampError")
	public String registerCampError(@RequestParam("errorMsg") String errorMsg, Model model) {
		model.addAttribute("campObj", campData);
		model.addAttribute("errorMsg",errorMsg);
		return "donationCamp";
	}
	@GetMapping(path = "/campderegistration")
	public String loadCampDeRegistration(Model model) {
		model.addAttribute("campObj", campObj);
		return "campDeRegistration";
	}
	@GetMapping(path = "/deletecamp")
	public String deleteCamp(@RequestParam int id, Model model) {
		try {
		this.template.delete("http://localhost:2050/api/v1/camps/"+id);
		}
		catch(Exception e) {
			model.addAttribute("message", "Camp not exist");
			return "delete";
		}
		model.addAttribute("message", "Camp is un registered successfully");
		return "delete";
	}
	
	
	
	
	@GetMapping(path = "/search")
	public String loadSearch(Model model) {
		return "search";
	}
	@GetMapping(path = "/searchbybloodgroup")
	public String loadSearchByGroup(Model model) {	
		model.addAttribute("donorObj",object);
		return "searchByGroup";
	}
	@GetMapping(path = "/bloodgroup")
	public String searchByGroup(@RequestParam String bloodGroup,Model model) {	
		Donor[] resp =template.getForObject("http://localhost:2000/api/v1/donors/bloodgroup/"+bloodGroup,Donor[].class);
		model.addAttribute("donors",resp);
		model.addAttribute("bloodgroup", bloodGroup);
		return "bloodGroupDonors";
	}
	@GetMapping(path = "/searchbylocation")
	public String loadSearchByLocation(Model model) {	
		model.addAttribute("donorObj",object);
		return "searchByLocation";
	}
	@GetMapping(path = "/location")
	public String searchByLocation(@RequestParam String location,Model model) {
		location = location.trim();
		if(location.length()==0) {
			return "locationDonors";
		}
		Donor[] resp =template.getForObject("http://localhost:2000/api/v1/donors/location/"+location,Donor[].class);
		model.addAttribute("donors",resp);
		model.addAttribute("location", location);
		return "locationDonors";
	}
	@GetMapping(path = "/searchbyName")
	public String loadSearchByName(Model model) {	
		model.addAttribute("donorObj",object);
		return "searchByName";
	}
	@GetMapping(path = "/donorName")
	public String searchByName(@RequestParam String donorName,Model model) {
		donorName= donorName.trim();
		if(donorName.length()==0) {
			return "donorNameDonors";
		}
		Donor[] resp =template.getForObject("http://localhost:2000/api/v1/donors/donorName/"+donorName,Donor[].class);
		model.addAttribute("donors",resp);
		model.addAttribute("name", donorName);
		return "donorNameDonors";
	}

}
