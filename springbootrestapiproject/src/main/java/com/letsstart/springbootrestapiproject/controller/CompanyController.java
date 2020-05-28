package com.letsstart.springbootrestapiproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.letsstart.springbootrestapiproject.dao.CompanyDAO;
import com.letsstart.springbootrestapiproject.dao.RequestDAO;
import com.letsstart.springbootrestapiproject.dao.RequestHistoryDAO;
import com.letsstart.springbootrestapiproject.model.Company;
import com.letsstart.springbootrestapiproject.model.Login;
import com.letsstart.springbootrestapiproject.model.Request;
import com.letsstart.springbootrestapiproject.model.RequestHistory;

import enums.Status;

@CrossOrigin(origins = "http://localhost:4078")
@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	CompanyDAO companyDAO;
	@Autowired
	RequestDAO requestDAO;
	@Autowired
	RequestHistoryDAO requestHistoryDAO;
	
	/* to save a company*/
	@PostMapping("/register")
	public Company createCompany(@Valid @RequestBody Company comp) {
		return companyDAO.save(comp);
	}
	
	/* to save a company*/
	@PostMapping("/update-company")
	public Company updateompany(@Valid @RequestBody Company comp) {
		Optional<Company> compaOpt = companyDAO.findOne(comp.getId());
		Company compa = compaOpt.get();
		compa.setCompanyName(comp.getCompanyName());
		compa.setAddress(comp.getAddress());
		compa.setAfm(comp.getAfm());
		compa.setPhone(comp.getPhone());
		return companyDAO.save(compa);
	}
	
	@PostMapping("/request")
	public Request createRequest(@Valid @RequestBody Request req) {
		Request req1 = new Request();
		req1.setDescription(req.getDescription());
		req1.setSupervisor(req.getSupervisor());
		req1.setCompany_id(req.getCompany_id());
		return requestDAO.save(req1);
	}
	
	@PostMapping("/update-request")
	public Request updateRequest(@Valid @RequestBody Request req) {	
		Optional<Request> req1 = requestDAO.findOne(req.getId());
		Request request = req1.get();
		request.setStatus(req.getStatus());
		return requestDAO.save(request);		
	}
	
	/*get all companies*/
	@GetMapping("/companies")
	public List<Company> getAllCompanies(){
		return companyDAO.findAll();
	}
	
	/*get all requests*/
	@GetMapping("/requesthistory")
	public List<RequestHistory> getAllRequests(){
		return requestHistoryDAO.findAll();
	}
	
	
	
	/*get company by id*/
//	@GetMapping("/companies/{id}")
//	public ResponseEntity<Company> getCompanyById(@PathVariable(value="id") Long compid){
//		
//		Optional<Company> optEmp=companyDAO.findOne(compid);
//		Company comp;
//		try {
//			comp = optComp.get();
//		} catch (NoSuchElementException e) {
//			comp = null;
//		}
//		
//		if(comp==null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok().body(comp);
//		
//	}
	
	// Company login
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public Object findByEmailAndPassword(@RequestBody Body body) {
//		List<Company> companies = companyDAO.loginByEmail(body.getEmail(), body.getPassword());
//		if(companies.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//		return companies.get(0);
//	}
	
	@PostMapping("/login")
	public Object createLogin(@Valid @RequestBody Login log) {
		List<Company> companies = companyDAO.loginByEmail(log.getEmail(), log.getPassword());
		if(companies.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return companies.get(0);
	}
	
	
	/*update an company by compid*/
//	@PutMapping("/companies/{id}")
//	public ResponseEntity<Company> updateCompany(@PathVariable(value="id") Long compid,@Valid @RequestBody Company compDetails){
//		
//		Optional<Company> optEmp=companyDAO.findOne(compid);
//		Company comp;
//		try {
//			comp = optComp.get();
//		} catch (NoSuchElementException e) {
//			comp = null;
//		}
//		
//		if(comp==null) {
//			return ResponseEntity.notFound().build();
//		}
//		comp.setUsername(compDetails.getUsername());
//		comp.setPassword(compDetails.getPassword());
//		comp.setFirstname(compDetails.getFirstname());
//		comp.setLastname(compDetails.getLastname());
//		comp.setAmount(compDetails.getAmount());
//		
//		Company updateCompany=companyDAO.save(comp);
//		return ResponseEntity.ok().body(updateCompany);
//	}
	
	/*Delete a request*/
//	@DeleteMapping("/requests/{id}")
//	public ResponseEntity<Request> deleteRequest(@PathVariable(value="id") Long reqid){
//		
//		Optional<Request> optEmp=requestDAO.findOne(reqid.longValue());
//		Company comp;
//		try {
//			reqid = optEmp.get();
//		} catch (NoSuchElementException e) {
//			reqid = null;
//		}
//		if(reqid==null) {
//			return ResponseEntity.notFound().build();
//		}
//			requestDAO.delete(reqid);
//		
//		return ResponseEntity.ok().build();
//	}
		

}
